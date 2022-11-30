package com.test.JavaCafe.kiosk.Command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString

public class CartCommand {
	private String name;
	private double price;
	private double count;
	
	public CartCommand() {
		super();
	}
	
	
}
