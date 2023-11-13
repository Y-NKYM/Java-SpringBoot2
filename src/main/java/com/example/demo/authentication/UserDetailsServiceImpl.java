package com.example.demo.authentication;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報生成
 * 
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	/** ユーザー情報テーブルRepository */
	private final UserRepository repository;
	
	/**
	 * ユーザー情報生成
	 * @param username ログイン検索に使用する値
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findByEmail(username)
				
			//User情報を取得出来なかった場合
			.orElseThrow(() -> new UsernameNotFoundException(username));
			
			//UserとはEntityのこと
			//withUsername()でusernameにメールをセット、passwordにはパスワードをセット
		return User.withUsername(user.getEmail())
				.password(user.getPassword())
				
				//権限に関する処理
				.roles("USER")
				
				//セットした情報でユーザーを作成する
				.build();
	}

}
