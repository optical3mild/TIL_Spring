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
<%@ include file="../include/admin_menu.jsp" %>
<h2>이메일 보내기</h2>
<form method="post" action="${path }/email/send.do">
	발신자 이름: <input name="senderName"><br>
	발신자 이메일 주소: <input name="senderMail"><br>
	수신자 이메일 주소: <input name="receiveMail"><br>
	제목: <input name="subject"><br>
	내용: <textarea rows="5" cols="80" name="message"></textarea><br>
	<input type="submit" value="전송">
</form>
<!-- 메일 전송결과 확인 메시지 출력 -->
<span style="coler:red;">${message }</span>
</body>
</html>