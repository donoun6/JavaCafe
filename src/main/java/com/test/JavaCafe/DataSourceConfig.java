package com.test.JavaCafe;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.test.JavaCafe.kiosk.Dao.KioskDao;
import com.test.JavaCafe.kiosk.service.KioskServiceImpl;
import com.test.JavaCafe.menuitem.Dao.MenuDao;
import com.test.JavaCafe.menuitem.Service.MenuServiceImpl;


@Configuration
public class DataSourceConfig {
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/jvx330?serverTimezone=Asia/Seoul");
		ds.setUsername("jvx330");
		ds.setPassword("jvx330");
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		ds.setMaxIdle(10);
		return ds;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public MenuDao menuDao() {
		return new MenuDao(dataSource());
	}
	
	@Bean
	public KioskDao kioskDao() {
		return new KioskDao(dataSource());
	}
	
	
	@Bean
	public MenuServiceImpl menuService() {
		return new MenuServiceImpl(menuDao());
	}
	
	@Bean
	public KioskServiceImpl kioskService() {
		return new KioskServiceImpl(kioskDao());
	}
}
