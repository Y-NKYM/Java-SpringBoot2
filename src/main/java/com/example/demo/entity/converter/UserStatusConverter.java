package com.example.demo.entity.converter;

import com.example.demo.constant.db.UserStatusKind;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserStatusConverter implements AttributeConverter<UserStatusKind, Boolean> {
	@Override
	public Boolean convertToDatabaseColumn(UserStatusKind userStatus) {
		return userStatus.isDisabled();
	}
	
	@Override
	public UserStatusKind convertToEntityAttribute(Boolean isDisabled) {
		return isDisabled?UserStatusKind.DISABLED:UserStatusKind.ENABLED;
	}
}
