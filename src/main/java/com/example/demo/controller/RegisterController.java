package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.RegisterForm;
import com.example.demo.service.RegisterService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {
	
	/** 新規登録画面 Service */
	private final RegisterService service;
	
	/**
	 * 新規登録画面
	 * 
	 * @param model
	 * @param form
	 * @return ログイン画面
	 */
	@GetMapping()
	public String view(Model model, RegisterForm form) {
		return "register";
	}
	
	@PostMapping()
	public void register(Model model, RegisterForm form) {
		var user = service.registerUser(form);
	}
}
