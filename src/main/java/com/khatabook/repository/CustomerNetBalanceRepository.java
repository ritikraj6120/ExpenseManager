package com.khatabook.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.khatabook.entities.CustomerNetBalance;

public interface CustomerNetBalanceRepository extends CrudRepository<CustomerNetBalance, UUID>{
	 Optional<CustomerNetBalance> findByCustomerId(UUID customerId);
	 List<CustomerNetBalance> findByUserId(UUID userId);
}

