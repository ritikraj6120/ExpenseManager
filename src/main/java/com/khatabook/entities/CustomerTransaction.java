package com.khatabook.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

@Entity
public class CustomerTransaction {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
	@Type(type = "uuid-char")
	private UUID id;
	
	@ColumnDefault("0")
	private Long transaction;
	
	@NotNull
	private boolean isBorrow;
	
	private String billNo;
	
	private String billDetails;
	
	@CreationTimestamp
	@Column (nullable = false, updatable = false,columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP" )
	private LocalDateTime createdDate;
	
	@ManyToOne( optional = false)
	@JoinColumn(name = "customer", nullable = false,referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Customer customer;
	
	public CustomerTransaction() {
		// TODO Auto-generated constructor stub
	}

	public CustomerTransaction(UUID id, Long transaction, @NotNull Boolean isBorrow, String billNo, String billDetails,
			LocalDateTime date, Customer customer) {
		super();
		this.id = id;
		this.transaction = transaction;
		this.isBorrow = isBorrow;
		this.billNo = billNo;
		this.billDetails = billDetails;
		this.createdDate = date;
		this.customer = customer;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Long getTransaction() {
		return transaction;
	}

	public void setTransaction(Long transaction) {
		this.transaction = transaction;
	}

	public Boolean getIsBorrow() {
		return isBorrow;
	}

	public void setIsBorrow(Boolean isBorrow) {
		this.isBorrow = isBorrow;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getBillDetails() {
		return billDetails;
	}

	public void setBillDetails(String billDetails) {
		this.billDetails = billDetails;
	}

	public LocalDateTime getDate() {
		return createdDate;
	}

	public void setDate(LocalDateTime date) {
		this.createdDate = date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
