<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file = "include/header.jsp" %>
<script>
	function doF() {
		// 비동기적인 방식 - 화면이 이동되지 않고 background에서 서버와 통신이 이루어짐.
		$.ajax({
			type : "post",
			url : "${path}/test/doF",
			success : function(result){
				$("#result").html("상품명: " + result.name + ", 가격: " + result.price);
			}
		});
	}
</script>
</head>
<body>
	<%@ include file = "include/menu.jsp" %>
	<h2>링크 테스트</h2>
	<!-- el 'path' : 컨텍스트 path -->
	<!-- test라는 가상 디렉토리 아래 do* url 생성, 링크를 클릭하면 서블릿 요청실행. -->
	<a href="${path }/test/doA">doA</a><br>
	<a href="${path }/test/doB">doB</a><br>
	<a href="${path }/test/doC">doC</a><br>
	<a href="${path }/test/doD">doD</a><br>
	<!-- href="#" : 페이지가 이동되지 않음. -->
	<a href="#" onclick="doF()">doF</a><br>
	<div id="result"></div>
</body>
</html>