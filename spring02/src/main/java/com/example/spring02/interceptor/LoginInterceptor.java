package com.example.spring02.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// HandlerInterceptorAdapter 추상클래스 상속
// preHandle(), postHandle() 오버라이딩
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	// 메인액션이 실행되기 전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 세션 객체 생성
		HttpSession session = request.getSession();
		// 세션이 없으면 = 로그인 되지 않은 상태
		if(session.getAttribute("userid") == null) {
			// 로그인 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/member/login.do?message=nologin");
			// 메인 액션으로 돌아가지 않음.
			return false; 
		} else {
			// 메인액션으로 이동
			return true;
		}
	}
	
	// 메인액션이 실행된 후
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
}
