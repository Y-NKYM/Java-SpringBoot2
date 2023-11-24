package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.UserStatusKind;
import com.example.demo.form.UserListForm;
import com.example.demo.service.UserListService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserListController {
	private final UserListService service;
	
	/** モデルキー：ユーザー情報リスト */
	private static final String KEY_USERLIST="userList";
	
	/** モデルキー：ユーザー状態Enum */
	private static final String KEY_USER_STATUS_KIND_OPTIONS="userStatusKindOptions";
	
	/** モデルキー：ユーザー権限Enum */
	private static final String KEY_AUTHORITY_KIND_OPTIONS="authorityKindOptions";

	
	@GetMapping(UrlConst.USER_LIST)
	public String view(Model model, UserListForm form) {
		var users = service.editUserList();
		model.addAttribute(KEY_USERLIST, users);
		
		model.addAttribute(KEY_USER_STATUS_KIND_OPTIONS, UserStatusKind.values());
		model.addAttribute(KEY_AUTHORITY_KIND_OPTIONS, AuthorityKind.values());
		
		return "userList";
	}
	
	@PostMapping(UrlConst.USER_LIST)
	public String searchUsers(Model model, UserListForm form) {
		var users = service.editUserListByParam(form);
		model.addAttribute(KEY_USERLIST, users);
		
		model.addAttribute(KEY_USER_STATUS_KIND_OPTIONS, UserStatusKind.values());
		model.addAttribute(KEY_AUTHORITY_KIND_OPTIONS, AuthorityKind.values());
		return "userList";
	}
	
}
