package com.khatabook.response.customertransaction;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomCustomerTransactionResponses {
	
	private int status_code;
	private String messsage_tag;
	private String message;
	@JsonProperty("customer_transactions")
	private ArrayList<CustomCustomerTransaction> customCustomerTransactions;
	
	
	public CustomCustomerTransactionResponses() {
		// TODO Auto-generated constructor stub
	}


	public CustomCustomerTransactionResponses(int status_code, String messsage_tag, String message,
			ArrayList<CustomCustomerTransaction> customCustomerTransactions) {
		super();
		this.status_code = status_code;
		this.messsage_tag = messsage_tag;
		this.message = message;
		this.customCustomerTransactions = customCustomerTransactions;
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


	public ArrayList<CustomCustomerTransaction> getCustomCustomerTransactions() {
		return customCustomerTransactions;
	}


	public void setCustomCustomerTransactions(ArrayList<CustomCustomerTransaction> customCustomerTransactions) {
		this.customCustomerTransactions = customCustomerTransactions;
	}


}
