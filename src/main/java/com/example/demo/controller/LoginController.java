package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.constant.AlertMessage;
import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
	
	/** ログイン画面 Service */
	private final LoginService service;
	
	/** PasswordEncoder */
	private final PasswordEncoder passwordEncoder;
	
	/** MessageSource */
	private final MessageSource messageSource;
	
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
		
		var user = service.searchUserByEmail(form.getEmail());
		var isCorrectUserAuth = user.isPresent() && 
				passwordEncoder.matches(form.getPassword(), user.get().getPassword());
		
		//enum取得
		var registerMessage = chooseMessageKey(isCorrectUserAuth);
		var message = AppUtil.getMessage(messageSource, registerMessage.getMessageId());
		model.addAttribute("msg", message);
		model.addAttribute("isError", registerMessage.isError());
				
		if(isCorrectUserAuth) {
			System.out.println(message);
			System.out.println(registerMessage.isError());
			return "/mypage";
		} else {
			System.out.println(message);
			System.out.println(registerMessage.isError());
			return "/login";
		}
	}
	
	private AlertMessage chooseMessageKey(boolean isCorrectUserAuth) {
		if(!isCorrectUserAuth) {
			//ログインできずエラー
			return AlertMessage.LOGIN_ERROR;
		}else {
			//ログイン完了
			return AlertMessage.LOGIN_SUCCEED;
		}
	}
}
