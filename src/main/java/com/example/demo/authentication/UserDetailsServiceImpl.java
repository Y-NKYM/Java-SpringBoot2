package com.example.demo.authentication;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
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
	
	/** アカウントロック継続時間 */
	@Value("${security.locking-time}")
	private int lockingTime;
	
	/** アカウントがロックされる失敗回数 */
	@Value("${security.locking-border-count}")
	private int lockingBorderCount;

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
			
		//データベースからアカウントロックに関する情報の入手
		var accountLockedTime = user.getAccountLockedTime();
		//「!null：ロック状態」かつ「ロック時間＋固定時間が現時間より後（時間がまだ立っていない）」
		var isAccountLocked = accountLockedTime != null
			&& accountLockedTime.plusMinutes(lockingTime).isAfter(LocalDateTime.now());
			
		//UserとはEntityのこと
		//withUsername()でusernameにメールをセット、passwordにはパスワードをセット
		return User.withUsername(user.getEmail())
				.password(user.getPassword())
				
				//Enumファイル内のgetAuthority()がEnum型になったため
				//権限種別のコード値変数をcodeに変更したため。
				.authorities(user.getAuthorityKind().getCode())
				
				//アカウントロック・利用可否チェック
				//Enumファイル内のgetAuthority()がEnum型になったため
				.disabled(user.getUserStatusKind().isDisabled())
				.accountLocked(isAccountLocked)
				
				//セットした情報でユーザーを作成する
				.build();
	}
	
	/**
	 * 認証失敗字にログイン失敗回数を加算、ロック日時の更新
	 * @param event
	 */
	@EventListener
	public void handle(AuthenticationFailureBadCredentialsEvent event) {
		var email = event.getAuthentication().getName();
		repository.findByEmail(email).ifPresent(user -> {
			repository.save(user.incrementLoginFailureCount());
		
			var isReachFailureCount = user.getLoginFailureCount() == lockingBorderCount;
			if(isReachFailureCount) {
				repository.save(user.updateAccountLocked());
			}
		});
	}
	
	/**
	 * ログイン認証成功時に失敗回数をリセット
	 * @param event
	 */
	@EventListener
	public void handle(AuthenticationSuccessEvent event) {
		var email = event.getAuthentication().getName();
		repository.findByEmail(email).ifPresent(user -> {
			repository.save(user.resetLoginFailureInfo());
		});
	}

}
