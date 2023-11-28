package com.example.demo.form;

import org.hibernate.validator.constraints.Length;

import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;

import lombok.Data;

@Data
public class UserListForm {
	
	/** ログインID */
	@Length(min=2, max=20)
	private String loginId;
	
	/** アカウント状態種別 */
	private UserStatusKind userStatusKind;
	
	/** ユーザー権限種別 */
	private AuthorityKind authorityKind;
	
	/** ユーザー一覧情報から選択されたユーザーのログインID */
	private String selectedLoginId;
	
	/**
	 * selectedLoginIdを初期化し、このクラスを返す。
	 * 削除された後、検索ボタンをクリックする動作となる。
	 * @return UserListForm
	 */
	public UserListForm clearSelectedLoginId() {
		this.selectedLoginId = null;
		return this;
	}
	
}
