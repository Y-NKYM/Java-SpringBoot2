package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {
	@Id  //Primary keyに必要
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer loginId;
	private String email;
	private String password;
}
