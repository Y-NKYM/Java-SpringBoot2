package com.example.demo.form;

import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;

import lombok.Data;

/**
 * ユーザー更新画面フォームクラス
 */
@Data
public class UserEditForm {
	
	/** ログイン失敗状況をリセットするか(trueならリセットする) */
	private boolean resetLoginFailure;
	
	/** アカウント状態種別 */
	private UserStatusKind userStatusKind;
	
	/** ログイン失敗状況をリセットするか(trueならリセットする) */
	private AuthorityKind authorityKind;
}
