package com.ecommerce.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerOrder {

	private long id;
	
	private JwtUser user;
	
	private JwtUser deliveryBy;
	
	private List<OrderTable> orders = new ArrayList();
	
	private LocalDateTime ordereDate;
	
	private LocalDateTime delivaryDate;
	
	private String delivaryAddress;
	
	private String delivaryContact;
	
	private int status; // 0-not processed 1-prcessed
	private int customerOrderStatus; //0-cancel 1-not confirmed 2-confirmed
}
