package com.example.spring02.controller.product;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.service.shop.ProductService;

@Controller // controller bean 으로 등록
@RequestMapping("/shop/product/*") // 공통된 url패턴
public class ProductController {
	
	@Inject // 서비스 객체 주입
	ProductService productService;
	
	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav) {
		// 뷰의 경로
		mav.setViewName("/shop/product_list");
		// 뷰에 전달할 데이터 저장.
		mav.addObject("list", productService.listProduct());
		// product_list.jsp로 데이터와 함께 포워딩.
		return mav;
	}
	
	// detail/상품번호
	@RequestMapping("detail/{product_id}")
	public ModelAndView detail(@PathVariable int product_id, ModelAndView mav) {
		// 뷰의 이름
		mav.setViewName("/shop/product_detail");
		// 뷰에 전달할 데이터 저장.
		mav.addObject("dto", productService.detailProduct(product_id));
		// product_detail.jsp로 포워딩됨.
		return mav;
	}
}
