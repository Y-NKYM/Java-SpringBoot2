package com.example.demo.service;

import java.util.List;

import com.example.demo.constant.UserDeleteResult;
import com.example.demo.dto.UserList;
import com.example.demo.dto.UserSearch;

public interface UserListService {
	
	/**
	 * ユーザー情報テーブルを全件検索し、ユーザー一覧画面に必要な情報へ変換して返します。
	 * @return ユーザー情報テーブルの全登録情報の配列
	 */
	public List<UserList> editUserList();
	
	/**
	 * 検索条件の情報のフォームを使って検索し、ユーザー一覧画面に必要な情報に返します。
	 * @param form
	 * @return 検索条件に対応するユーザー情報テーブルの登録情報の配列
	 */
	public List<UserList> editUserListByParam(UserSearch dto);
	
	/**
	 * 指定されたIDのユーザー情報を削除します。
	 * @param loginId ログインID
	 * @return Enum 実行結果（エラー有無）
	 */
	public UserDeleteResult deleteUserById(String loginId);
}
