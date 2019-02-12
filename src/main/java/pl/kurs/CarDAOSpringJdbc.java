package pl.kurs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class CarDAOSpringJdbc extends JdbcDaoSupport implements CarDAO {

	@Override
	public List<Car> getAll() {
		throw new RuntimeException("Implement me");
	}

	@Override
	public void create(Car car) {
		throw new RuntimeException("Implement me");
	}

	@Override
	public void delete(int idc) {
		getJdbcTemplate().update("delete from car where idc=?", new Object[] { idc });
	}

	@Override
	public Car get(int idc) {
		throw new RuntimeException("Not implemented and not needed in examples. Could be omitted.");
	}

	@Override
	public void update(Car car) {
		throw new RuntimeException("Not implemented and not needed in examples. Could be omitted.");
	}

}
