package pl.kurs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class CarDAOJdbc implements CarDAO {

	DataSource dataSource;
	
	public CarDAOJdbc(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Car> getAll() {
		throw new RuntimeException("Implement me");
	}

	@Override
	public void create(Car car) {
		throw new RuntimeException("Implement me");
	}

	@Override
	public Car get(int idc) {
		try {
			ResultSet rs = dataSource.getConnection().createStatement()
					.executeQuery("select * from car where idc=" + idc);
			rs.next();
			Car car = new Car();
			car.setIdc(rs.getInt("idc"));
			car.setMake(rs.getString("make"));
			car.setModel(rs.getString("model"));
			car.setPrice(rs.getDouble("price"));
			car.setRegNum(rs.getString("regnum"));
			return car;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Car car) {
		try {
			PreparedStatement pstmt = dataSource.getConnection()
					.prepareStatement("update car set make=?,model=?,regnum=?,price=?");
			pstmt.setString(1, car.getMake());
			pstmt.setString(2, car.getModel());
			pstmt.setString(3, car.getRegNum());
			pstmt.setDouble(4, car.getPrice());
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void delete(int idc) {
		try {
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement("delete from car where idc=?");
			pstmt.setInt(1, idc);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
