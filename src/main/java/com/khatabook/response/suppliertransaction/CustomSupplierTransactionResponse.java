package com.khatabook.response.suppliertransaction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomSupplierTransactionResponse {

	private int status_code;
	private String messsage_tag;
	private String message;
	@JsonProperty("supplier_transaction")
	private CustomSupplierTransaction customSupplierTransaction;
	public CustomSupplierTransactionResponse() {
	}
	
	public CustomSupplierTransactionResponse(int status_code, String messsage_tag, String message,
			CustomSupplierTransaction customSupplierTransaction) {
		super();
		this.status_code = status_code;
		this.messsage_tag = messsage_tag;
		this.message = message;
		this.customSupplierTransaction = customSupplierTransaction;
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
	public CustomSupplierTransaction getCustomSupplierTransaction() {
		return customSupplierTransaction;
	}
	public void setCustomSupplierTransaction(CustomSupplierTransaction customSupplierTransaction) {
		this.customSupplierTransaction = customSupplierTransaction;
	}
	
}
