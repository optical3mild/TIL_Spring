package com.example.spring02.controller.chart;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.service.chart.GoogleChartService;

// @RestController
//	- REST : REpresentitive State Transfer. 리소스(게시물, 레코드)마다 고유한 주소를 할당.
//	- RestController : Spring 4.0부터 지원
//	- ajax --> json형식으로 리턴
//		> 기본적으로 컨트롤러의 메소드의 리턴값은 jsp페이지로 넘어가는 것(redirect, forward).		
//		> Controller : @ResponseBody를 붙이면 json등의 일반 자료형으로 리턴할 수 있음.
//		  ex) ------------------------------------------------------------------------------------
//	 		@Controller
//			@RequestMapping("chart/*")
//			public class GoogleChartController {
//	
//				@ResponseBody
//				@RequestMapping("chart1.do")
//				public MemberDTO chart1() {
//					return new MemberDTO();
//				}
//			}
//		  ---------------------------------------------------------------------------------------
//		> RestController : @ResponseBody없이 일반자료형으로 리턴가능 

@RestController
@RequestMapping("chart/*")
public class GoogleChartController {
	
	@Inject
	GoogleChartService googleChartService;

	@RequestMapping("chart1.do")
	public ModelAndView chart1() {
		return new ModelAndView("chart/chart01");
	}
	
	@RequestMapping("chart2.do")
	public ModelAndView chart2() {
		return new ModelAndView("chart/chart02");
	}
	
	// view(jsp)로 넘어가지 않고 호출한 곳에 JSONObject를 리턴함.
	@RequestMapping("cart_money_list.do")
	public JSONObject cart_money_list() {
		return googleChartService.getChartData();
	}
}
