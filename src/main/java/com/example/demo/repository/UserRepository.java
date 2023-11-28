package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;
import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	Optional<User> findByEmail(String email);
	
	List<User> findByLoginIdLike(String loginId);
	List<User> findByLoginIdLikeAndUserStatusKind(String loginId, UserStatusKind userStatusKind);
	List<User> findByLoginIdLikeAndAuthorityKind(String loginId, AuthorityKind authorityKind);
	List<User> findByLoginIdLikeAndUserStatusKindAndAuthorityKind(String loginId, UserStatusKind userStatusKind, AuthorityKind authorityKind);
}
