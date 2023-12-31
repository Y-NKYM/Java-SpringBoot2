package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.constant.UserDeleteResult;
import com.example.demo.dto.UserList;
import com.example.demo.dto.UserSearch;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserListServiceImpl implements UserListService {
	/** ユーザーDBテーブルに接続 */
	private final UserRepository repository;
	
	/** Dozer Mapper */
	private final Mapper mapper;
	
	/**
	 * データベース内のユーザーテーブル情報の全てを取得します。
	 * 
	 * @return UserList DBから得た全ユーザー情報をUserList型に型変換したもの。
	 */
	@Override
	public List<UserList> editUserList(){
		return toUserLists(repository.findAll());
	}
	
	/**
	 * UserListForm（検索用フォーム）からUserEntityへ型変換し、toUserListsメソッドへ引き渡します。
	 * 
	 * @param UserListForm 検索条件データ
	 * @return DBから得た各ユーザー情報をUserList型に型変換したもの。
	 */
	@Override
	public List<UserList> editUserListByParam(UserSearch dto){
		return toUserLists(findUserByParam(dto));
	}
	
	@Override
	public UserDeleteResult deleteUserById(String loginId) {
		var user = repository.findById(loginId);
		if(user.isEmpty()) {
			return UserDeleteResult.ERROR;
		}
		repository.deleteById(loginId);
		return UserDeleteResult.SUCCEED;
	}
	
	/**
	 * ユーザー情報EntityのListをユーザー一覧情報DTOのListに変換します。
	 * 
	 * @param users 全ユーザー情報EntityのList
	 * @return 全ユーザー一覧情報DTOのList
	 */
	private List<UserList> toUserLists(List<UserInfo> users){
		var userLists = new ArrayList<UserList>();
		for(UserInfo user: users) {
			var userList = mapper.map(user, UserList.class);
			userList.setUserStatusKind(user.getUserStatusKind().getDisplayValue());
			userList.setAuthorityKind(user.getAuthorityKind().getDisplayValue());
			userLists.add(userList);
		}
		return userLists;
	}
	
	/**
	 * 検索条件を元にデータベースから対応するユーザー情報一覧を取得します。
	 * 
	 * @param UserEntity 検索条件データ
	 * @return DBからの結果を持つUserEntityの集まり
	 */
	private List<UserInfo> findUserByParam(UserSearch dto){
		var loginIdParam = AppUtil.addWildcard(dto.getLoginId());
		if(dto.getUserStatusKind()!=null && dto.getAuthorityKind()!= null) {
			return repository.findByLoginIdLikeAndUserStatusKindAndAuthorityKind(loginIdParam, dto.getUserStatusKind(), dto.getAuthorityKind());
		}else if(dto.getUserStatusKind()!=null) {
			return repository.findByLoginIdLikeAndUserStatusKind(loginIdParam, dto.getUserStatusKind());
		}else if(dto.getAuthorityKind()!= null) {
			return repository.findByLoginIdLikeAndAuthorityKind(loginIdParam, dto.getAuthorityKind());
		}else {
			return repository.findByLoginIdLike(loginIdParam);
		}
	}

}
