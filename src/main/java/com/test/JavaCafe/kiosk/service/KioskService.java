package com.test.JavaCafe.kiosk.service;

import java.util.List;

import com.test.JavaCafe.kiosk.Command.CartCommand;
import com.test.JavaCafe.kiosk.Command.CategoryCommand;
import com.test.JavaCafe.kiosk.Command.OrderCommand;
import com.test.JavaCafe.kiosk.Command.PriceCommand;
import com.test.JavaCafe.menuitem.Command.MenuCommand;

public interface KioskService {
	
	public List<MenuCommand> allFindMenu();
	
	public List<CategoryCommand> findCategory();
	
	public List<MenuCommand> viewMenu(String category);
	
	public List<MenuCommand> menuInfo(String name);
	
	public void addCart(CartCommand menu);
	
	public List<CartCommand> allFindCart();
	
	public long getCount(String name);
	
	public long getOrderCount();
	
	public List<PriceCommand> addPrice();
	
	public void deleteCart(String name);
	
	public void addOrder();
	
	public void deleteCartAll();
	
	public List<OrderCommand> allFindOrder(long orderNum);
}
