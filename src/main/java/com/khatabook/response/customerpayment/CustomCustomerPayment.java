package com.khatabook.response.customerpayment;

import java.time.LocalDateTime;
import java.util.UUID;

public class CustomCustomerPayment {

	private UUID id;
	private UUID customerPay;
	private String billNo;
	private String billDetails;
	private LocalDateTime date;
	private UUID customerId;
	
	public CustomCustomerPayment() {
		// TODO Auto-generated constructor stub
	}

	public CustomCustomerPayment(UUID id, UUID customerPay, String billNo, String billDetails, LocalDateTime date,
			UUID customerId) {
		super();
		this.id = id;
		this.customerPay = customerPay;
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

	public UUID getCustomerPay() {
		return customerPay;
	}

	public void setCustomerPay(UUID customerPay) {
		this.customerPay = customerPay;
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
