package pl.kurs;

import java.util.List;

public interface CarDAO {
	
	List<Car> getAll();

	Car get(int idc);

	void create(Car car);

	void update(Car car);

	void delete(int idc);
}