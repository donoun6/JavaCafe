package com.test.JavaCafe.menuitem.Provider;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CategoryProvider {
	private String CategoryCode;

	@Override
	public String toString() {
		return CategoryCode;
	}
	
	
}
