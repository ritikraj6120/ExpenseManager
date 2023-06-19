package com.khatabook.response.suppliertransaction;

import java.time.LocalDateTime;
import java.util.UUID;

public class CustomSupplierTransaction {
	private UUID id;
	private Long transaction;
	private Boolean isPayment;
	private String billNo;
	private String billDetails;
	private LocalDateTime date;
	private UUID customerId;
	public CustomSupplierTransaction() {
	}
	
	public CustomSupplierTransaction(UUID id, Long transaction, Boolean isPayment, String billNo, String billDetails,
			LocalDateTime date, UUID customerId) {
		super();
		this.id = id;
		this.transaction = transaction;
		this.isPayment = isPayment;
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
	
	public Boolean isPayment() {
		return isPayment;
	}
	public void setPayment(Boolean isPayment) {
		this.isPayment = isPayment;
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
