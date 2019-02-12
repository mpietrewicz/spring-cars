package pl.kurs;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

public class Car {
	int idc;
	String make;
	String model;
	String regNum;
	double price;
	
	public int getIdc() {
		return idc;
	}
	public void setIdc(int idc) {
		this.idc = idc;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getRegNum() {
		return regNum;
	}
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "idc:"+idc+" make: "+make+" model: "+model+" regnum: "+regNum+" price: "+price;
	}
	
}
