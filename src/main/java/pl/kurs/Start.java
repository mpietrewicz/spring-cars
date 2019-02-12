package pl.kurs;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import org.aspectj.lang.annotation.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

@Configuration
class CarsConfiguration {

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
			dao.delete(car.getIdc());
		}

	}
}
