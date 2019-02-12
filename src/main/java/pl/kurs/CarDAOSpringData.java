package pl.kurs;

import java.util.List;

public class CarDAOSpringData implements CarDAO {

	private CarRepository carRepository;

	public CarDAOSpringData(CarRepository carRepository) {
		this.carRepository = carRepository;
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
