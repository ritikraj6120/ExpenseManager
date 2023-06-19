
package com.khatabook.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

@Entity
public class SupplierNetBalance {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
	@Type(type = "uuid-char")
	private UUID id;
	
	@ColumnDefault("0")
	private long netBalance;
	
	@ManyToOne( optional = false)
	@JoinColumn(name = "user", nullable = false,referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@OneToOne( optional = false)
	@JoinColumn(name = "supplier", nullable = false,referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Supplier supplier;

	public SupplierNetBalance() {
	}

	public SupplierNetBalance(UUID id, long netBalance, User user, Supplier supplier) {
		this.id = id;
		this.netBalance = netBalance;
		this.user = user;
		this.supplier = supplier;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}
