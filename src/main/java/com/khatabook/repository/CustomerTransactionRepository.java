package com.khatabook.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.khatabook.entities.CustomerTransaction;

public interface CustomerTransactionRepository extends CrudRepository<CustomerTransaction, UUID>{
	 List<CustomerTransaction> findByCustomerId(UUID customerId);
}
