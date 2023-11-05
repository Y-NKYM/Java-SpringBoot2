package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.RegisterForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {
	
//	/** ログイン画面 Service */
//	private final LoginService service;
//	
//	/** PasswordEncoder */
//	private final PasswordEncoder passwordEncoder;
//	
//	/** MessageSource */
//	private final MessageSource messageSource;
	
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
		
	}
}
