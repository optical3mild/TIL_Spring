package com.example.spring01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring01.model.dto.ProductDTO;


//ajax 처리 전용 컨트롤러(백그라운드에서 실행됨.)
//view를 리턴하는 것이 아닌 json을 리턴.
@RestController // 스프링 4.0 이상부터 사용가능.
public class SampleRestController {
	@RequestMapping("test/doF")
	public ProductDTO doF() {
		//json 데이터가 리턴됨
		return new ProductDTO("냉장고",500000);
	}
}
