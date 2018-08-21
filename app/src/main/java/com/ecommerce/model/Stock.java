package com.ecommerce.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class Stock implements Serializable{

	private long id;
	
//	@JsonManagedReference
	private Product product;
	
	private int minimunStock;
	
	private int quantity;
	
	private LocalDateTime lastUpdated;
}
