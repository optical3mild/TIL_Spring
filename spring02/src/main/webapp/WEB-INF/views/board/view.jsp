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
	width: 600px;
	height: 100px;
	border: 1px dotted gray;
	background-color: gray;
}
</style>
</head>
<body>
<!-- spring02 project menu -->
<%@ include file="../include/menu.jsp" %>
<h2>게시물 보기</h2>
<form id="form1" name="form1" method="post" action="${path }/board/insert.do">
	<div>
		제목 <input name="title" id="title" size="80" value="${dto.title }" placeholder="제목을 입력하세요">
	</div>
	<div>
		조회수 : ${dto.viewcnt }
	</div>
	<div style="width:800px;">
		내용 <textarea id="content" name="content" rows="3" cols="80" placeholder="내용을 입력하세요">${dto.content }</textarea>
	</div>
	<div> 첨부파일을 등록하세요
		<div class="fileDrop"></div>
		<div id="uploadedList"></div>
	</div>
	<div style="width:700px; text-align:center;">
		<!-- 수정/삭제 기능을 위해 글번호를 받아 hidden으로 보관 -->
		<input type="hidden" name="bno" value="${dto.bno }">
		
		<!-- 글 작성자에게만 수정/삭제 버튼 표시 -->
		<c:if test="${sessionScope.userid == dto.writer }">
			<button type="button" id="btnUpdate">수정</button>
			<button type="button" id="btnDelete">삭제</button>
		</c:if>
		<button type="button" id="btnList">목록</button>
	</div>
</form>
</body>
<!-- 페이지에서 사용할 javascript 메서드 -->
<script src="${path }/include/js/common.js"></script>

<!-- CKEditor -->
<script src="${path }/ckeditor/ckeditor.js"></script>

<script type="text/javascript">
$(function(){
	listAttach(); //첨부파일 목록 불러옴
	
	//첨부파일 삭제
	// id가 uploadedList인 태그의 class가 file_del인 태그 클릭
	$("#uploadedList").on("click", ".file_del", function(e){
		var that = $(this); //클릭한 태그
		$.ajax({
			type : "post",
			url : "${path}/upload/deleteFile",
			data : {fileName : $(this).attr("data-src")},
			dataType : "text",
			success : function(result) {
				if(result == "deleted")	{
					//화면에서 태그 제거
					that.parent("div").remove();
				}
			}
		})
	})
	
	//CKEDTOR적용
	CKEDITOR.replace("content", {
		//파일업로드를 처리할 주소
		filebrowserUploadUrl: "${path}/imageUpload.do",
		height: "300px"
	});
	
	$("#btnSave").click(function(){
		var str = "";
		// uploadedList 내부의 .file 태그를 각각 반복
		$("#uploadedList .file").each(function(i) { // i=index 해당하는 태그 목록의 순서대로 index 생성
			//hidden 태그 수정하여 추가. 
			//$(this) = 현재 작업중인 태그
			str += "<input type='hidden' name='files["+i+"]' value='" + $(this).val() +"'>";	
		});
		// 폼에 hidden 태그들을 붙여 폼의 정보(제목, 내용)와 같이 hidden 태그를 전송
		$("#form1").append(str);
		document.form1.submit();
	});
	
	$(".fileDrop").on("dragenter dragover", function(e){
		//기본효과 막음 : drop의 기본효과 = 해당 파일을 브라우저에서 보여줌
		e.preventDefault();
	});

	$(".fileDrop").on("drop", function(e){
		//기본효과 막음
		e.preventDefault();
		//첫번째 첨부파일 : 다중업로드 불가
		var files = e.originalEvent.dataTransfer.files;
		var file = files[0];
		//폼 데이터에 첨부파일 추가
		var formData = new FormData();
		formData.append("file", file);
		$.ajax({
			url: "${path}/upload/uploadAjax",
			data: formData,
			dataType: "text", // 웹에서 정보를 넘기므로 text형태로 넘김
			processData: false,	// 진행률 옵션
			contentType: false,
			type: "post",
			success: function(data) {
				console.log(data);
				//data : 업로드한 파일 정보, http상태코드
				var fileInfo = getFileInfo(data);
				console.log(fileInfo);
				var html = "<a href='" +fileInfo.getLink+"'>" + fileInfo.fileName+"</a><br>";
				
				// hidden tag생성
				html += "<input type = 'hidden' class='file' value='" + fileInfo.fullName+ "'>";
				$("#uploadedList").append(html);
			}
		});
	});
});

//첨부파일 리스트를 출력하는 함수
function listAttach() {
	$.ajax({
		type: "post",
		url: "${path}/board/getAttach/${dto.bno}",	// url로 게시물 번호를 전달
		success: function(list){
			console.log(list);
			//list : json 
			// $(선택자) : 선택자에는 태그/객체가 들어갈 수 있음.
			//  - $("p") : 현재문서의 모든 p태그들
			//	- $("#result") : id가 result인 태그 
			//	- $(".info") : class가 info인 태그
			//	- $(document) : document객체 ==> $(list) : list객체
			// each(function(){}) : 반복문. 각각의 요소마다 function을 실행
			$(list).each(function(){
				console.log(getFileInfo(this));
				// 첨부파일의 정보
				var fileInfo = getFileInfo(this);
				// this : 현재의 객체
				var html = "<div><a href='" + fileInfo.getLink + "'>" + fileInfo.fileName + "</a>&nbsp;&nbsp;";
				html += "<a href='#' class='file_del' data-src='"+ this +"'>[삭제]</a></div>";
				// id가 uploadedList인 태그의 마지막에 추가
				$("#uploadedList").append(html);
			});
		}
	})
}
</script>
</html>