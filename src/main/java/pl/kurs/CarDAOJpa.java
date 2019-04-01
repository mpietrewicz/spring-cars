package pl.kurs;

import java.util.List;

import javax.persistence.EntityManagerFactory;

public class CarDAOJpa implements CarDAO {

	EntityManagerFactory entityManagerFactory;

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

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
