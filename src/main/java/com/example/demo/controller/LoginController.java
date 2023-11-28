package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.constant.AlertMessage;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;
import com.example.demo.util.AppUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(UrlConst.LOGIN)
public class LoginController {
	
	/** ログイン画面 Service */
	private final LoginService service;
	
	/** PasswordEncoder */
	private final PasswordEncoder passwordEncoder;
	
	/** MessageSource */
	private final MessageSource messageSource;
	
	/** セッション情報 */
	private final HttpSession session;
	
	/**
	 * ログイン画面
	 * 
	 * @param model
	 * @param form
	 * @return ログイン画面
	 */
	@GetMapping()
	public String view(Model model, LoginForm form) {
		return ViewNameConst.LOGIN;
	}
	
	/**
	 * ログインエラー画面表示
	 * 
	 * @param model
	 * @param form
	 * @return ログイン画面
	 */
	@GetMapping(params = "error")
	public String viewWithError(Model model, LoginForm form) {
		var errorInfo = (Exception)session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		model.addAttribute("msg", errorInfo.getMessage());
		model.addAttribute("isError", true);
		return ViewNameConst.LOGIN;
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
			return ViewNameConst.MYPAGE;
		} else {
			System.out.println(message);
			System.out.println(registerMessage.isError());
			return ViewNameConst.LOGIN;
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
