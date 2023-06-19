package com.khatabook.response.user;

public class LoggedInUser {
	private String userName;
	private String email;
//	private String jwt;
	public LoggedInUser() {
		// TODO Auto-generated constructor stub
	}
	public LoggedInUser(String userName, String email) {
		super();
		this.userName = userName;
		this.email = email;
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
	

}
