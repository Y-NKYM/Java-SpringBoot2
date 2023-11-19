package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.UserStatusKind;
import com.example.demo.form.UserListForm;

@Controller
public class UserListController {
	private static final String KEY_USER_STATUS_KINDS="userStatusKinds";
	private static final String KEY_AUTHORITY_KINDS="authorityKinds";
	
	@GetMapping(UrlConst.USER_LIST)
	public String view(Model model, UserListForm form) {
		model.addAttribute(KEY_USER_STATUS_KINDS, UserStatusKind.values());
		model.addAttribute(KEY_AUTHORITY_KINDS, AuthorityKind.values());
		
		return "userList";
	}
	
}
