<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }" />

<div style="text-align: center;">
	<a href="${path }/">Main</a> <!-- 1. 메인페이지 -->
	<a href="${path }/gugu.do">구구단</a> <!-- 2. 구구단 페이지 -->
	<a href="${path }/test.do">테스트</a> <!-- 3. test실습 -->
	<a href="${path }/member/list.do">회원관리</a> <!-- 4. DB연동 연습 -->
</div>
<hr>