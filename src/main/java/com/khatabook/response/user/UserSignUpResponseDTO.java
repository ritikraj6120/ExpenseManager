package com.khatabook.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserSignUpResponseDTO {

	private int status_code;
	private String message_tag;
	private String message;
	@JsonProperty("user")
	private SignedUpUser customUser;
	public UserSignUpResponseDTO() {}
	public UserSignUpResponseDTO(int status_code, String message_tag, String message, SignedUpUser customUser) {
		super();
		this.status_code = status_code;
		this.message_tag = message_tag;
		this.message = message;
		this.customUser = customUser;
	}
	
	public int getStatus_code() {
		return status_code;
	}
	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}
	public String getMessage_tag() {
		return message_tag;
	}
	public void setMessage_tag(String message_tag) {
		this.message_tag = message_tag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public SignedUpUser getCustomUser() {
		return customUser;
	}
	public void setCustomUser(SignedUpUser customUser) {
		this.customUser = customUser;
	}
	
	

}
