package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.User;

public interface UserEditService {
	
	/**
	 * ログインIDを使って特定のユーザー情報をDBから取得し、返却します。
	 * @param loginId ユーザーのID
	 * @return 該当するユーザーの登録情報
	 */
	public Optional<User> searchUser(String loginId);
	
}
