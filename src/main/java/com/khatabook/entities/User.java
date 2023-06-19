package com.khatabook.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public class User{
	 @Id
	 @GeneratedValue(generator = "uuid2")
	 @GenericGenerator(name = "uuid2", strategy = "uuid2")
	 @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
	 @Type(type = "uuid-char")
	private UUID id;
	@Column(columnDefinition = "varchar(255)", nullable=false)
	private String username;
	@Column(nullable=false,unique=true)
	private String email;
	@Column(nullable = false)
	private String password;
	@CreationTimestamp
	@Column (nullable = false, updatable = false,columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP" )
	private LocalDateTime createdDate;
	
	public User() {
		
	}
	public User(UUID id, String username, String email, String password, LocalDateTime createdDate) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.createdDate = createdDate;
	}
	
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
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
	public LocalDateTime getDate() {
		return createdDate;
	}
	public void setDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", date="
				+ createdDate + ", getId()=" + getId() + ", getUsername()=" + getUsername() + ", getEmail()=" + getEmail()
				+ ", getPassword()=" + getPassword() + ", getDate()=" + getDate() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}