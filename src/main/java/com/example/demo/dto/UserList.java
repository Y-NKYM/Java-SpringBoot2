package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserList {
	
	/** ログインID */
	private String loginId;
	
	/** メールアドレス */
	private String email;
	
	/** ログイン失敗回数 */
	private int loginFailureCount;
	
	/** アカウントロック日時 */
	private LocalDateTime accountLockedTime;
	
	/** アカウント状態 */
	private String status;
	
	/** 権限 */
	private String authority;
}
