package com.test.JavaCafe.menuitem.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Menu {
	private String category;
	private String name;
	private String price;
	
	public Menu() {
		super();
	}
	
}
