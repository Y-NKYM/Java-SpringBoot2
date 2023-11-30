package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserEditServiceImpl implements UserEditService{
	private final UserRepository repository;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<User> searchUser(String loginId){
		return repository.findById(loginId);
	}
}
