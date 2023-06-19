package com.khatabook.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.khatabook.entities.SupplierNetBalance;

public interface SupplierNetBalanceRepository extends CrudRepository<SupplierNetBalance, UUID>{
	 Optional<SupplierNetBalance> findBySupplierId(UUID supplierId);
	 List<SupplierNetBalance> findByUserId(UUID userId);
}

