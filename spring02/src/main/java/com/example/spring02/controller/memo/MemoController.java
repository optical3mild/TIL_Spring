package com.example.spring02.controller.memo;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.memo.dto.MemoDTO;
import com.example.spring02.service.memo.MemoService;

@Controller
@RequestMapping("memo/*") // 공통적인 url패턴 - 공통패턴과 메소드별 세부 패턴으로 나누어 작성할 수 있다.
public class MemoController {

	@Inject // 의존관계 주입
	MemoService memoService;
	
	//방법 1. 전체코드 - modelandview를 선언하고 메소드를 이용하여 저장, 리턴
	@RequestMapping("list.do") // 세부적인 url pattern
	// ModelAndView : model과 view의 정보를 같이 가지고 있는 클래스
	//	> 리턴타입 String으로 포워딩할 경로를 지정하고, Model객체에 data를 저장하여 한번에 처리하는 방식대신에 사용.
	public ModelAndView list(ModelAndView mav) {
		List<MemoDTO> items = memoService.list();
		// servlet-context에 저장된 접두사, 접미사를 이용하여 전체경로가 완성됨.
		mav.setViewName("memo/memo_list"); // 포워딩할 view의 이름
		mav.addObject("list", items); // 전달할 데이터
		return mav;
	}
	
//	//방법 2. 축약된 코드 - 리턴에 modelandview를 저장할 값과 함께 생성하여 리턴.
//	@RequestMapping("list.do") // 세부적인 url pattern
//	public ModelAndView list() {
//		List<MemoDTO> items = memoService.list();
//		return new ModelAndView("memo/memo_list", "list", items);
//	}

	@RequestMapping("insert.do")	
//	public String insert(@ModelAttribute MemoDTO dto) {
//	@ModelAttribute : form에서 전달한 값을 받아 자동으로 dto를 생성하고 받은 값을 저장. 생략가능.
	public String insert(MemoDTO dto) {
		System.out.println(dto.toString());
		memoService.insert(dto);
		return "redirect:/memo/list.do";
	}
	
	// 글번호가 PathVariable로 url에 포함되어 전달됨.
	@RequestMapping("view/{idx}")
	public ModelAndView view(@PathVariable int idx, ModelAndView mav) {
		// 포워딩할 뷰의 이름
		mav.setViewName("memo/view");
		// 전달할 데이터
		mav.addObject("dto", memoService.memo_view(idx));
		return mav;
	}
	
	@RequestMapping("update/{idx}")
	// @ModelAttribute : form에서 전달한 값을 받아 자동으로 dto를 생성하고 받은 값을 저장. 생략가능.
	public String update(@PathVariable int idx, @ModelAttribute MemoDTO dto) {
		// 메모 수정
		memoService.update(dto);
		// 수정 완료 후 목록으로 이동.
		return "redirect:/memo/list.do";
	}
	
	@RequestMapping("delete/{idx}")
	public String delete(@PathVariable int idx) {
		// 레코드 삭제 처리
		memoService.delete(idx);
		// 삭제 완료 후 목록으로 이동
		return "redirect:/memo/list.do";
	}
}
