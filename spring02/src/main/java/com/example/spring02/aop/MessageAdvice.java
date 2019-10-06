package com.example.spring02.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component // 기타 bean
@Aspect	// aop bean -  공통업무를 지원하는 코드
public class MessageAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageAdvice.class);
	
	//@Before, @After ==> 매개변수로 JoinPoint 사용
	//@Around ==> 매개변수로 ProceedingJoinPoint 사용
	
	@Before("execution(* com.example.spring02.service.message.MessageService*.*(..))")
	public void startLog(JoinPoint jp) {
		logger.info("핵심 업무의 코드정보: "+ jp.getSignature());
		logger.info("method: "+ jp.getSignature().getName());
		logger.info("매개변수: "+ Arrays.toString(jp.getArgs()));
	}
	
	@Around("execution(* com.example.spring02.service.message.MessageService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable	{
		//호출 전
		long start = System.currentTimeMillis();
		
		//대상 메소드 호출
		Object result = pjp.proceed();
		
		//호출 후
		long end = System.currentTimeMillis();
		logger.info(pjp.getSignature().getName()+":"+(end-start));
		logger.info("================");
		return result;
	}
}
