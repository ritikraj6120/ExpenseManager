package com.khatabook.response.user;

public class LoggedInUser {
	private String userName;
	private String email;
	private String token;
	public LoggedInUser() {
		// TODO Auto-generated constructor stub
	}

	public LoggedInUser(String userName, String email, String token) {
		this.userName = userName;
		this.email = email;
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
