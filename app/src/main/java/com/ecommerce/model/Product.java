package com.ecommerce.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

public class Product {

	private long id;
	
	private String name;
	
	private double price;
	
	private double discount_rate;
	
	private com.ecommerce.model.Category category;
	
	private com.ecommerce.model.Stock stock ;
	
	private List<com.ecommerce.model.OrderTable> orderTable = new ArrayList();
	
	private String description;
	
	private String keywords;
	
	private int defaultUnit;
	
	private LocalDateTime lastUpdated;
	
//    private byte[] image;
	
	private int status;

/*	public String getName(){
		return this.name;
	}*/
	
}
