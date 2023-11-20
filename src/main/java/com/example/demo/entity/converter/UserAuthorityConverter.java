package com.example.demo.entity.converter;

import com.example.demo.constant.AuthorityKind;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserAuthorityConverter implements AttributeConverter<AuthorityKind, String> {
	@Override
	public String convertToDatabaseColumn(AuthorityKind authorityKind) {
		return authorityKind.getCode();
	}
	
	@Override
	public AuthorityKind convertToEntityAttribute(String value) {
		return AuthorityKind.from(value);
	}
}
