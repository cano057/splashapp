package com.ecommerce.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderTable {

	private long id;
	
	private CustomerOrder customerOrder;
	
	private Product product;
	
	private int quantity;
	
	private double price;
	
	private int status;
}
