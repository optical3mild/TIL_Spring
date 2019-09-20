<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>메모장</h2>
<table style="border: 1px solid black; width:500px;">
	<thead>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>메모</th>
			<th>날짜</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="row" items="${list }">
			<tr>
				<td>${row.idx }</td>
				<td>${row.writer }</td>
				<td>${row.memo }</td>
				<td>${row.post_date }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>