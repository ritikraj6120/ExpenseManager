package com.khatabook.request.customertransaction;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.khatabook.entities.CustomerTransaction;

public class AddCustomerTransactionDTO {
	@JsonProperty("transaction_amount")
	@NotNull(message="Transaction Amount can't be null")
	@Min(value = 1, message = "Transaction Amount must be greater than 0")
    @Max(value = Long.MAX_VALUE, message = "Transaction Amount exceeds maximum value")
	private Long transaction;
	
	@JsonProperty("isBorrowed")
	@NotNull
	private boolean isBorrow;
	
	@JsonProperty("bill_details")
	private String billNo;
	@JsonProperty("bill_no")
	private String billDetails;
	@JsonProperty("date")
    @NotNull(message="Transaction date cannot be null")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transactionDate;
	
	
	public AddCustomerTransactionDTO(
			@NotNull(message = "Transaction Amount can't be null") @Min(value = 1, message = "Transaction Amount must be greater than 0") @Max(value =  Long.MAX_VALUE, message = "Transaction Amount exceeds maximum value") Long transaction,
			@NotNull boolean isBorrow, String billNo, String billDetails,
			@NotNull(message = "Transaction date cannot be null") LocalDateTime transactionDate) {
		this.transaction = transaction;
		this.isBorrow = isBorrow;
		this.billNo = billNo;
		this.billDetails = billDetails;
		this.transactionDate = transactionDate;
	}

	public AddCustomerTransactionDTO() {
	}
	
	public Long getTransaction() {
		return transaction;
	}

	public void setTransaction(Long transaction) {
		this.transaction = transaction;
	}

	public boolean isBorrow() {
		return isBorrow;
	}

	public void setBorrow(boolean isBorrow) {
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

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public CustomerTransaction getCustomerTransaction()
	{
		CustomerTransaction customerTransaction = new CustomerTransaction();
		customerTransaction.setTransaction(transaction);
		customerTransaction.setIsBorrow(isBorrow);
		customerTransaction.setBillNo(billNo);
		customerTransaction.setBillDetails(billDetails);
		customerTransaction.setDate(transactionDate);
		return customerTransaction;
 	}
	
}
