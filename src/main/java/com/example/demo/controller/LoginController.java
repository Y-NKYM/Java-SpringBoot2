package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
	
//	private static final String LOGIN_ID="user";
//	private static final String PASSWORD="pass";
	private final LoginService service;
	
	@GetMapping()
	public String view(Model model, LoginForm form) {
		return "login";
	}
	
	@PostMapping()
	public String login(Model model, LoginForm form) {
		System.out.println(form.toString());
//		var isCorrectLoginId = form.getLoginId().equals(LOGIN_ID);
//		var isCorrectPassword = form.getPassword().equals(PASSWORD);
//		System.out.println(isCorrectLoginId);
//		System.out.println(isCorrectPassword);
//		var isCorrectUserAuth = isCorrectLoginId && isCorrectPassword;
		
		var user = service.searchUserByEmail(form.getEmail());
		
		var isCorrectUserAuth = user.isPresent() && 
				form.getPassword().equals(user.get().getPassword());
		
		if(isCorrectUserAuth) {
			return "redirect:/mypage";
		} else {
			model.addAttribute("msg", "ログイン情報が一致しません");
			return "login";
		}
	}
}
