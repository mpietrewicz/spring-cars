package pl.kurs;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter @Getter
@Entity
public class Car {
	@Id
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
