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
<%@ include file="../include/admin_menu.jsp" %>
<div id ="chart_div"></div>
<button id ="btn" type ="button" onclick="drawChart()">refresh</button>
</body>
<!-- 구글 차트 호출을 위한 js파일 -->
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
	// 구글차트 라이브러리 로딩
	google.load("visualization", "1", {
		"packages":["corechart"]
	});
	//	라이브러리 로딩이 완료되면 drawChart 함수 호출
	google.setOnLoadCallback(drawChart);
	function drawChart(){
		// 차트 그리기에 필요한 json데이터 로딩
		var jsonData = $.ajax({
			url : "${path}/chart/cart_money_list.do",
			dataType : "json",
			async : false	// 동기식
		}).responseText;
		console.log(jsonData);
		
		// json을 dataTable로 변환
		var data = new google.visualization.DataTable(jsonData);
		console.log("dataTable : "+ data);
		
		// div에 pie차트를 그림
		// var chart = new google.visualization.PieChart(document.getElementById("chart_div"));

		// div에 line차트를 그림
		// var chart = new google.visualization.LineChart(document.getElementById("chart_div"));

		// div에 column차트를 그림
		var chart = new google.visualization.ColumnChart(document.getElementById("chart_div"));
		
		chart.draw(data, {
			title : "chart예제",
			width : 600,
			height : 440,
			
			// curveType : "function", // LineChart옵션 : 곡선처리
		});
	}
</script>
</html>