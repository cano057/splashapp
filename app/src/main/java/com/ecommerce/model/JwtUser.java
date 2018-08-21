package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtUser {

	private long id;
	
	private List<com.ecommerce.model.CustomerOrder> coustomerOrder= new ArrayList<>();
	
	private  List<com.ecommerce.model.CustomerOrder> customerOrderDeliveryBy= new ArrayList<>();
	
	private String email;
	
	private String fullName;
	
	private String password;
	
	private String address;
	
	private String contactNumber;
	
	private String alternetContactNumber;

	private String role;

	private int status;

	private String verification;

	//public JwtUser(id, coustomerOrder, customerOrderDeliveryBy ,email, fullName, password, address, contactNumber, alternetContactNumber, role, status, verification) {
	public JwtUser(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
}