<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>

<!-- bootstrap style 적용 -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">

<!-- summernote style 적용 -->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">

</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>메모장</h2>
<form method="post" action="${path }/memo/insert.do">
	이름 : <input name="writer" size="10"><br>
<!-- 메모 : <input name="memo" size="40">  -->
	메모 : <textarea rows="3" cols="50" name="memo" id="memo"></textarea>
	
	<input type="submit" value="확인">
</form>
<br>
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
				<td><a href="#" onclick="memo_view('${row.idx }')" />${row.memo }</td>
				<td>
					<fmt:formatDate value="${row.post_date }" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
	<!-- bootstrap script 적용 -->
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
	<!-- summernote script 적용 -->
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script>
	
	<script>
	$(function() {
		//id가 memo인 태그를 summernote로 변경.
		$("#memo").summernote({
			height: 300,
			width: 800
		});	
	});
	</script>
	
	<script>
		function memo_view(idx) {
			location.href="${path}/memo/view/"+idx;
		}
	</script>
</html>