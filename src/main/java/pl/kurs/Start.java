package pl.kurs;

import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories(basePackages = "pl.kurs")
class CarsConfiguration {

	@Bean
	public DataSource dataSource() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUrl("jdbc:mysql://127.0.0.1:6033/komis");
		dataSource.setUser("root");
		dataSource.setPassword("");
		return dataSource;
	}

	@Bean
	public JpaTransactionManager transactionManager(AbstractEntityManagerFactoryBean entityManageFactory) {
		return new JpaTransactionManager(entityManageFactory.getObject());
	}

	@Bean
	public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        return entityManagerFactory;
    }

	@Bean
	public CarDAO carDAO(CarRepository carRepository) {
        CarDAOSpringData carDAOSpringData = new CarDAOSpringData(carRepository);
        return carDAOSpringData;
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

//		Car car = dao.get(163);
//		System.out.println("car: " +car);
//		car.setModel("XXX");
//		dao.update(car);
//		Car updatedCar = dao.get(163);
//		System.out.println("updatedCar: " +updatedCar);


		for (Car car : cars) {
			System.out.println("Delete car id: " + car.id +" Model: " +dao.get(car.getId()).getModel());
			dao.delete(car.getId());
		}

	}
}
