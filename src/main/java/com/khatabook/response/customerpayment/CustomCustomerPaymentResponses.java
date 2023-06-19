package com.khatabook.response.customerpayment;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomCustomerPaymentResponses {

	private int status_code;
	private String messsage_tag;
	private String message;
	@JsonProperty("customerPayments")
	private ArrayList<CustomCustomerPayment> customCustomerPayments;
	
	public CustomCustomerPaymentResponses() {
		// TODO Auto-generated constructor stub
	}

	public CustomCustomerPaymentResponses(int status_code, String messsage_tag, String message,
			ArrayList<CustomCustomerPayment> customCustomerPayments) {
		super();
		this.status_code = status_code;
		this.messsage_tag = messsage_tag;
		this.message = message;
		this.customCustomerPayments = customCustomerPayments;
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

	public ArrayList<CustomCustomerPayment> getCustomCustomerPayments() {
		return customCustomerPayments;
	}

	public void setCustomCustomerPayments(ArrayList<CustomCustomerPayment> customCustomerPayments) {
		this.customCustomerPayments = customCustomerPayments;
	}
	
}
