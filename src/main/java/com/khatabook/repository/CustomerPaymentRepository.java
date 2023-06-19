package com.khatabook.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.khatabook.entities.CustomerPayment;

public interface CustomerPaymentRepository extends CrudRepository<CustomerPayment, UUID>{
	 Optional<CustomerPayment> findByCustomerId(UUID customerId);
}

