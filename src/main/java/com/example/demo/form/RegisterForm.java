package com.example.demo.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data  //Getter・Setterを作成してくれるアノテーション
public class RegisterForm {
	@NotBlank
	private String email;
	
	@Length(min=8, max=20, message="Formクラスないパスワードエラーメッセージです！{min}～{max}")
	private String password;
}
