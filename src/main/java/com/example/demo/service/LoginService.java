package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	private final UserRepository repository;
	
//	public Optional<User> searchUserById(String loginId){
//		return repository.findById(loginId);
//	}
	public Optional<User> searchUserByEmail(String email){
		return repository.findByEmail(email);
	}
}
