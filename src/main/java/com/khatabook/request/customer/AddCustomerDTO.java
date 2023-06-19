package com.khatabook.request.customer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddCustomerDTO {
	@JsonProperty("name")
	@NotNull(message = "Customer name is required.")
//	@Size(min = 1, max = 25, message = "Customer name length can't exceed 25 characters")
	private String name;
	@JsonProperty("phone")
	@NotBlank(message = "Phone number is required")
	@Pattern(regexp="^\\+91\\d{10}$",message="phone number must start with +91 and 10 digits long")
	private String phone;
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
	public AddCustomerDTO() {
	} 
}

