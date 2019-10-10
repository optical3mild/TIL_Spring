<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- spring02 project header -->
<%@ include file="../include/header.jsp" %>

	<style type="text/css">
	.fileDrop {
		width: 100%;
		height: 200px;
		border: 1px dotted blue;
	}
	
	small {
		margin-left: 3px;
		font-weight: bold;
		color: gray;
	}

	</style>
</head>
<body>
<!-- spring02 project menu -->
<%@ include file="../include/menu.jsp" %>
<h2>Ajax File Upload</h2>

<!-- 파일을 업로드할 영역 -->
<div class="fileDrop"></div>

<!-- 업로드된 파일 목록을 출력할 영역 -->
<div class="uploadedList"></div>

</body>
<script>
//$(function(){}) == $(document).ready(function(){})
$(function(){
	// 드래그 기본 효과를 막음
	$(".fileDrop").on("dragenter dragover", function(event){
		event.preventDefault();
	});

	$(".fileDrop").on("drop", function(event){
		// drop이 될 때 기본 효과를 막음.
		event.preventDefault();
//>>	// 첨부파일 배열
		var files = event.originalEvent.dataTransfer.files;
		var file = files[0]; // 첫번째 첨부파일
		var formData = new FormData(); // 폼객체
		formData.append("file", file); // 폼에 file변수 추가.
		
		//서버에 파일 업로드(백그라운드에서 실행됨)
		//contentType: false --> multipart/form-data로 처리됨 (= form tag의 enctype속성 설정과 동일)
		// > <form mehthod="post" enctype="multipart/form-data">
		$.ajax({
			type: "post",
			url: "${path}/upload/uploadAjax",
			data: formData,
			dataType: "text",
			processData: false,
			contentType: false,
			success: function(data, status, req){
				console.log("data: "+ data); // 업로드 된 파일 이름
				console.log("status: "+ status); // 성공, 실패여부
				console.log("req: "+ req.status); // 요청코드 값
			}
		});
	});
});
</script>
</html>