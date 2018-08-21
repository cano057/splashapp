package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {

	private long id;
	
	private String category_name;
	
	private int status;
	
	private List<Product> products = new ArrayList();
}
