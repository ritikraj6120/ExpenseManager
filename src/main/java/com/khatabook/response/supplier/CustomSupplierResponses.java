package com.khatabook.response.supplier;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonPropertyOrder({ "status_code", "message_tag", "message", "customers" })
public class CustomSupplierResponses {
	private int status_code;
	private String messsage_tag;
	private String message;
	@JsonProperty("suppliers")
	private ArrayList<CustomSupplier> customSuppliers;
	public CustomSupplierResponses() {
		
	}
	public CustomSupplierResponses(int status_code, String messsage_tag, String message,
			ArrayList<CustomSupplier> customSuppliers) {
		super();
		this.status_code = status_code;
		this.messsage_tag = messsage_tag;
		this.message = message;
		this.customSuppliers = customSuppliers;
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
	public ArrayList<CustomSupplier> getCustomSuppliers() {
		return customSuppliers;
	}
	public void setCustomSuppliers(ArrayList<CustomSupplier> customSuppliers) {
		this.customSuppliers = customSuppliers;
	}
}
