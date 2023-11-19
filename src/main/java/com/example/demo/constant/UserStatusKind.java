package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatusKind {
	/** 利用可能 */
	ENABLED(false, "利用可能"),
	
	/** 利用可能 */
	DISABLED(true, "利用不可");
	
	/** DBの登録値 */
	private boolean isDisabled;
	
	/** 画面に表示する選択肢説明文 */
	private String displayValue;
}
