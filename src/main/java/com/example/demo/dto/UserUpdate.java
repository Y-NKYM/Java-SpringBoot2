package com.example.demo.dto;

import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;

import lombok.Data;

@Data
public class UserUpdate {
	
	/** ログインID（更新対象者） */
	private String loginId;
	
	/** ログイン失敗状況リセットするか（trueならリセットする） */
	private boolean resetLoginFailure;
	
	/** アカウント状態種別 */
	private UserStatusKind userStatusKind;
	
	/** ユーザー権限種別 */
	private AuthorityKind authorityKind;
	
	/** 更新ユーザーID （更新者）*/
	private String updateUserId;
	
}
