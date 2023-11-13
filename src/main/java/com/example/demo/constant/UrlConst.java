package com.example.demo.constant;

/**
 * URL定数クラス
 */
public class UrlConst {
	/** ログイン画面 */
	public static final String LOGIN = "/login";
	
	/** 新規登録画面 */
	public static final String REGISTER = "/register";
	
	/** マイページ画面 */
	public static final String MYPAGE = "/mypage";
	
	/** 認証不要画面 */
	public static final String[] NO_AUTH = { LOGIN, REGISTER, "/webjars/**" };
}
