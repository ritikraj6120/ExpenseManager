package com.khatabook.request.customertransaction;

import java.time.LocalDateTime;

import javax.annotation.Nullable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khatabook.entities.CustomerTransaction;

public class UpdateCustomerTransactionDTO {
	@JsonProperty("transaction_amount")
	@Nullable
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
	@Nullable
    private LocalDateTime transactionDate;
	
	public UpdateCustomerTransactionDTO() {
	}
	
	public UpdateCustomerTransactionDTO(
			@Min(value = 1, message = "Transaction Amount must be greater than 0") @Max(value = Long.MAX_VALUE, message = "Transaction Amount exceeds maximum value") Long transaction,
			@NotNull boolean isBorrow, String billNo, String billDetails, LocalDateTime transactionDate) {
		super();
		this.transaction = transaction;
		this.isBorrow = isBorrow;
		this.billNo = billNo;
		this.billDetails = billDetails;
		this.transactionDate = transactionDate;
	}

	public boolean isBorrow() {
		return isBorrow;
	}
	
	public void setBorrow(boolean isBorrow) {
		this.isBorrow = isBorrow;
	}
	public Long getTransaction() {
		return transaction;
	}

	public void setTransaction(Long transaction) {
		this.transaction = transaction;
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
		customerTransaction.setBillNo(billNo);
		customerTransaction.setBillDetails(billDetails);
		customerTransaction.setDate(transactionDate);
		return customerTransaction;
 	}
}
