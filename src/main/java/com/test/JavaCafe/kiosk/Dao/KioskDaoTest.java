package com.test.JavaCafe.kiosk.Dao;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.test.JavaCafe.DataSourceConfig;

public class KioskDaoTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
		KioskDao dao = context.getBean("kioskDao", KioskDao.class);
		System.out.println(dao.allFindOrder(1005));
		
		context.close();
	}
}
