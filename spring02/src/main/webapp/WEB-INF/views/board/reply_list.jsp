<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- spring02 project header -->
<%@ include file="../include/header.jsp" %>
<style>
	table, td, tr {
		border : none;
	} 
</style>
</head>
<body>
<!-- 2. 줄바꿈 처리 관련 값 설정 : jstl의 안에서는 '\n', '\\n'사용 불가-->
<% pageContext.setAttribute("newLineChar", "\n"); %>
<table style="width:700px">
	<c:forEach var="row" items="${list }">
	
		<!-- 3. 태그 문자 처리(무력화) : 2.줄바꿈 처리에서 <br>을 사용하므로, 그 전에 처리하여야 한다. -->
		<c:set var="str" value="${fn:replace(row.replytext, '<','&lt;') }" />
		<c:set var="str" value="${fn:replace(str, '<','&gt;') }" />

		<!-- fn사용을 위해 태그라이브러리 fn(jstl/functions)추가 필요 : header.jsp에 추가-->
		<!-- 1. 공백처리 : fn:replace(원본문자열 객체, 찾을문자열 형태, 바꿀 문자열) -->
		<c:set var="str" value="${fn:replace(str,'  ','&nbsp;&nbsp;')}" />

		<!-- 2. 줄바꿈 처리 : jstl안에서 '\n'사용불가로 미리 선언한 변수값을 가져옴 -->
		<c:set var="str" value="${fn:replace(str, newLineChar,'<br>')}" />
		<tr>
			<td>
				${row.name }
				(<fmt:formatDate value="${row.regdate }" pattern="yyyy-MM-dd a HH:mm:ss" />)<br>
				${str} <!-- jstl로 변환한 내용을 출력 -->
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>