package com.example.spring03_boot.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03_boot.model.dto.GuestbookDTO;
import com.example.spring03_boot.service.GuestbookService;

@Controller // controller bean으로 등록
public class GuestbookController {
	
	@Inject // 서비스 빈 inject
	GuestbookService guestbookService;
	
	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav) {
		mav.setViewName("list");	// view의 이름
		List<GuestbookDTO> list = guestbookService.list();
		mav.addObject("list", list);	// view에 전달할 데이터
		return mav; // view로 이동
	}
}
