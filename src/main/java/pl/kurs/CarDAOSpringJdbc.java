package pl.kurs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class CarDAOSpringJdbc extends JdbcDaoSupport implements CarDAO {

	private static final RowMapper<Car> mapper = new RowMapper<Car>() {
		@Override
		public Car mapRow(ResultSet resultSet, int i) throws SQLException {
			Car car = new Car();
			car.setId(resultSet.getInt("id"));
			car.setMake(resultSet.getString("make"));
			car.setModel(resultSet.getString("model"));
			car.setRegNum(resultSet.getString("regnum"));
			car.setPrice(resultSet.getDouble("price"));
			return car;
		}
	};

	@Override
	public List<Car> getAll() {
		return getJdbcTemplate().query("select * from car", mapper);
	}

	@Override
	public void create(Car car) {
		getJdbcTemplate().update("insert into car(make, model, regnum, price) values(?, ?, ?, ?)",
				car.getMake(), car.getModel(), car.getRegNum(), car.getPrice());
	}

	@Override
	public void delete(int idc) {
		getJdbcTemplate().update("delete from car where id=?", new Object[] { idc });
	}

	@Override
	public Car get(int id) {
		try {
			return getJdbcTemplate().queryForObject("select * from car where id=?", mapper, id);
		} catch (EmptyResultDataAccessException exception) {
			System.out.println("Błąd! Nie znaleziono rekordu o id: " +id);
			return null;
		}
	}

	@Override
	public void update(Car car) {
		throw new RuntimeException("Not implemented and not needed in examples. Could be omitted.");
	}

}
