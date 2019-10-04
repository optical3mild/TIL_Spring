package com.example.spring02.controller.chart;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// controller bean으로 등록
@Controller
// 공통적인 url mapping
@RequestMapping("chart/*")
public class JFreeChartController {
	
	// 세부적인 url mapping
	@RequestMapping("chart1.do")
	// HttpServletResponse : JFreeChart를 이용하기 위한 매개변수 형. servlet에서 사용하는 일반적 매개변수 형
	public void createChart1(HttpServletResponse response) {
		
	}
	
}
