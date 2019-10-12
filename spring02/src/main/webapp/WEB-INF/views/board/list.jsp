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
<h2>게시판</h2>

<!-- 세션을 확인하여 버튼을 숨기거나 보여줌 -->
<%-- <c:if test="${sessionScope.userid != null}"> --%>
	<button type="button" id="btnWrite">글쓰기</button>
<%-- </c:if> --%>

${map.count }개의 게시물이 있습니다<br>
<table>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>이름</th>
		<th>날짜</th>
		<th>조회수</th>
	</tr>
	<!-- foreach var="개별데이터" items="집합데이터" -->
	<c:forEach var="row" items="${map.list }">
		<tr>
			<td>${row.bno }</td>
			<td>${row.title }</td>
			<td>${row.name }</td>
			<td>
				<fmt:formatDate value="${row.regdate }" pattern="yyyy-MM-dd HH:mm:ss" />
			</td>
			<td>${row.viewcnt }</td>
		</tr>
	</c:forEach>
</table>
</body>
<script type="text/javascript">
$(function(){
	$("#btnWrite").click(function(){
		location.href="${path}/board/write.do";
	})	
})
</script>
</html>