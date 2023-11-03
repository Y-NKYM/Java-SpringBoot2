package com.example.demo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	/** ログイン画面 Service */
	private final LoginService service;
	
	/** PasswordEncoder */
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * ログイン画面
	 * 
	 * @param model
	 * @param form
	 * @return ログイン画面
	 */
	@GetMapping()
	public String view(Model model, LoginForm form) {
		return "login";
	}
	
	/**
	 * ログイン認証
	 * 
	 * @param model
	 * @param form
	 * @return ログイン／マイページ画面
	 */
	@PostMapping()
	public String login(Model model, LoginForm form) {
//		var encodedPassword = passwordEncoder.encode(form.getPassword());
//		System.out.println(encodedPassword.toString());
		
		var user = service.searchUserByEmail(form.getEmail());
		
//		var isCorrectUserAuth = user.isPresent() && 
//				form.getPassword().equals(user.get().getPassword());
		
		var isCorrectUserAuth = user.isPresent() && 
				passwordEncoder.matches(form.getPassword(), user.get().getPassword());
		
		if(isCorrectUserAuth) {
			return "redirect:/mypage";
		} else {
			model.addAttribute("msg", "ログイン情報が一致しません");
			return "login";
		}
	}
}
