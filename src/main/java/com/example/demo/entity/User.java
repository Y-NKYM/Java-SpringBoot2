package com.example.demo.entity;

import java.time.LocalDateTime;

import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;
import com.example.demo.entity.converter.UserAuthorityConverter;
import com.example.demo.entity.converter.UserStatusConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id  //Primary keyに必要
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private String loginId;
	private String email;
	private String password;
	
	/** ユーザー状態種別 */
	@Column(name="is_disabled")
	@Convert(converter=UserStatusConverter.class)
	private UserStatusKind userStatusKind;
	
	/** ログイン失敗回数 */
	@Column(name="login_failure_count")
	private int loginFailureCount = 0;
	
	/** アカウントロック日時 */
	@Column(name="account_locked_time")
	private LocalDateTime accountLockedTime;
	
	/** ユーザー権限種別 */
	@Column(name="authority")
	@Convert(converter=UserAuthorityConverter.class)
	private AuthorityKind authorityKind;
	
	/** 登録日時 */
	@Column(name="create_time")
	private LocalDateTime createTime;
	
	/** 最終更新日時 */
	@Column(name="update_time")
	private LocalDateTime updateTime;
	
	public User incrementLoginFailureCount() {
		return new User(loginId, email, password, userStatusKind, ++loginFailureCount, accountLockedTime, authorityKind, createTime, updateTime);
	}
	
	public User resetLoginFailureInfo() {
		return new User(loginId, email, password, userStatusKind, 0, null, authorityKind, createTime, updateTime);
	}
	
	public User updateAccountLocked() {
		return new User(loginId, email, password, userStatusKind, 0, LocalDateTime.now(), authorityKind, createTime, updateTime);
	}
}
