package com.khatabook.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

@Entity
public class CustomerNetBalance {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
	@Type(type = "uuid-char")
	private UUID id;
	
	@ColumnDefault("0")
	private long netBalance;
	
	@ManyToOne( optional = false)
	@JoinColumn(name = "user", nullable = false,referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@OneToOne( optional = false)
	@JoinColumn(name = "customer", nullable = false,referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Customer customer;

	
	public CustomerNetBalance(UUID id, long netBalance,  User user, Customer customer) {
		super();
		this.id = id;
		this.netBalance = netBalance;
		this.user = user;
		this.customer = customer;
	}
	
	public CustomerNetBalance() {
		super();
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public long getNetBalance() {
		return netBalance;
	}

	public void setNetBalance(long netBalance) {
		this.netBalance = netBalance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
