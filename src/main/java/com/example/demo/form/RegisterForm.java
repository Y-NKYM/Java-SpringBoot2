package com.example.demo.form;

import lombok.Data;

@Data  //Getter・Setterを作成してくれるアノテーション
public class RegisterForm {
	private String email;
	private String password;
}
