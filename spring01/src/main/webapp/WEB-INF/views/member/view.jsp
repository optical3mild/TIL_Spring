<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- views/member/write.jsp -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<%@ include file = "../include/header.jsp" %>
	<style>
		table, td {
			border : 1px solid black;
			width : 400px;
			/* border-collapse: collapse; */
		}
	</style>
</head>
<body>
	<%@ include file = "../include/menu.jsp" %>
	<h2>회원정보 수정</h2>
	<form name="form1" method="post">
		<table>
			<tr>
				<td>아이디</td>
				<td><input name="userid" value="${dto.userid }" readonly></td>
			</tr>
			<tr>
				<!-- 비밀번호가 맞으면 수정가능하도록 구현. -->
				<td>비밀번호</td>
				<td><input type="password" name="passwd"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input name="name" value="${dto.name }"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input name="email" value="${dto.email }"></td>
			</tr>
			<tr>
				<td>가입일</td>
				<td>
					<c:if test="${join_date != null }">
						<fmt:formatDate value="${join_date }" pattern="yyyy-MM-dd HH:mm:ss" />
					</c:if>
					<fmt:formatDate value="${dto.join_date }" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="수정" id="btnUpdate">
					<input type="button" value="삭제" id="btnDelete">
					<div style="color : red;">${message } </div>
				</td>
			</tr>
		</table>
	</form>
</body>
<script>
$(function() {
	$("#btnUpdate").click(function(){
		// name이 form1인 태그의 액션을 지정(속성설정)
		document.form1.action="${path}/member/update.do";
		// form1을 실행.
		document.form1.submit();
	});
});
</script>
</html>