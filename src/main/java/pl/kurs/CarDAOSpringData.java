package pl.kurs;

import java.util.List;

public class CarDAOSpringData implements CarDAO {

	private CarRepository carRepository;

	public CarDAOSpringData(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Override
	public List<Car> getAll() {
		return carRepository.findAll();
	}

	@Override
	public Car get(int idc) {
		return carRepository.findOne(idc);
	}

	@Override
	public void create(Car car) {
		carRepository.save(car);
	}

	@Override
	public void update(Car car) {
		carRepository.save(car);
	}

	@Override
	public void delete(int idc) {
		carRepository.delete(idc);
	}

}
