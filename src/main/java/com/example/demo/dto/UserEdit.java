package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * ユーザー編集画面表示情報DTOクラス
 */
@Data
public class UserEdit {
	
	/** ログインID */
	private String loginId;
	
	/** ログイン失敗回数 */
	private String loginFailureCount;
	
	/** アカウントロック日時 */
	private LocalDateTime accountLockedTime;

}
