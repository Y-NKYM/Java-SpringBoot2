package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlertMessage {
	SUCCEED(MessageConst.REGISTER_SUCCEED, false),
	EXISTED_USER(MessageConst.REGISTER_EXISTED_USER, true),
	LOGIN_ERROR(MessageConst.LOGIN_WRONG_INPUT, true),
	LOGIN_SUCCEED(MessageConst.LOGIN_SUCCEED, false);
	private String messageId;
	private boolean isError;
}
