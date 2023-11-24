package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserList;
import com.example.demo.entity.User;
import com.example.demo.form.UserListForm;
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
	
	@Override
	public List<UserList> editUserList(){
		return toUserLists(repository.findAll());
	}
	
	@Override
	public List<UserList> editUserListByParam(UserListForm form){
		var user = mapper.map(form, User.class);
		return toUserLists(findUserByParam(user));
	}
	
	/**
	 * ユーザー情報EntityのListをユーザー一覧情報DTOのListに変換します。
	 * 
	 * @param users 全ユーザー情報EntityのList
	 * @return 全ユーザー一覧情報DTOのList
	 */
	private List<UserList> toUserLists(List<User> users){
		var userLists = new ArrayList<UserList>();
		for(User user: users) {
			var userList = mapper.map(user, UserList.class);
			userList.setUserStatusKind(user.getUserStatusKind().getDisplayValue());
			userList.setAuthorityKind(user.getAuthorityKind().getDisplayValue());
			userLists.add(userList);
		}
		return userLists;
	}
	
	private List<User> findUserByParam(User user){
		var loginIdParam = AppUtil.addWildcard(user.getLoginId().toString());
		if(user.getUserStatusKind()!=null && user.getAuthorityKind()!= null) {
			return repository.findByLoginIdLikeAndUserStatusKindAndAuthorityKind(loginIdParam, user.getUserStatusKind(), user.getAuthorityKind());
		}else if(user.getUserStatusKind()!=null) {
			return repository.findByLoginIdLikeAndUserStatusKind(loginIdParam, user.getUserStatusKind());
		}else if(user.getAuthorityKind()!= null) {
			return repository.findByLoginIdLikeAndAuthorityKind(loginIdParam, user.getAuthorityKind());
		}else {
			return repository.findByLoginIdLike(loginIdParam);
		}
	}

}
