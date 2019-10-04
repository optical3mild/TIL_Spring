package com.example.spring02.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// 스프링에서 관리하는 bean으로 등록
@Component
// 공통적인 업무를 지원하는 bean
@Aspect
public class LogAdvice {
	
	// 로깅을 위한 변수
	private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);
	
	// 메서드가 실행되는 시점과 범위 정의
	// .. : 모든 하위패키지
	// *Controller : Controller로 끝나는 모든 클래스
	// *(..) : 모든 메소드
	// => logPrint() 실행 (before) --> 핵심업무(메소드)실행(proceed()) --> logPrint() 실행 (after)
	// 범위지정에 따라 선택된 메소드에 아래의 메소드 행번호에 표시된 화살표와 동일한 모양의 화살표가 표시됨
	//  : 각 코드에서 적용여부를 확인할 수 있다.
	@Around(
			"execution(* com.example.spring02.controller..*Controller.*(..))"
		+ " or execution(* com.example.spring02.service..*Impl.*(..))"
		+ " or execution(* com.example.spring02.model..dao.*Impl.*(..))"
	)
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
	// 핵심업무가 호출되기 전 실행 : before
		long start = System.currentTimeMillis(); // 시작시간측정
		
	// 핵심업무 실행
	// proceed()가 호출되는 시점을 기준으로 before after가 결정 : proceed()가 호출되면 핵심업무를 실행
		Object result = joinPoint.proceed();
		
	// 핵심업무가 종료되면 이하를 실행 : after
		String type = joinPoint.getSignature().getDeclaringTypeName(); // 호출한 클래스 이름
		String name = "";
		if(type.indexOf("Controller") > -1) {
			name = "Controller \t: ";
		} else if(type.indexOf("Service") > -1) {
			name = "ServiceImpl \t: ";
		} else if(type.indexOf("DAO") > -1) {
			name = "DaoImpl \t: ";
		}
		// 로그에 남김 : 호출한 클래스, method정보
		logger.info(name+type+"."+joinPoint.getSignature().getName()+"()");
		// method에 저장되는 매개변수
		logger.info(Arrays.toString(joinPoint.getArgs()));
		// 종료시간측정
		long end = System.currentTimeMillis();
		long time = end - start;
		logger.info("실행시간: "+time);
		return result;
	}
}
