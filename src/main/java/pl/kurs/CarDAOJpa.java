package pl.kurs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CarDAOJpa implements CarDAO {

	EntityManagerFactory entityManagerFactory;

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public List<Car> getAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Car> cars = entityManager.createQuery("from Car").getResultList();
		entityManager.close();
		return cars;
	}

	@Override
	public Car get(int idc) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Car car = entityManager.find(Car.class, idc);
		entityManager.close();
		return car;
	}

	@Override
public void create(Car car) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
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
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void update(Car car) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
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
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void delete(int idc) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Car car = entityManager.find(Car.class, idc);
		entityManager.remove(car);
		entityManager.close();
	}

}
