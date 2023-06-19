package com.khatabook.response.suppliernetbalance;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomSupplierNetBalanceResponses {
	private int status_code;
	private String messsage_tag;
	private String message;
	@JsonProperty("customers_netbalance")
	private List<CustomSupplierNetBalance> customCustomersNetBalance;
	public CustomSupplierNetBalanceResponses() {
	}
	public CustomSupplierNetBalanceResponses(int status_code, String messsage_tag, String message,
			List<CustomSupplierNetBalance> customCustomersNetBalance) {
		super();
		this.status_code = status_code;
		this.messsage_tag = messsage_tag;
		this.message = message;
		this.customCustomersNetBalance = customCustomersNetBalance;
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
	public List<CustomSupplierNetBalance> getCustomCustomersNetBalance() {
		return customCustomersNetBalance;
	}
	public void setCustomCustomersNetBalance(List<CustomSupplierNetBalance> customCustomersNetBalance) {
		this.customCustomersNetBalance = customCustomersNetBalance;
	}
}