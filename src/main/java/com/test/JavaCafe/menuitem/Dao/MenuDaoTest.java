package com.test.JavaCafe.menuitem.Dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.test.JavaCafe.DataSourceConfig;
import com.test.JavaCafe.menuitem.Command.MenuCommand;

public class MenuDaoTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
		MenuDao dao = context.getBean("menuDao", MenuDao.class);
		
		dao.viewMenu("커피");
		context.close();
	}
	
	
}
