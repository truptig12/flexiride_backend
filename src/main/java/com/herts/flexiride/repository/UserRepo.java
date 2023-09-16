package com.herts.flexiride.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herts.flexiride.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

//	User findByEmail(String email);
	
	Optional<User> findByEmail(String email);
}
