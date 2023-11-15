package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
	
	@Column(name="is_disabled")
	private boolean isDisabled;
	
	@Column(name="login_failure_count")
	private int loginFailureCount = 0;
	
	@Column(name="account_locked_time")
	private LocalDateTime accountLockedTime;
	
	public User incrementLoginFailureCount() {
		return new User(loginId, email, password, isDisabled, ++loginFailureCount, accountLockedTime);
	}
	
	public User resetLoginFailureInfo() {
		return new User(loginId, email, password, isDisabled, 0, null);
	}
	
	public User updateAccountLocked() {
		return new User(loginId, email, password, isDisabled, 0, LocalDateTime.now());
	}
}
