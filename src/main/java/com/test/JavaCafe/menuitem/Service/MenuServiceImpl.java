package com.test.JavaCafe.menuitem.Service;


import java.util.List;

import com.test.JavaCafe.menuitem.Command.MenuCommand;
import com.test.JavaCafe.menuitem.Dao.MenuDao;
import com.test.JavaCafe.menuitem.Provider.CategoryProvider;

public class MenuServiceImpl implements MenuService{
	MenuDao menuDao = null;
	
	public MenuServiceImpl(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
	
	@Override
	public void addCategory(MenuCommand menu) {
		menuDao.addCategory(menu);
	}

	@Override
	public void addMenu(MenuCommand menu) {
		menuDao.addMenu(menu);
	}

	@Override
	public List<CategoryProvider> findCategory() {
		return menuDao.findCategory();
	}

	@Override
	public List<MenuCommand> viewMenu(String category) {
		return menuDao.viewMenu(category);
	}

	@Override
	public void deleteMenu(MenuCommand menu) {
		menuDao.deleteMenu(menu);
	}

	@Override
	public void deleteCategory(MenuCommand menu) {
		menuDao.deleteCategory(menu);
	}
	
	@Override
	public void deleteCategory2(MenuCommand menu) {
		menuDao.deleteCategory2(menu);
	}

}
