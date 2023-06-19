package com.khatabook.response.customerpayment;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomCustomerPaymentResponse {

	private int status_code;
	private String messsage_tag;
	private String message;
	
	@JsonProperty("customerPayment")
	private CustomCustomerPayment customCustomerPayment;
	
	public CustomCustomerPaymentResponse() {
		// TODO Auto-generated constructor stub
	}

	public CustomCustomerPaymentResponse(int status_code, String messsage_tag, String message,
			CustomCustomerPayment customCustomerPayment) {
		super();
		this.status_code = status_code;
		this.messsage_tag = messsage_tag;
		this.message = message;
		this.customCustomerPayment = customCustomerPayment;
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

	public CustomCustomerPayment getCustomCustomerPayment() {
		return customCustomerPayment;
	}

	public void setCustomCustomerPayment(CustomCustomerPayment customCustomerPayment) {
		this.customCustomerPayment = customCustomerPayment;
	}
	

}
