package com.khatabook.response.customer;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonPropertyOrder({ "status_code", "message_tag", "message", "customers" })
public class CustomCustomerResponses {
	private int status_code;
	private String messsage_tag;
	private String message;
	@JsonProperty("customers")
	private ArrayList<CustomCustomer> customCustomers;
	
	public CustomCustomerResponses() {
		super();
	}
	
	public CustomCustomerResponses(int status_code, String messsage_tag, String message, ArrayList<CustomCustomer> 
	customCustomers) {
		super();
		this.status_code = status_code;
		this.messsage_tag = messsage_tag;
		this.message = message;
		this.customCustomers = customCustomers;
	}
	
	

	public int getStatus_code() {
		return status_code;
	}
	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}
	public String getMesssage_tag() {
		return messsage_tag;
	}
	public void setMesssage_tag(String messsage_tag) {
		this.messsage_tag = messsage_tag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@JsonProperty("customers") 
	public ArrayList<CustomCustomer> getCustomCustomer() {
		return customCustomers;
	}
	public void setCustomCustomer(ArrayList<CustomCustomer> customCustomers) {
		this.customCustomers= customCustomers;
	}
	
}
