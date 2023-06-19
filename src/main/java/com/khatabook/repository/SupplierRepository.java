package com.khatabook.repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.khatabook.entities.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, UUID>{
	public ArrayList<Supplier> findByUserIdAndPhone(UUID userId,String phone);
	public Optional<Supplier> findByPhone(String phone);
	public ArrayList<Supplier> findByUserId(UUID userId);
}
