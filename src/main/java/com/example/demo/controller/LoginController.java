package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.LoginForm;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private static final String LOGIN_ID="user";
	private static final String PASSWORD="pass";
	
	@GetMapping()
	public String view(Model model, LoginForm form) {
		return "login";
	}
	
	@PostMapping()
	public String login(Model model, LoginForm form) {
		System.out.println(form.toString());
		var isCorrectLoginId = form.getLoginId().equals(LOGIN_ID);
		var isCorrectPassword = form.getPassword().equals(PASSWORD);
		System.out.println(isCorrectLoginId);
		System.out.println(isCorrectPassword);
		var isCorrectUserAuth = isCorrectLoginId && isCorrectPassword;
		if(isCorrectUserAuth) {
			return "redirect:/mypage";
		} else {
			model.addAttribute("msg", "ログイン情報が一致しません");
			return "login";
		}
	}
}
