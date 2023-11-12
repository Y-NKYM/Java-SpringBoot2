package com.example.demo.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.constant.AlertMessage;
import com.example.demo.constant.MessageConst;
import com.example.demo.entity.User;
import com.example.demo.form.RegisterForm;
import com.example.demo.service.RegisterService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {
	
	/** 新規登録画面 Service */
	private final RegisterService service;
	
	/** MessageSource */
	private final MessageSource messageSource;
	
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
	public String register(Model model, @Validated RegisterForm form, BindingResult bdResult) {
		//バリデーション結果
		if(bdResult.hasErrors()) {
//			var message = AppUtil.getMessage(messageSource, MessageConst.FORM_ERROR);
//			model.addAttribute("msg", message);
//			model.addAttribute("isError", true);
			editGuideMessage(model, MessageConst.FORM_ERROR, true);
			return "register";
		}
		
		var user = service.registerUser(form);
		String params = "";
		if(user.isPresent()) params = user.get().getLoginId().toString();
		
		//enum取得
		var registerMessage = chooseMessageKey(user);
//		var message = AppUtil.getMessage(messageSource, registerMessage.getMessageId(), params);
//		model.addAttribute("msg", message);
//		model.addAttribute("isError", registerMessage.isError());
		editGuideMessage(model, registerMessage.getMessageId(), registerMessage.isError());
		
		if(user.isPresent()) {
			return "mypage";
		}else {
			return "register";
		}
	}
	
	/**
	 * アラート内メッセージの設定
	 * @param model
	 * @param messageId
	 * @param isError
	 */
	private void editGuideMessage(Model model, String messageId, boolean isError) {
		var message = AppUtil.getMessage(messageSource, messageId);
		model.addAttribute("msg", message);
		model.addAttribute("isError", isError);
	}
	
	/**
	 * ユーザー情報登録の結果メッセージキーを判断
	 * @param user もしくは Empty
	 * @return
	 */
	private AlertMessage chooseMessageKey(Optional<User> user) {
		if(user.isEmpty()) {
			//新規登録できずエラー
			return AlertMessage.EXISTED_USER;
		}else {
			//新規登録完了
			return AlertMessage.SUCCEED;
		}
	}
}
