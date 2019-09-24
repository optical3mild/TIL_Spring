<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- session을 사용하지 않게 설정 -->
<%-- <%@ page session="false" %> --%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
<%@ include file="include/header.jsp" %>
</head>
<body>
<%@ include file="include/menu.jsp" %>
	<!-- 자바코드 : 세션변수가 존재하면 
		if( session.getAttribute("userid") != null )
	-->
<c:if test="${sessionScope.userid != null }">
	<h2>
		${sessionScope.name }(${sessionScope.userid })님의 방문을 환영합니다.
	</h2>
</c:if>

<h1>
	Hello world!  
</h1>
	<P>The time on the server is ${serverTime}. </P>
</body>
</html>
