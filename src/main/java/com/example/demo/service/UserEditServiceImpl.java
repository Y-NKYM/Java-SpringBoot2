package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.constant.UserEditMessage;
import com.example.demo.dto.UserEditResult;
import com.example.demo.dto.UserUpdate;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserEditServiceImpl implements UserEditService{
	private final UserRepository repository;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<UserInfo> searchUser(String loginId){
		return repository.findById(loginId);
	}
	
	public UserEditResult updateUser(UserUpdate userUpdate) {
		//戻り値オブジェクトのインスタンスを作成
		var userUpdateResult = new UserEditResult();
		
		//現在の登録情報の取得
		var user = repository.findById(userUpdate.getLoginId());
		if(user.isEmpty()) {
			userUpdateResult.setUpdateMessage(UserEditMessage.FAILED);
			return userUpdateResult;
		}
		
		//以降はユーザー情報がDB内に存在している前提
		//UserのEntity情報を取得
		var updateInfo = user.get();
		
		//セット
		updateInfo.setUserStatusKind(userUpdate.getUserStatusKind());
		updateInfo.setAuthorityKind(userUpdate.getAuthorityKind());
		if(userUpdate.isResetLoginFailure()) {
			updateInfo.setLoginFailureCount(0);
			updateInfo.setAccountLockedTime(null);
		}
		updateInfo.setUpdateTime(LocalDateTime.now());
		updateInfo.setUpdateUser(userUpdate.getUpdateUserId());
		
		//DBに更新を試みる
		try {
			repository.save(updateInfo);
		}catch(Exception e) {
			userUpdateResult.setUpdateMessage(UserEditMessage.FAILED);
			return userUpdateResult;
		}
		
		//更新成功時のメッセージ
		userUpdateResult.setUpdateUser(updateInfo);
		userUpdateResult.setUpdateMessage(UserEditMessage.SUCCEED);
		return userUpdateResult;
	}
}
