package com.test.JavaCafe.kiosk.service;

import java.util.List;

import com.test.JavaCafe.kiosk.Command.CartCommand;
import com.test.JavaCafe.kiosk.Command.CategoryCommand;
import com.test.JavaCafe.kiosk.Command.OrderCommand;
import com.test.JavaCafe.kiosk.Command.PriceCommand;
import com.test.JavaCafe.kiosk.Dao.KioskDao;
import com.test.JavaCafe.menuitem.Command.MenuCommand;

public class KioskServiceImpl implements KioskService{
	KioskDao kioskDao = null;
	
	public KioskServiceImpl(KioskDao kioskDao) {
		this.kioskDao = kioskDao;
	}
	
	@Override
	public List<MenuCommand> allFindMenu() {
		return kioskDao.allFindMenu();
	}

	@Override
	public List<CategoryCommand> findCategory() {
		return kioskDao.findCategory();
	}

	@Override
	public List<MenuCommand> viewMenu(String category) {
		return kioskDao.viewMenu(category);
	}

	@Override
	public List<MenuCommand> menuInfo(String name) {
		return kioskDao.menuInfo(name);
	}

	@Override
	public void addCart(CartCommand cart) {
		kioskDao.addCart(cart);
	}

	@Override
	public List<CartCommand> allFindCart() {
		return kioskDao.allFindCart();
	}

	@Override
	public long getCount(String name) {
		return kioskDao.getCount(name);
	}
	
	@Override
	public long getOrderCount() {
		return kioskDao.getOrderCount();
	}
	
	@Override
	public List<PriceCommand> addPrice() {
		return kioskDao.addPrice();
	}

	@Override
	public void deleteCart(String name) {
		kioskDao.deleteCart(name);
	}

	@Override
	public void addOrder() {
		kioskDao.addOrder();
	}

	@Override
	public void deleteCartAll() {
		kioskDao.deleteCartAll();
	}

	@Override
	public List<OrderCommand> allFindOrder(long orderNum) {
		return kioskDao.allFindOrder(orderNum);
	}

}
