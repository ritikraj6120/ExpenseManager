package com.khatabook.response.supplier;


import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomSupplierResponse {
	private int status_code;
	private String messsage_tag;
	private String message;
	@JsonProperty("supplier")
	private CustomSupplier customSupplier;
	
	public CustomSupplierResponse() {
		super();
	}

	public CustomSupplierResponse(int status_code, String messsage_tag, String message, CustomSupplier customSupplier) {
		super();
		this.status_code = status_code;
		this.messsage_tag = messsage_tag;
		this.message = message;
		this.customSupplier = customSupplier;
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

	public CustomSupplier getCustomSupplier() {
		return customSupplier;
	}

	public void setCustomSupplier(CustomSupplier customSupplier) {
		this.customSupplier = customSupplier;
	}
	
}
