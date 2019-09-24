<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="menu">
	<tr>
		<td>
			<a href="${path }">Home</a>	|	
			<a href="${path }/memo/list.do">메모장</a>	|	
			<a href="${path }/upload/uploadForm">업로드테스트</a>	|	
			<a href="${path }/shop/product/list.do">상품목록</a>	|	
		</td>
		<td style="align:right">
			<a href="${path }/member/login.do">로그인</a>
		</td>
	</tr>
</table>



<hr>