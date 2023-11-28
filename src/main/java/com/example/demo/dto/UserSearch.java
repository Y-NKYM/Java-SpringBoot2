package com.example.demo.dto;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UserStatusKind;

import lombok.Data;

@Data
public class UserSearch {
	
	/** ログインID */
	private String loginId;
	
	/** アカウント状態種別 */
	private UserStatusKind userStatusKind;
	
	/** ユーザー権限種別 */
	private AuthorityKind authorityKind;

}