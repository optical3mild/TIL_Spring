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
		formData.append("file", file); // 폼에 file변수 추가(폼에 파일 추가).
		
		//서버에 파일 업로드(백그라운드에서 실행됨)
		//contentType: false --> multipart/form-data로 처리됨 (= form tag의 enctype속성 설정과 동일)
		// > <form mehthod="post" enctype="multipart/form-data">
		$.ajax({
			type: "post", // controller에 중복된 url매핑이 있지만, 방식에 따라 컨트롤러의 메소드 선택이 가능.
			url: "${path}/upload/uploadAjax",
			data: formData, // 파일이 저장된 폼 객체를 data로
			dataType: "text", // 웹에서 보내는 data는 항상 text --> 웹에서 넘어가는 값은 text로 구성해서 넘기게 됨.
			processData: false, // 진행률을 보는 옵션
			contentType: false,
			// 서버의 리턴값
			success: function(data, status, req){
				console.log("data: "+ data); // 업로드 된 파일 이름
				console.log("status: "+ status); // 성공, 실패여부
				console.log("req: "+ req.status); // 요청코드 값
				var str = "";
				if(checkImageType(data)){ // 이미지 파일
					str="<div><a href='${path}/upload/displayFile?fileName='"+getImageLink(data)+"'>";
					str+="<img src='${path}/upload/displayFile?fileName="+data+"'></a>";
				} else{ // 이미지가 아닌경우
					str="<div>";
					str+="<a href='${path}/upload/displayFile?fileName='"+data+"'>"+getOriginalName(data)+"</a>";
				}
				str+="<span data-src="+data+">[삭제]</span></div>";
				$(".uploadedList").append(str);
			}
		});
	}); // ./fileDrop 함수
	
	// 첨부파일 삭제함수
	// data: "fileName="+$(this).attr("data-src")  =  data: {fileName: $(this).attr("data-src")},
	$(".uploadedList").on("click", "span", function(event){
		//현재 클릭한 태그 $(this)
		var that = $(this);
		$.ajax({
			url: "${path}/upload/deleteFile",
			type: "post",
			// 클릭한 태그의 속성 중 data-src를 보낼 값으로 지정.
			data: {
				fileName: $(this).attr("data-src")
			},
			dataType: "text",
			success: function(result){
				// 요청이 성공적으로 수행되면 화면에서 해당 div를 삭제한다.
				if(result == "deleted"){
					that.parent("div").remove();
				}
			}
		})
	});
	
	function getOriginalName(fileName) {
		if(checkImageType(fileName)) { //이미지 파일이면 skip
			return;
		}
		var idx = fileName.indexOf("_")+1; // uuid를 제외한 파일이름
		return fileName.substr(idx);
	}
	
	function getImageLink(fileName) {
		if(!checkImageType(fileName)){ //이미지 파일이 아니면 skip
			return;
		}
		var front = fileName.substr(0,12); // 연월일 경로
		var end = fileName.substr(14);	// s_제거
		return front+end;
	}
	
	function checkImageType(fileName) {
		var pattern = /jpg|png|jpeg/i; // 정규표현식( i: 대소문자 무시)
		return fileName.match(pattern); // match() : 규칙에 맞으면 true
	}
});
</script>
</html>