package pl.kurs;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Car {
	int id;
	String make;
	String model;
	String regNum;
	double price;

	@Override
	public String toString() {
		return "id:"+ id +" make: "+make+" model: "+model+" regnum: "+regNum+" price: "+price;
	}
	
}
