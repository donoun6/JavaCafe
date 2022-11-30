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
@RequestMapping("/deletecategory")
public class DeleteCategoryController {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	MenuService menuService = context.getBean("menuService",MenuService.class);
	
	@GetMapping
	public String deleteCategoryForm(Model model) {
		model.addAttribute("menu", new MenuCommand());
		return "deleteCategory/delete_category";
	}
	
	@ModelAttribute("categoryProviderList")
	public List<CategoryProvider> getCategoryProviderList(MenuCommand menu){
		List<CategoryProvider> list = new ArrayList<CategoryProvider>();
		menuService.findCategory().forEach(c -> list.add(new CategoryProvider(c.toString())));;
		
		return list;
	}
	
	@PostMapping
	public String deleteCategory(@ModelAttribute("menu") MenuCommand menu, Model model) {
		model.addAttribute("menu",menu);
		
		menuService.deleteCategory(menu);
		menuService.deleteCategory2(menu);
		
		return "deleteCategory/success_delete_category";
	}
}
