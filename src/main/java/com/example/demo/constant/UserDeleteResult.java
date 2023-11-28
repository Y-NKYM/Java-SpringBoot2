package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserDeleteResult {
	SUCCEED(MessageConst.USERLIST_DELETE_SUCCEED),
	ERROR(MessageConst.USERLIST_NON_EXISTED_LOGIN_ID);
	
	private String messageId;
}
