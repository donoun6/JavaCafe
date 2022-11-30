package com.test.JavaCafe.kiosk.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.JavaCafe.DataSourceConfig;
import com.test.JavaCafe.kiosk.Command.CartCommand;
import com.test.JavaCafe.kiosk.Command.CategoryCommand;
import com.test.JavaCafe.kiosk.Command.OrderCommand;
import com.test.JavaCafe.kiosk.Command.PriceCommand;
import com.test.JavaCafe.kiosk.service.KioskService;
import com.test.JavaCafe.menuitem.Command.MenuCommand;
import com.test.JavaCafe.menuitem.Provider.CategoryProvider;
import com.test.JavaCafe.menuitem.Service.MenuService;

@Controller
@RequestMapping("kiosk")
public class KioskController {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	KioskService kioskService = context.getBean("kioskService",KioskService.class);
	
	//=========================================kiosk메인===================================================
	@GetMapping
	public String kioskPage(@ModelAttribute("menu") MenuCommand menu, Model model,HttpServletRequest request){
		List<MenuCommand> menu_list = kioskService.allFindMenu();
		
		if(request.getParameter("order") != null ) {
			kioskService.addOrder();
			kioskService.deleteCartAll();
			return"redirect:/kiosk/order";
		}
		
		model.addAttribute("menu_list",menu_list);
		
		model.addAttribute("orderCount",kioskService.getOrderCount());
		
		if(menu.getCategory() == null) {
			List<MenuCommand> categorylist = kioskService.viewMenu("커피");
			model.addAttribute("categorylist",categorylist);
		}
		else {
			List<MenuCommand> categorylist = kioskService.viewMenu(menu.getCategory());
			model.addAttribute("categorylist",categorylist);
		}
		
		List<CartCommand> cartlist = kioskService.allFindCart();
		model.addAttribute("cartlist",cartlist);
		
		List<Long> countLits = new ArrayList<Long>();
		for(int i = 0; i < cartlist.size(); i++) {
			String name = cartlist.get(i).getName();
			countLits.add(kioskService.getCount(name));
		}
		model.addAttribute("countLits",countLits);
		
		List<PriceCommand> addprice = kioskService.addPrice();
		model.addAttribute("addprice",addprice);
		
		String cartName = request.getParameter("del");
		
		if (cartName != null) {
			kioskService.deleteCart(cartName);
			return"redirect:/kiosk";
		}
		
		return"kiosk/kiosk";
	}
	
	//============================================================================================
	@ModelAttribute("categoryProviderList")
	public List<CategoryCommand> getCategoryProviderList(CategoryCommand menu, Model model){
		List<CategoryCommand> category_list = kioskService.findCategory();
		model.addAttribute("category_list",category_list);

		return category_list;
	}
	
	//============================================================================================
	@ModelAttribute("categoryList")
	public List<CategoryProvider> getCategoryProviderList(MenuCommand menu){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
		MenuService menuService = context.getBean("menuService", MenuService.class);
		
		List<CategoryProvider> list = new ArrayList<CategoryProvider>();
		menuService.findCategory().forEach(c -> list.add(new CategoryProvider(c.toString())));
		
		context.close();
		return list;
	}
	
	//============================================================================================
	@PostMapping
	public String menuInfo(@ModelAttribute("cart") CartCommand cart,MenuCommand menu, Model model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		List<MenuCommand> menuInfolist = kioskService.menuInfo(menu.getName());
		
		model.addAttribute("orderCount",kioskService.getOrderCount());
		
		model.addAttribute("menuInfolist",menuInfolist);		
		menuInfolist.forEach(c -> menu.setCategory(c.getCategory()));
		
		if(menu.getName() != null){
			List<MenuCommand> categorylist = kioskService.viewMenu(menu.getCategory());
			model.addAttribute("categorylist",categorylist);
		}
		kioskService.addCart(cart);		
		
		List<CartCommand> cartlist = kioskService.allFindCart();
		model.addAttribute("cartlist",cartlist);
		
		List<Long> countLits = new ArrayList<Long>();
		for(int i = 0; i < cartlist.size(); i++) {
			String name = cartlist.get(i).getName();
			countLits.add(kioskService.getCount(name));
		}
		model.addAttribute("countLits",countLits);
		
		List<PriceCommand> addprice = kioskService.addPrice();
		model.addAttribute("addprice",addprice);
		
		return "kiosk/kiosk";
	}
	
	@GetMapping("order")
	public String orderPage(Model model,HttpServletRequest request) {
		long orderNum = kioskService.getOrderCount() + 999;
		
		model.addAttribute("orderNum",orderNum);
		
		return "kiosk/kiosk_successOrder";
	}
}
