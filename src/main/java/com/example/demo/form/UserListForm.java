package com.example.demo.form;

import org.hibernate.validator.constraints.Length;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UserStatusKind;

import lombok.Data;

@Data
public class UserListForm {
	
	/** ログインID */
	@Length(min=2, max=20)
	private String LoginId;
	
	/** アカウント状態種別 */
	private UserStatusKind userStatusKind;
	
	/** ユーザー権限種別 */
	private AuthorityKind authorityKind;
	
}
