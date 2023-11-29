package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.constant.SessionKeyConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.UserDeleteResult;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;
import com.example.demo.dto.UserSearch;
import com.example.demo.form.UserListForm;
import com.example.demo.service.UserListService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(UrlConst.USER_LIST)
public class UserListController {
	
	/** ユーザー一覧画面Serviceクラス */
	private final UserListService service;
	
	/** Dozer Mapper */
	private final Mapper mapper;
	
	/** メッセージソース */
	private final MessageSource messageSource;
	
	/** セッション */
	private final HttpSession session;
	
	/** モデルキー：ユーザー情報リスト */
	private static final String KEY_USERLIST="userList";
	
	/** モデルキー：ユーザー状態Enum */
	private static final String KEY_USER_STATUS_KIND_OPTIONS="userStatusKindOptions";
	
	/** モデルキー：ユーザー権限Enum */
	private static final String KEY_AUTHORITY_KIND_OPTIONS="authorityKindOptions";
	
	@GetMapping()
	public String view(Model model, UserListForm form) {
		//sessionの削除
		session.removeAttribute(SessionKeyConst.SELECTED_LOGIN_ID);
		
		var users = service.editUserList();
		model.addAttribute(KEY_USERLIST, users);
		
		model.addAttribute(KEY_USER_STATUS_KIND_OPTIONS, UserStatusKind.values());
		model.addAttribute(KEY_AUTHORITY_KIND_OPTIONS, AuthorityKind.values());
		
		return ViewNameConst.USER_LIST;
	}
	
	@PostMapping(params = "search")
	public String searchUsers(Model model, UserListForm form) {
		var searchDto = mapper.map(form, UserSearch.class);
		var users = service.editUserListByParam(searchDto);
		model.addAttribute(KEY_USERLIST, users);
		
		model.addAttribute(KEY_USER_STATUS_KIND_OPTIONS, UserStatusKind.values());
		model.addAttribute(KEY_AUTHORITY_KIND_OPTIONS, AuthorityKind.values());
		return ViewNameConst.USER_LIST;
	}
	
	@PostMapping(params = "delete")
	public String deleteUser(Model model, UserListForm form) {
		var userDeleteResult = service.deleteUserById(form.getSelectedLoginId());
		//取得したExecuteResultがErrorかSucceedかを判別。
		model.addAttribute("isError", userDeleteResult == UserDeleteResult.ERROR);
		//
		model.addAttribute("msg", AppUtil.getMessage(messageSource, userDeleteResult.getMessageId()));
		return searchUsers(model, form.clearSelectedLoginId());
	}
	
	@PostMapping(params = "edit")
	public String updateUser(UserListForm form){
		session.setAttribute(SessionKeyConst.SELECTED_LOGIN_ID, form.getSelectedLoginId());
		return AppUtil.doRedirect(UrlConst.USER_EDIT);
	}
}
