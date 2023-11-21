package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserList;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserListServiceImpl implements UserListService {
	/** ユーザーDBテーブルに接続 */
	private final UserRepository repository;
	
	/** Dozer Mapper */
	private final Mapper mapper;
	
	public List<UserList> editUserList(){
		return toUserLists(repository.findAll());
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
			userList.setStatus(user.getStatus().getDisplayValue());
			userList.setAuthority(user.getAuthority().getDisplayValue());
			userLists.add(userList);
		}
		return userLists;
	}

}
