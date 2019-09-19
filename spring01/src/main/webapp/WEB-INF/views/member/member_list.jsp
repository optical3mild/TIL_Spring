<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<%@ include file = "../include/header.jsp" %>
	<style>
		table, td, th {
			border : 1px solid black;
			width : 700px;
			border-collapse: collapse;
		}
	</style>
</head>
<body>
	<%@ include file = "../include/menu.jsp" %>
	<h2>회원목록</h2>
	<input type="button" value="회원등록" onclick="location.href='${path }/member/write.do'" >
	<table>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>이메일</th>
			<th>가입날짜</th>
		</tr>
		<c:forEach var="row" items="${list }">
			<tr>
				<td>${row.userid }</td>
				<td>
					<a href="${path }/member/view.do?userid=${row.userid }" >${row.name }</a>
				</td>
				<td>${row.email }</td>
				<td>
					<fmt:formatDate value="${row.join_date }" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>