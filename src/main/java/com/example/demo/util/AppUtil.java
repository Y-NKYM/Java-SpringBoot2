package com.example.demo.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

/** アプリケーション共通クラス */
public class AppUtil {
	/**
	 * 
	 * @param messageSource
	 * @param key
	 * @param params
	 * @return value
	 */
	public static String getMessage(MessageSource messageSource, 
			String key, Object...params) {
		return messageSource.getMessage(key, params, Locale.JAPAN);
	}
	
	public static String addWildcard(String param) {
		return "%" + param + "%";
	}
	
	public static String doRedirect(String url) {
		return "redirect:" + url;
	}
}
