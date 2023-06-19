package com.khatabook.response.customerborrowing;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomCustomerBorrowingResponses {

	private int status_code;
	private String messsage_tag;
	private String message;
	@JsonProperty("customerBorrowings")
	private ArrayList<CustomCustomerBorrowing> customCustomerBorrowings;
	
	public CustomCustomerBorrowingResponses() {
		// TODO Auto-generated constructor stub
	}

	public CustomCustomerBorrowingResponses(int status_code, String messsage_tag, String message,
			ArrayList<CustomCustomerBorrowing> customCustomerBorrowings) {
		super();
		this.status_code = status_code;
		this.messsage_tag = messsage_tag;
		this.message = message;
		this.customCustomerBorrowings = customCustomerBorrowings;
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

	public ArrayList<CustomCustomerBorrowing> getCustomCustomerBorrowings() {
		return customCustomerBorrowings;
	}

	public void setCustomCustomerBorrowings(ArrayList<CustomCustomerBorrowing> customCustomerBorrowings) {
		this.customCustomerBorrowings = customCustomerBorrowings;
	}
	
}
