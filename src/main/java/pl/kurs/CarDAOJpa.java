package pl.kurs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class CarDAOJpa implements CarDAO {

	@Override
	public List<Car> getAll() {
		throw new RuntimeException("Implement me");
	}

	@Override
	public Car get(int idc) {
		throw new RuntimeException("Implement me");
	}

	@Override
	public void create(Car car) {
		throw new RuntimeException("Implement me");
	}

	@Override
	public void update(Car car) {
		throw new RuntimeException("Implement me");
	}

	@Override
	public void delete(int idc) {
		throw new RuntimeException("Implement me");
	}

}
