package com.khatabook.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.khatabook.entities.User;

public interface UserRepository extends CrudRepository<User, UUID>{

	public Optional<User> findByEmail(String email);
	
}
