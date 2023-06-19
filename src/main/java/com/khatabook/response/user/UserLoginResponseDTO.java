package com.khatabook.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLoginResponseDTO {
	private int status_code;
	private String messsage_tag;
	private String message;
	@JsonProperty("user")
	private LoggedInUser loggedInUser;
	public UserLoginResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserLoginResponseDTO(int status_code, String messsage_tag, String message, LoggedInUser loggedInUser) {
		super();
		this.status_code = status_code;
		this.messsage_tag = messsage_tag;
		this.message = message;
		this.loggedInUser = loggedInUser;
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

	public LoggedInUser getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(LoggedInUser loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	

}
