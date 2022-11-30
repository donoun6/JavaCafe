package com.test.JavaCafe.menuitem.Command;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class MenuCommand {
	
	private String category;
	private String name;
	private double price;
	private String image;
	
	public MenuCommand(){}
}
