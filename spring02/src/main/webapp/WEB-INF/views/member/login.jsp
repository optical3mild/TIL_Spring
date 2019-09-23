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
<h2>로그인</h2>
<form name="form1" method="post">
	<table style="width:400">
		<tr>
			<td>아이디</td>
			<td><input name="userid" id="userid"></td>		
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd" id="passwd"></td>		
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" id="btnLogin" value="로그인">
			</td>
		</tr>
	</table>
</form>
</body>
<script type="text/javascript">
	$(function(){
		$("#btnLogin").click(function(){
			var userid = $("#userid").val();
			var passwd = $("#passwd").val();
			if(userid=="") {
				alert("아이디를 입력하세요");
				$("#userid").focus();
				return;
			}
			if(passwd=="") {
				alert("비밀번호를 입력하세요");
				$("#passwd").focus();
				return;
			}
			document.form1.action="${path}/member/login_check.do";
			document.form1.submit();
		});
	})
</script>
</html>