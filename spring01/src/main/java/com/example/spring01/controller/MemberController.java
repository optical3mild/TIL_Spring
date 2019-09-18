package com.example.spring01.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

		System.out.println(list.toString());
		//model에 결과를 담음
		model.addAttribute("list", list);
		
		// WEB-INF/views/member/member_list.jsp로 포워딩 - model도 같이 전달.
		return "member/member_list";
	}
	
	// write페이지로 포워딩
	@RequestMapping("member/write.do")
	public String write() {
		// WEB-INF/views/member/write.jsp로 포워딩
		return "member/write";
	}

	
	// write페이지에서 db로 회원가입정보 업로드
	@RequestMapping("member/insert.do")
	// 방법 1. HttpServletRequest사용
	//public String insert(HttpServletRequest request) {
	//	String userid = request.getParameter("userid");
	// ------------------------------------------------------
	// 방법 2.
	// - form의 각 항목 inputTag의 name과 dto의 필드명이 동일하도록 작성.
	// - 매개변수로 MemberDTO사용 : dto를 자동으로 생성하고 form에서 넘어온 값을 자동으로 저장.
	// - @ModelAttribute : 생략가능. 해당 객체를 폼에서 전달되는 값을 저장하는 객체로 지정.
	// - @ModelAttribute / @RequestParam
	//      > 매개변수를 여러개로 하여 각 태그를 @RequestParam을 이용하여 따로 받을 수 있음. 
	//		> 이 경우에는 코드에서 dto를 따로 호출하여 저장하는 과정이 필요함.  = 코드가 길어짐.
	public String insert(@ModelAttribute MemberDTO dto) {
		System.out.println(dto);
		//목록을 보여주는 페이지의 컨트롤러 메소드로 연결.
		return "redirect:/member/list.do";
	}
}
