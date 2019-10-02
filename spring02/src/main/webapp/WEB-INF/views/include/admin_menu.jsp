<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="menu">
	<tr>
		<td>
			<a href="${path }/shop/product/list.do">상품목록</a>	|
			<a href="${path }/shop/product/write.do">상품등록</a>	|
			<a href="${path }/pdf/list.do">PDF</a>	|	
		</td>
		<td class="login">
			<c:choose>
				<c:when test="${sessionScope.admin_userid == null }">
					<!-- 로그인 하지 않은 상태 : 로그인페이지 연결 -->
					<a href="${path }/admin/login.do">관리자로그인</a>
				</c:when>
				<c:otherwise>
					<!-- 로그인한 상태 : 로그아웃 servlet연결 -->
					${sessionScope.admin_name }님이 로그인 중 입니다.
					<a href="${path }/admin/logout.do">로그아웃</a>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
<hr>