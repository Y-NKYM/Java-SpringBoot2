package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.constant.SessionKeyConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.UserEditMessage;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;
import com.example.demo.dto.UserEdit;
import com.example.demo.dto.UserUpdate;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.UserEditForm;
import com.example.demo.service.UserEditService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(UrlConst.USER_EDIT)
public class UserEditController {
	
	/** ユーザー編集画面Serviceクラス */
	private final UserEditService service;
	
	/** セッション */
	private final HttpSession session;
	
	/** Dozer Mapper */
	private final Mapper mapper;
	
	/** メッセージソース */
	private final MessageSource messageSource;
	
	@GetMapping()
	public String view(Model model, UserEditForm form) throws Exception{
		//セッションに保管したloginIdの情報を取得
		var loginId = (String)session.getAttribute(SessionKeyConst.SELECTED_LOGIN_ID);
		var user = service.searchUser(loginId);
		if(user.isEmpty()) {
			return ViewNameConst.USER_EDIT_ERROR;
			//throw new Exception("ログインIDに該当するユーザー情報が見つかりません");
		}
		//編集画面表示に必要な項目の設定
		setupCommonInfo(model, user.get());
		
		return ViewNameConst.USER_EDIT;
	}
	
	@PostMapping(params="update")
	//「@AuthenticationPrincipal User」よりログインユーザーの情報を取得できる
	public String updateUser(Model model, UserEditForm form, @AuthenticationPrincipal User user) {
		//データ更新に必要な情報をUserUpdateDTOにまとめる。
		var updateDto = mapper.map(form, UserUpdate.class);
		updateDto.setLoginId((String)session.getAttribute(SessionKeyConst.SELECTED_LOGIN_ID));
		updateDto.setUpdateUserId(user.getUsername());
		
		var updateResult = service.updateUser(updateDto);
		var updateMessage = updateResult.getUpdateMessage();
		model.addAttribute("isError", updateMessage == UserEditMessage.FAILED);
		model.addAttribute("msg", AppUtil.getMessage(messageSource, updateMessage.getMessageId()));
		
		//データが更新出来なかった場合、旧データを取得し、エラーメッセージと共に編集画面を表示
		if(updateMessage == UserEditMessage.FAILED) {
			var loginId = (String)session.getAttribute(SessionKeyConst.SELECTED_LOGIN_ID);
			var oldUser = service.searchUser(loginId);
			setupCommonInfo(model, oldUser.get());
		}else {
			setupCommonInfo(model, updateResult.getUpdateUser());
		}
				
		return ViewNameConst.USER_EDIT;
	}
	
	
	private void setupCommonInfo(Model model, UserInfo user) {
		model.addAttribute("userEditForm", mapper.map(user, UserEditForm.class));
		model.addAttribute("userEdit", mapper.map(user, UserEdit.class));
		
		model.addAttribute("userStatusKindOptions", UserStatusKind.values());
		model.addAttribute("authorityKindOptions", AuthorityKind.values());
	}
}
