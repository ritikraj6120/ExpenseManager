package com.khatabook.request.suppliertransaction;

import java.time.LocalDateTime;

import javax.annotation.Nullable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khatabook.entities.SupplierTransaction;

public class UpdateSupplierTransactionDTO {
	@JsonProperty("transaction_amount")
	@Nullable
	@Min(value = 1, message = "Transaction Amount must be greater than 0")
    @Max(value = Long.MAX_VALUE, message = "Transaction Amount exceeds maximum value")
	private Long transaction;
	
	@JsonProperty("isPayment")
	@NotNull
	private boolean isPayment;
	
	@JsonProperty("bill_details")
	private String billNo;
	
	@JsonProperty("bill_no")
	private String billDetails;
	
	@JsonProperty("date")
	@Nullable
    private LocalDateTime transactionDate;
	
	public UpdateSupplierTransactionDTO() {
	}
	

	public UpdateSupplierTransactionDTO(
			@Min(value = 1, message = "Transaction Amount must be greater than 0") @Max(value = Long.MAX_VALUE, message = "Transaction Amount exceeds maximum value") Long transaction,
			@NotNull boolean isPayment, String billNo, String billDetails, LocalDateTime transactionDate) {
		super();
		this.transaction = transaction;
		this.isPayment = isPayment;
		this.billNo = billNo;
		this.billDetails = billDetails;
		this.transactionDate = transactionDate;
	}

	public Long getTransaction() {
		return transaction;
	}


	public void setTransaction(Long transaction) {
		this.transaction = transaction;
	}


	public boolean isPayment() {
		return isPayment;
	}


	public void setPayment(boolean isPayment) {
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


	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}


	public SupplierTransaction getSupplierTransaction()
	{
		SupplierTransaction supplierTransaction = new SupplierTransaction();
		supplierTransaction.setTransaction(transaction);
		supplierTransaction.setBillNo(billNo);
		supplierTransaction.setBillDetails(billDetails);
		supplierTransaction.setCreatedDate(transactionDate);
		return supplierTransaction;
 	}
}
