package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {
	@Id  //Primary keyに必要
	@Column(name="id")
	private String loginId;
	private String email;
	private String password;
}
