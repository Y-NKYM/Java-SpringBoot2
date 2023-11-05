package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.form.RegisterForm;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterService {
	private final UserRepository repository;
	
	public User registerUser(RegisterForm form){
		var user = new User();
		user.setEmail(form.getEmail());
		user.setPassword(form.getPassword());
		
		return repository.save(user);
	}
}
