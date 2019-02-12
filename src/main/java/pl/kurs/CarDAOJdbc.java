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
		List<Car> carList = new ArrayList<Car>();
		try {
			ResultSet resultSet = dataSource.getConnection().createStatement().executeQuery("select * from car");
			while (resultSet.next()) {
				Car car = new Car();
				car.setId(resultSet.getInt("id"));
				car.setMake(resultSet.getString("make"));
				car.setModel(resultSet.getString("model"));
				car.setPrice(resultSet.getDouble("price"));
				car.setRegNum(resultSet.getString("regnum"));
				carList.add(car);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carList;
	}

	@Override
	public void create(Car car) {
		try {
			PreparedStatement statement = dataSource.getConnection().
					prepareStatement("insert into car(make, model, regnum, price) values(?, ?, ?, ?)");
			statement.setString(1, car.getMake());
			statement.setString(2, car.getModel());
			statement.setString(3, car.getRegNum());
			statement.setDouble(4, car.getPrice());
			statement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Car get(int id) {
		try {
			ResultSet rs = dataSource.getConnection().createStatement()
					.executeQuery("select * from car where id=" + id);
			rs.next();
			Car car = new Car();
			car.setId(rs.getInt("id"));
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
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement("delete from car where id=?");
			pstmt.setInt(1, idc);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
