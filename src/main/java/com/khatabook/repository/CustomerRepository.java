package com.khatabook.repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.khatabook.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, UUID>{
	public ArrayList<Customer> findByUserIdAndPhone(UUID userId,String phone);
	public Optional<Customer> findByPhone(String phone);
	public ArrayList<Customer> findByUserId(UUID userId);
}
