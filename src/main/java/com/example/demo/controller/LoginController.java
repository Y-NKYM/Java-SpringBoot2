package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.LoginForm;

@Controller
@RequestMapping("/login")
public class LoginController {
	@GetMapping()
	public String view(LoginForm form) {
		return "login";
	}
	
	@PostMapping()
	public void login(LoginForm form) {
		System.out.println(form.toString());
	}
}
