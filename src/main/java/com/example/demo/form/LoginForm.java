package com.example.demo.form;

import lombok.Data;

@Data  //Getter・Setterを作成してくれるアノテーション
public class LoginForm {
	private String loginId;
	private String password;
}
