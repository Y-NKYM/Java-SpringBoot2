package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;
import com.example.demo.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, String>{
	Optional<UserInfo> findByEmail(String email);
	
	List<UserInfo> findByLoginIdLike(String loginId);
	List<UserInfo> findByLoginIdLikeAndUserStatusKind(String loginId, UserStatusKind userStatusKind);
	List<UserInfo> findByLoginIdLikeAndAuthorityKind(String loginId, AuthorityKind authorityKind);
	List<UserInfo> findByLoginIdLikeAndUserStatusKindAndAuthorityKind(String loginId, UserStatusKind userStatusKind, AuthorityKind authorityKind);
}
