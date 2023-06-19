package com.khatabook.response.suppliernetbalance;

import java.util.UUID;

public class CustomSupplierNetBalance {
	private UUID id;
	private long netBalance;
	private UUID supplierId;
	private String suppliername;
	
	public CustomSupplierNetBalance() {
	}

	public CustomSupplierNetBalance(UUID id, long netBalance, UUID supplierId, String suppliername) {
		super();
		this.id = id;
		this.netBalance = netBalance;
		this.supplierId = supplierId;
		this.suppliername = suppliername;
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

	public UUID getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(UUID supplierId) {
		this.supplierId = supplierId;
	}

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	
}
