package com.khatabook.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;



@Entity
public class Customer {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
	@Type(type = "uuid-char")
	private UUID id;
	@Column(nullable=false,length=25)
    @Size(min = 1, max = 25)
	private String name;
	
	@Column(nullable=false,unique = true)
	private String phone;
	
	@CreationTimestamp
	@Column (nullable = false, updatable = false,columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP" )
	private LocalDateTime createdDate;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "user", nullable = false,referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	
	public Customer(UUID id, String name, String phone,LocalDateTime date,User user) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.createdDate = date;
		this.user = user;
	}
	
	public Customer() {
		
	}

	public UUID getId() {
		return id;	
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDateTime getDate() {
		return createdDate;
	}

	public void setDate(LocalDateTime date) {
		this.createdDate = date;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Customers [id=" + id + ", name=" + name + ", phone=" + phone + ", date=" + createdDate + ", user=" + user
				+ "]";
	}
}

