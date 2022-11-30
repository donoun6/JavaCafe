package com.test.JavaCafe.menuitem.Service;


import java.util.List;

import com.test.JavaCafe.menuitem.Command.MenuCommand;
import com.test.JavaCafe.menuitem.Provider.CategoryProvider;

public interface MenuService {
	
	void addCategory(MenuCommand menu);
	
	void addMenu(MenuCommand menu);
	
	List<CategoryProvider> findCategory();
	
	List<MenuCommand> viewMenu(String category);
	
	void deleteMenu(MenuCommand menu);
	
	void deleteCategory(MenuCommand menu);
	
	void deleteCategory2(MenuCommand menu);
	
}
