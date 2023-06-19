package com.khatabook.response.customertransaction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomCustomerTransactionResponse {

	private int status_code;
	private String messsage_tag;
	private String message;
	@JsonProperty("customer_transaction")
	private CustomCustomerTransaction customCustomerTransaction;
	
	public CustomCustomerTransactionResponse() {
		// TODO Auto-generated constructor stub
	}

	public CustomCustomerTransactionResponse(int status_code, String messsage_tag, String message,
			CustomCustomerTransaction customCustomerTransaction) {
		super();
		this.status_code = status_code;
		this.messsage_tag = messsage_tag;
		this.message = message;
		this.customCustomerTransaction = customCustomerTransaction;
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

	@JsonProperty("customer_transaction")
	public CustomCustomerTransaction getCustomCustomerTransaction() {
		return customCustomerTransaction;
	}

	public void setCustomCustomerTransaction(CustomCustomerTransaction customCustomerTransaction) {
		this.customCustomerTransaction = customCustomerTransaction;
	}
	
}
