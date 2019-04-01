package pl.kurs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class CarDAOJpa implements CarDAO {

	@PersistenceContext EntityManager entityManager;

	@Transactional
	@Override
	public List<Car> getAll() {
		List<Car> cars = entityManager.createQuery("from Car").getResultList();
		return cars;
	}

	@Transactional
	@Override
	public Car get(int idc) {
		Car car = entityManager.find(Car.class, idc);
		return car;
	}

	@Transactional
	@Override
public void create(Car car) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(car);
			entityManager.getTransaction().commit();
		} catch (Exception transactionException) {
			try {
				entityManager.getTransaction().rollback();
			} catch (Exception rollbackException) {
				System.out.println("rollbackException: " + rollbackException.getMessage());
			}
		}
	}

	@Transactional
	@Override
	public void update(Car car) {
		try {
			entityManager.getTransaction().begin();
			Car carToUpdate = entityManager.find(Car.class, car.getId());
			entityManager.merge(carToUpdate);

			carToUpdate.setModel(car.getModel());
			carToUpdate.setMake(car.getMake());
			carToUpdate.setPrice(123);
			carToUpdate.setRegNum(car.getRegNum());

			entityManager.getTransaction().commit();
		} catch (Exception transactionException) {
			try {
				entityManager.getTransaction().rollback();
			} catch (Exception rollbackException) {
				System.out.println("rollbackException: " + rollbackException.getMessage());
			}
		}
	}

	@Transactional
	@Override
	public void delete(int idc) {
		Car car = entityManager.find(Car.class, idc);
		entityManager.remove(car);
	}

}
