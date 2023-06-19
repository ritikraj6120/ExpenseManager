package com.khatabook.response.customernetbalance;

import java.util.UUID;

public class CustomCustomerNetBalance {
	private UUID id;
	private long netBalance;
	private UUID customerId;
	private String customername;
	
	public CustomCustomerNetBalance() {
	}
	public CustomCustomerNetBalance(UUID id, long netBalance, UUID customerId, String customername) {
		super();
		this.id = id;
		this.netBalance = netBalance;
		this.customerId = customerId;
		this.customername = customername;
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
	public UUID getCustomerId() {
		return customerId;
	}
	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	
}
