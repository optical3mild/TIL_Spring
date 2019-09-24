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
<h2>장바구니</h2>
<c:choose>
	<c:when test="${map.count == 0}">
		장바구니가 비었습니다.
	</c:when>
	<c:otherwise>
		<form name="form1" method="post" action="${path }/shop/cart/update.do">
			<table>
				<tr>
					<th>상품명</th>
					<th>단가</th>
					<th>수량</th>
					<th>금액</th>
					<th>&nbsp;</th>
				</tr>
				<c:forEach var="row" items="${map.list }">
					<tr>
						<td>${row.product_name }</td>
						<td>${row.price }</td>
						<td>
							<input type="number" name="amount" value="${row.amount }">
							<input type="hidden" name="product_id" value="${row.product_id }">
						</td>
						<td>${row.money }</td>
						<td>
							<a href="${path }/shop/cart/delete.do?cart_id=${row.cart_id }">삭제</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</form>
		
	</c:otherwise>
</c:choose>
</body>
</html>