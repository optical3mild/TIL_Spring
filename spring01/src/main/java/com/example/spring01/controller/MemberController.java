package com.example.spring01.controller;

import java.util.Date;
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
		//System.out.println(dto);
		memberService.insertMember(dto);
		//목록을 보여주는 페이지의 컨트롤러 메소드로 연결.
		return "redirect:/member/list.do";
	}
	
	// userid를 받아 상세화면 페이지로 포워딩.
	@RequestMapping("member/view.do")
//	@RequestParam : request.getParameter("변수명")대체. 생략가능.
//	public String view(@RequestParam String userid, Model model) {
	public String view(String userid, Model model) {
		//모델에 자료 저장
		System.out.println("컨트롤 : "+ userid);
		model.addAttribute("dto", memberService.viewMember(userid));
		// view.jsp로 포워딩
		return "member/view";
	}
	
	@RequestMapping("member/update.do")
	public String update(MemberDTO dto, Model model) { // java.util.Date
		// 비밀번호 체크
		boolean result = memberService.checkPw(dto.getUserid(), dto.getPasswd());
		if(result) { // 비밀번호가 맞는 경우
			// 회원정보 수정
			memberService.updateMember(dto);
			// 수정 후 목록으로 이동
			return "redirect:/member/list.do";
		} else { // 비밀번호가 틀린경우
			// 사용자가 입력한 정보를 버리지 않고 model에 저장하여 포워딩과 함께 페이지에 뿌림.
			model.addAttribute("dto", dto);
			model.addAttribute("join_date", memberService.viewMember(dto.getUserid()).getJoin_date());
			model.addAttribute("message", "비밀번호를 확인하세요.");
			return "member/view"; // forward
		}
	}
}
