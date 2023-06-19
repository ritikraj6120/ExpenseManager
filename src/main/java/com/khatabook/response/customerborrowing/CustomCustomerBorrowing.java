package com.khatabook.response.customerborrowing;

import java.time.LocalDateTime;




public class CustomCustomerBorrowing {
	
	private Long id;
	private Long customerBorrow;
	private String billNo;
	private String billDetails;
	private LocalDateTime date;
	private Long customerId;
	
	public CustomCustomerBorrowing() {
		super();
	}

	public CustomCustomerBorrowing(Long id, Long customerBorrow, String billNo, String billDetails, LocalDateTime date,
			Long customerId) {
		super();
		this.id = id;
		this.customerBorrow = customerBorrow;
		this.billNo = billNo;
		this.billDetails = billDetails;
		this.date = date;
		this.customerId = customerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerBorrow() {
		return customerBorrow;
	}

	public void setCustomerBorrow(Long customerBorrow) {
		this.customerBorrow = customerBorrow;
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

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

}
