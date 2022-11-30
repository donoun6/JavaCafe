package com.test.JavaCafe.kiosk.Command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class OrderCommand {
	private long orderNum;
	private String name;
	private double price;
	public OrderCommand() {
	}
}
