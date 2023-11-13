package com.example.demo.service;

import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.form.RegisterForm;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterService {
	private final UserRepository repository;
	
	/** Dozerの戻り値がMapper型なため */
	private final Mapper mapper;
	
	/** PasswordEncoder */
	private final PasswordEncoder passwordEncoder;
	
	public Optional<User> registerUser(RegisterForm form){
		var existedUser = repository.findByEmail(form.getEmail());
		
		//メールアドレスが存在していた場合
		if(existedUser.isPresent()) {
			return Optional.empty();
		}
		
		var user = mapper.map(form, User.class);
		var encodedPassword = passwordEncoder.encode(form.getPassword());
		user.setPassword(encodedPassword);
		
		return Optional.of(repository.save(user));
	}
}
