package com.khatabook.response.customertransaction;

import java.time.LocalDateTime;
import java.util.UUID;

public class CustomCustomerTransaction {
	private UUID id;
	private Long transaction;
	private Boolean isBorrow;
	private String billNo;
	private String billDetails;
	private LocalDateTime date;
	private UUID customerId;
	public CustomCustomerTransaction() {
		// TODO Auto-generated constructor stub
	}
	public CustomCustomerTransaction(UUID id, Long transaction, Boolean isBorrow, String billNo, String billDetails,
			LocalDateTime date, UUID customerId) {
		super();
		this.id = id;
		this.transaction = transaction;
		this.isBorrow = isBorrow;
		this.billNo = billNo;
		this.billDetails = billDetails;
		this.date = date;
		this.customerId = customerId;
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
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public UUID getCustomerId() {
		return customerId;
	}
	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}
	

}
