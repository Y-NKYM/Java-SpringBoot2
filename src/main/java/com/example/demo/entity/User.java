package com.example.demo.entity;

import java.time.LocalDateTime;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UserStatusKind;
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
	private Integer loginId;
	private String email;
	private String password;
	
	/** ユーザー状態種別 */
	@Column(name="is_disabled")
	@Convert(converter=UserStatusConverter.class)
	private UserStatusKind status;
	
	/** ログイン失敗回数 */
	@Column(name="login_failure_count")
	private int loginFailureCount = 0;
	
	/** アカウントロック日時 */
	@Column(name="account_locked_time")
	private LocalDateTime accountLockedTime;
	
	/** ユーザー権限種別 */
	@Convert(converter=UserAuthorityConverter.class)
	private AuthorityKind authority;
	
	/** 登録日時 */
	@Column(name="create_time")
	private LocalDateTime createTime;
	
	/** 最終更新日時 */
	@Column(name="update_time")
	private LocalDateTime updateTime;
	
	public User incrementLoginFailureCount() {
		return new User(loginId, email, password, status, ++loginFailureCount, accountLockedTime, authority, createTime, updateTime);
	}
	
	public User resetLoginFailureInfo() {
		return new User(loginId, email, password, status, 0, null, authority, createTime, updateTime);
	}
	
	public User updateAccountLocked() {
		return new User(loginId, email, password, status, 0, LocalDateTime.now(), authority, createTime, updateTime);
	}
}
