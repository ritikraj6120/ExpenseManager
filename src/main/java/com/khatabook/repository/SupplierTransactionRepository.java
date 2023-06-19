package com.khatabook.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.khatabook.entities.SupplierTransaction;

public interface SupplierTransactionRepository extends CrudRepository<SupplierTransaction, UUID>{
	 List<SupplierTransaction> findBySupplierId(UUID supplierId);
}
