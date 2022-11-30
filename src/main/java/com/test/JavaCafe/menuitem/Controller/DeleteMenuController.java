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
@RequestMapping("/deletemenu")
public class DeleteMenuController {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	MenuService menuService = context.getBean("menuService",MenuService.class);
	
	@GetMapping
	public String deleteMenuForm(Model model) {
		model.addAttribute("menu", new MenuCommand());
		return "deleteMenu/delete_menu";
	}
	
	@ModelAttribute("categoryProviderList")
	public List<CategoryProvider> getCategoryProviderList(MenuCommand menu){
		List<CategoryProvider> list = new ArrayList<CategoryProvider>();
		menuService.findCategory().forEach(c -> list.add(new CategoryProvider(c.toString())));;
		
		return list;
	}
	
	@PostMapping
	public String deleteMenu(@ModelAttribute("menu") MenuCommand menu, Model model) {
		model.addAttribute("menu",menu);
		menuService.deleteMenu(menu);
		
		return "deleteMenu/success_delete_menu";
	}
}
