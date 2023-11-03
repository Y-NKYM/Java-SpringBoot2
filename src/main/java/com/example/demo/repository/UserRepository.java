package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

//@Repository
public interface UserRepository extends JpaRepository<User, String>{
	Optional<User> findByEmail(String email);
}
