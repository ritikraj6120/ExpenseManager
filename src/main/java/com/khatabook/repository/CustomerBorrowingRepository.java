package com.khatabook.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.khatabook.entities.CustomerBorrowing;

public interface CustomerBorrowingRepository extends CrudRepository<CustomerBorrowing, UUID>{
	 Optional<CustomerBorrowing> findByCustomerId(UUID customerId);
}

