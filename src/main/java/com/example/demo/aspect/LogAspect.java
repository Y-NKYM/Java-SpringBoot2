package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	/** ログを出力するためのクラス（Logback） */
	private final Logger log = LoggerFactory.getLogger(LogAspect.class);
	
	/**
	 * 指定したメソッドの開始、終了ログを出力します。
	 * 
	 * <p>また例外発生時は加えて例外内容をログに出力します。
	 * 
	 * @param jp 処理を挿入する場所の情報
	 * @return 指定したメソッドの戻り値
	 * @throws Throwable
	 */
	@Around("execution(* com.example.demo..*(..))")
	public Object writeLog(ProceedingJoinPoint jp) throws Throwable{
		Object returnObj = null;
		
		//開始ログの出力
		log.info("start:"+jp.getSignature().toString());
		
		try {
			
			//JoinPointのメソッドの実行
			returnObj = jp.proceed();
			
		} catch(Throwable t) {
			
			//エラーログの出力
			log.error(t.toString());
			throw t;
			
		}
		
		//終了ログの出力
		log.info("end:"+jp.getSignature().toString());
		
		return returnObj;
	}
}
