package com.example.demo.dto;

import com.example.demo.constant.UserEditMessage;
import com.example.demo.entity.UserInfo;

import lombok.Data;

@Data
public class UserEditResult {
	
	/** ユーザー更新結果 */
	private UserInfo updateUser;
	
	/** ユーザー更新結果メッセージEnum */
	private UserEditMessage updateMessage;
}
