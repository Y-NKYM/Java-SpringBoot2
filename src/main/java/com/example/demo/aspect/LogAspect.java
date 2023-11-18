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
	
	@Around("execution(* com.example.demo..*(..))")
	public Object writeLog(ProceedingJoinPoint jp) {
		Object returnObj = null;
		
		//開始ログの出力
		log.info("start:"+jp.getSignature().toString());
		
		try {
			
			//JoinPointのメソッドの実行
			returnObj = jp.proceed();
			
		} catch(Throwable t) {
			
			//エラーログの出力
			log.error(t.toString());
			
		}
		
		//終了ログの出力
		log.info("end:"+jp.getSignature().toString());
		
		return returnObj;
	}
}
