package com.test.JavaCafe.kiosk.Command;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryCommand {
	
	private String category;
	
	public CategoryCommand(){}

	@Override
	public String toString() {
		return category;
	}
	
	
}
