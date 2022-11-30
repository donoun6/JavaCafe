package com.test.JavaCafe.menuitem.Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
@RequestMapping("/addmenu")

public class AddMenuController {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	MenuService menuService = context.getBean("menuService", MenuService.class);
	
	//============================================메뉴 추가 form================================================
	@GetMapping
	public String addMenuForm(Model model) {
		model.addAttribute("menu", new MenuCommand());
		return "addMenu/add_menu";
	}
	
	//============================================form:select================================================
	@ModelAttribute("categoryProviderList")
	public List<CategoryProvider> getCategoryProviderList(MenuCommand menu){
		List<CategoryProvider> list = new ArrayList<CategoryProvider>();
		menuService.findCategory().forEach(c -> list.add(new CategoryProvider(c.toString())));
		
		return list;
	}

	//============================================메뉴 추가================================================
	@PostMapping
	public String addMenu(@ModelAttribute("menu") MenuCommand menu, Model model) {
		model.addAttribute("menu", menu);
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		int totalBytes = 0;
		try {
			try {
				fis = new FileInputStream("C:\\Users\\Administrator\\Desktop\\cafe_Img\\"+menu.getImage());
				fos = new FileOutputStream("C:\\NCSDongHwan\\JavaCafe\\src\\main\\webapp\\resources\\images\\"+menu.getImage());
				for(int readByte; (readByte = fis.read()) != -1;) {
					fos.write(readByte);
					totalBytes++;
				}
			}finally {
				
			}
		} catch (IOException e) { //checked exception 이다.
			e.printStackTrace();
		}
		
		menuService.addMenu(menu);
		context.close();
		return "addMenu/success_add_menu";
	}
	
}
