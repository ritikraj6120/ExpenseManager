package com.khatabook.response.customer;

import java.util.UUID;

public class CustomCustomer {
	private UUID id;
    private String name;
    private String phone;
    private UUID userId;
    
	public CustomCustomer() {
		super();
	}

	public CustomCustomer(UUID id, String name, String phone, UUID userId) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.userId = userId;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	
}
