package pl.kurs;

import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

@Configuration
class CarsConfiguration {

	@Bean
	public DataSource dataSource() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUrl("jdbc:mysql://127.0.0.1:6033/komis");
		dataSource.setUser("root");
		dataSource.setPassword("");
		return dataSource;
	}

	/*

	Utworzenie bazy danych "komis" i tabeli "car":
	create database komis;
	use komis
	create table car;
	create table car(id int unsigned not null auto_increment, make varchar(20), model varchar(20),
	price double(7,2), regnum varchar(40), constraint pk_id primary key (id));

	 */

	@Bean
	public CarDAO carDAO(DataSource dataSource) {
		CarDAOSpringJdbc carDAOSpringJdbc = new CarDAOSpringJdbc();
		carDAOSpringJdbc.setDataSource(dataSource);
		return carDAOSpringJdbc;
	}

}

public class Start {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(CarsConfiguration.class);
		CarDAO dao = context.getBean("carDAO", CarDAO.class);

		for (int i = 0; i < 5; i++) {
			Car car = new Car();
			car.setMake("Fiat");
			car.setModel("Panda" + i);
			car.setPrice(1000 + i);
			car.setRegNum(String.format("KR%05d", new Random().nextInt(100000)));
			dao.create(car);
		}

		List<Car> cars = dao.getAll();
		for (Car car : cars) {
			System.out.println(car.toString());
		}

		for (Car car : cars) {
			dao.delete(car.getId());
		}

	}
}
