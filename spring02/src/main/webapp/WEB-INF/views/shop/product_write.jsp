<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- spring02 project header -->
<%@ include file="../include/header.jsp" %>
<style>
	table, td {
		border : none;
	}
</style>
</head>
<body>
<!-- spring02 project menu -->
<%@ include file="../include/admin_menu.jsp" %>
<h2>상품등록</h2>
<form name="form1" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>상품명</td>
			<td>
				<input name="product_name" id="product_name">
			</td>
		</tr>
		<tr>
			<td>가격</td>
			<td>
				<input name="price" id="price">
			</td>
		</tr>
		<tr>
			<td>상품설명</td>
			<td>
				<textarea rows="5" cols="60" name="description" id="description"></textarea>
			</td>
		</tr>
		<tr>
			<td>상품이미지</td>
			<td>
				<input type="file" name="file1" id="file1">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<!-- onclick에 'javascript:' 생략가능 -->
			<!-- <input type="button" value="등록" onclick="javascript:product_write()"> -->
				<input type="button" value="등록" onclick="product_write()">
				<input type="button" value="목록" onclick="location.href='${path}/admin/product/list.do'">
			</td>
		</tr>
	</table>
</form>
</body>
<script src="${path}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	
	//id가 description인 태그에 ckeditor를 적용시킴. 이미지 업로드 안됨.
	//CKEDITOR.replace("description"); //태그의 id와 name을 모두 참조하여 변경
	
	//이미지 업로드를 할 경우
	CKEDITOR.replace("description", {
		// 이미지 업로드 탭 기능 활성화, 파일업로드 컨트롤러 작업필요.
		filebrowserUploadUrl : "${path}/imageUpload.do"
	});
	
	function product_write() {
		//태그를 name으로 조회할 경우 : 계층구조를 따라가야 함.
		//var product_name = document.form1.product_name.value;
		
		//태그를 id로 조회할 경우
		var product_name = $("#product_name").val();
		
		var price = $("#price").val();
		var description = $("#description").val();
		if(product_name == "") { //빈값의 경우
			alert("상품이름을 입력하세요");
			$("#product_name").focus(); // 입력포커스 이동
			return; //함수 종료, 폼 데이터를 제출하지 않음.
		}
		if(price == "") {
			alert("가격을 입력하세요");
			$("#price").focus();
			return;
		}
		//ckeditor를 사용하기 위해 주석처리
		//if(description == "") {
		//	alert("상품설명을 입력하세요");
		//	$("#description").focus();
		//	return;
		//}
		//폼 데이터를 받을 주소
		document.form1.action="${path}/shop/product/insert.do";
		//폼 데이터를 서버에 전송
		document.form1.submit();
	}
</script>
</html>