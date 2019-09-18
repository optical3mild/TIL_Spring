package com.example.spring01.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring01.model.dto.MemberDTO;
import com.example.spring01.service.MemberService;

@Controller //스프링에서 관리하는 컨트롤러 빈으로 등록
public class MemberController {
	//MemberService 인스턴스를 주입시킴.
	@Inject
	MemberService memberService;
	
	@RequestMapping("member/list.do") // url Mapping
	public String memberList(Model model) {
		//MemberService호출.
		List<MemberDTO> list = memberService.memberList();

		//model에 결과를 담음
		model.addAttribute("list", list);
		
		// WEB-INF/views/member/member_list.jsp로 포워딩 - model도 같이 전달.
		return "member/member_list";
	}
}
