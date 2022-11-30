package com.test.JavaCafe.menuitem.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.JavaCafe.DataSourceConfig;
import com.test.JavaCafe.menuitem.Command.MenuCommand;
import com.test.JavaCafe.menuitem.Provider.CategoryProvider;
import com.test.JavaCafe.menuitem.Service.MenuService;

@Controller
@RequestMapping("/view")
public class ViewMenuController {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	MenuService menuService = context.getBean("menuService",MenuService.class);
	
	//============================================메뉴리스트 form================================================
	@GetMapping
	public String viewMenuform(Model model) {
		model.addAttribute("menu", new MenuCommand());
		return "/viewMenu/view_menu";
	}
	
	//============================================form:select================================================
	@ModelAttribute("categoryProviderList")
	public List<CategoryProvider> getCategoryProviderList(MenuCommand menu){
		List<CategoryProvider> list = new ArrayList<CategoryProvider>();
		menuService.findCategory().forEach(c -> list.add(new CategoryProvider(c.toString())));;
		
		return list;
	}
	
	//============================================메뉴리스트================================================
	@PostMapping
	public String viewMenu(MenuCommand menu, Model model) {
		List<MenuCommand> menu_list = menuService.viewMenu(menu.getCategory());
		model.addAttribute("menu_list",menu_list);
		return "/viewMenu/success_view_menu";
	}
	
}

