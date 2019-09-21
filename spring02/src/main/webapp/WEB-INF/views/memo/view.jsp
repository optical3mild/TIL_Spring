<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- spring02 project header -->
<%@ include file="../include/header.jsp" %>
</head>
<body>
<!-- spring02 project menu -->
<%@ include file="../include/menu.jsp" %>
<h2>메모 보기</h2>
<form name="form1" method="post">
	<table>
		<tr>
			<td>번호</td>
			<td>${dto.idx }</td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input name="writer" value="${dto.writer }"></td>
		</tr>
		<tr>
			<td>메모</td>
			<td><input name="memo" value="${dto.memo }" size="50"></td>
		</tr>
		<tr align="center">
			<td colspan="2">
				<!-- 검색을 위해 idx 값을 같이 넣어주어야 함. -->
				<input type="hidden" name="idx" value="${dto.idx }">
				<input type="button" value="수정" id="btnUpdate">
				<input type="button" value="삭제" id="btnDelete">
			</td>
		</tr>
	</table>
</form>
</body>
<script>
	$(function(){
		$("#btnUpdate").click(function(){
			document.form1.action="${path}/memo/update/${dto.idx}";
			document.form1.submit();
		});
		$("#btnDelete").click(function(){
			if(confirm("삭제하시겠습니까?")) {
				document.form1.action="${path}/memo/delete/${dto.idx}";
				document.form1.submit();
			}
		})
	})
</script>
</html>