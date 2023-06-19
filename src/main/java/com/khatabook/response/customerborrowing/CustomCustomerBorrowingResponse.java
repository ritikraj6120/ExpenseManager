package com.khatabook.response.customerborrowing;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomCustomerBorrowingResponse {
	private int status_code;
	private String messsage_tag;
	private String message;
	
	@JsonProperty("customerBorrowing")
	private CustomCustomerBorrowing customCustomerBorrowing;
	
	public CustomCustomerBorrowingResponse() {
		super();
	}

	public CustomCustomerBorrowingResponse(int status_code, String messsage_tag, String message,
			CustomCustomerBorrowing customCustomerBorrowing) {
		super();
		this.status_code = status_code;
		this.messsage_tag = messsage_tag;
		this.message = message;
		this.customCustomerBorrowing = customCustomerBorrowing;
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

	public CustomCustomerBorrowing getCustomCustomerBorrowing() {
		return customCustomerBorrowing;
	}

	public void setCustomCustomerBorrowing(CustomCustomerBorrowing customCustomerBorrowing) {
		this.customCustomerBorrowing = customCustomerBorrowing;
	}

}
