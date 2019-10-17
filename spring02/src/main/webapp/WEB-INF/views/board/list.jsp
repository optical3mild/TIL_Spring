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
<!-- 검색폼 -->
<form name="form1" method="post" action="${path }/board/list.do">
	<!-- 컨트롤러에서 받은 검색조건 값에 따라 option선택 -->
	<select name="search_option">
		<option value="name" <c:if test="${map.search_option == 'name' }">selected</c:if> >이름</option>
		<option value="title" <c:if test="${map.search_option == 'title' }">selected</c:if> >제목</option>
		<option value="content" <c:if test="${map.search_option == 'content' }">selected</c:if> >내용</option>
		<option value="all" <c:if test="${map.search_option == 'all' }">selected</c:if> >이름+제목+내용</option>
	</select>
	<!-- 컨트롤러에서 받은 키워드 값을 입력 -->
	<input name="keyword" value="${map.keyword }">
	<input type="submit" value="조회">
</form>

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
	<!-- foreach var="개별데이터" items="집합데이터" : 횟수가 가변적 -->
	<c:forEach var="row" items="${map.list }">
		<tr>
			<td>${row.bno }</td>
			<td>
				<a href="${path}/board/view.do?bno=${row.bno}">${row.title }</a>
				<!-- 댓글 수 출력 -->
				<c:if test="${row.cnt > 0}">
					<span style="color:red;"> (${row.cnt })</span>
				</c:if>
			</td>
			<td>${row.name }</td>
			<td>
				<fmt:formatDate value="${row.regdate }" pattern="yyyy-MM-dd HH:mm:ss" />
			</td>
			<td>${row.viewcnt }</td>
		</tr>
	</c:forEach>
	<!-- 페이지 네비게이션 출력 -->
	<tr>
		<td colspan = "5" align="center">
			<!-- [처음] : 첫번째 블록인 경우 표시되지 않음-->
			<c:if test="${map.pager.curPage > 1 }">
				<a href="#" onclick="list('1')">[처음]</a>
			</c:if>

			<!-- [이전] : 첫번째 블록인 경우 표시되지 않음-->
			<c:if test="${map.pager.curBlock > 1 }">
				<a href="#" onclick="list('${map.pager.prevPage}')">[이전]</a>
			</c:if>
			
			<!-- forEach var="개별데이터" begin="시작" end="끝" : 횟수가 고정적-->
			<c:forEach var="num" begin="${map.pager.blockBegin }" end="${map.pager.blockEnd }">
				<c:choose>
					<c:when test="${num == map.pager.curPage }">
						<!-- 현재 페이지인 경우 숫자만 출력하고 색을 넣어 현재페이지 표시 -->
						<span style="color:red;">${num }</span>
					</c:when>
					<c:otherwise>
						<a href="#" onclick="list('${num}')">${num }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<!-- [다음] : 마지막 블록인 경우 표시되지 않음-->
			<c:if test="${map.pager.curBlock < map.pager.totBlock}">
				<a href="#" onclick="list('${map.pager.nextPage}')">[다음]</a>
			</c:if>

			<!-- [끝] : 마지막 블록인 경우 표시되지 않음-->
			<c:if test="${map.pager.curPage < map.pager.totPage}">
				<a href="#" onclick="list('${map.pager.totPage}')">[끝]</a>
			</c:if>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
$(function(){
	$("#btnWrite").click(function(){
		location.href="${path}/board/write.do";
	})
})
function list(page){
	//location.href = "${path}/board/list.do?curPage="+page;
	location.href = "${path}/board/list.do?search_option=${map.search_option}"
						+"&keyword=${map.keyword}&curPage="+page;
}
</script>
</html>