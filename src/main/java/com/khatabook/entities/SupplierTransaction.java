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
public class SupplierTransaction {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
	@Type(type = "uuid-char")
	private UUID id;
	
	@ColumnDefault("0")
	private Long transaction;
	
	// isPurchase true means isBorrow false
	@NotNull	
	private boolean isPayment;
	
	private String billNo;
	
	private String billDetails;
	
	@CreationTimestamp
	@Column (nullable = false, updatable = false,columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP" )
	private LocalDateTime createdDate;
	
	@ManyToOne( optional = false)
	@JoinColumn(name = "supplier", nullable = false,referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Supplier supplier;
	
	public SupplierTransaction() {
		
	}

	public SupplierTransaction(UUID id, Long transaction, @NotNull boolean isPayment, String billNo,
			String billDetails, LocalDateTime createdDate, Supplier supplier) {
		super();
		this.id = id;
		this.transaction = transaction;
		this.isPayment = isPayment;
		this.billNo = billNo;
		this.billDetails = billDetails;
		this.createdDate = createdDate;
		this.supplier = supplier;
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

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
}
