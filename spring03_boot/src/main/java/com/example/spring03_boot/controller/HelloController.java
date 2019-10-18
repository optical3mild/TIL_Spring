package com.example.spring03_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
	@RequestMapping("/hello.do")
	public ModelAndView hello(ModelAndView mav) {
		mav.setViewName("hello"); //뷰의 이름
		mav.addObject("message", "스프링 부트 애플리케이션");
		return mav;
	}

}
