package com.test.JavaCafe.menuitem.Controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.JavaCafe.DataSourceConfig;
import com.test.JavaCafe.menuitem.Command.MenuCommand;
import com.test.JavaCafe.menuitem.Service.MenuService;

@Controller
@RequestMapping("/addcategory")
public class AddCategoryController {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	MenuService menuService = context.getBean("menuService", MenuService.class);
	
	//============================================카테고리 추가 form================================================
	@GetMapping
	public String addCategoryForm(Model model) {
		model.addAttribute("menu", new MenuCommand());
		return "addCategory/add_category";
	}
	
	//============================================카테고리 추가================================================
	@PostMapping
	public String addCategory(@ModelAttribute("menu") MenuCommand menu, Model model) {
		model.addAttribute("menu",menu);
		
		menuService.addCategory(menu);
		context.close();
		return "addCategory/success_add_category";
	}

}
