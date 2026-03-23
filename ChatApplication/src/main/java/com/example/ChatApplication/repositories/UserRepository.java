package com.example.ChatApplication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ChatApplication.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User>  findByUsername(String username);
	Optional<User>  findByEmail(String email);
	
}
