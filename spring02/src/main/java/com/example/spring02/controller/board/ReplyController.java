package com.example.spring02.controller.board;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring02.model.board.dto.ReplyDTO;
import com.example.spring02.service.board.ReplyService;

//@ResponseBody를 붙이지 않아도 뷰가 아닌 데이터 리턴가능 = 뷰로 돌아가지 않아도 됨. spring4.0부터 사용가능.
@RestController
@RequestMapping("reply/*")
public class ReplyController {
	
	@Inject
	ReplyService replyService;

	@RequestMapping("insert.do") //세부적인 url pattern
	public void insert(ReplyDTO dto, HttpSession session) {
		// 댓글 작성자 id
		String userid = (String)session.getAttribute("userid");
		dto.setReplyer(userid);
		// 댓글이 테이블에 저장됨
		replyService.create(dto);
		// jsp페이지로 가거나 데이터를 리턴하지 않음 
	}
}
