package com.khatabook.request.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern.Flag;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khatabook.entities.User;

import lombok.Data;
@Data
public class UserRegistrationDTO {
	
	
	@JsonProperty("username")
	@NotBlank(message = "UserName can't be empty")
	@Size(min = 2, max = 100, message = "The length of UserName must be between 2 and 100 characters.")
	private String username;
	@JsonProperty("email")
	@NotBlank(message = "The email address is required.")
	@Email(message = "The email address is invalid.", flags = { Flag.CASE_INSENSITIVE })
	private String email;
	@JsonProperty("password")
	@NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	private String password;
	
	
	 public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User toUser() {
		    User user= new User();
		        user.setUsername(username);
		        user.setEmail(email);
		        user.setPassword(password);
		        return user;
		  }
	
}
