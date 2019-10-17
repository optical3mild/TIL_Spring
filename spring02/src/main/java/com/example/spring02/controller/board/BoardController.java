package com.example.spring02.controller.board;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.board.dto.BoardDTO;
import com.example.spring02.service.board.BoardService;
import com.example.spring02.service.board.Pager;

@Controller	//controller bean
@RequestMapping("board/*")	//공통적인 url pattern
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardService boardService;
	
	@RequestMapping("list.do")	// 세부적인 url pattern
	public ModelAndView list(
			@RequestParam(defaultValue="name") String search_option,	//검색 옵션
			@RequestParam(defaultValue="") String keyword,				//검색 키워드
			@RequestParam(defaultValue="1") int curPage					//요청 페이지번호
		) throws Exception {
		logger.info("option: " + search_option);
		logger.info("keyword: " + keyword);
		
		//레코드 갯수 계산
		//int count = boardService.countArticle(); //전체 레코드 수
		int count = boardService.countArticle(search_option, keyword); //조건에 따른 레코드 수
		
		//페이지 관련 설정
		Pager pager = new Pager(count, curPage);
		int start =  pager.getPageBegin();
		int end = pager.getPageEnd();
		
		//List<BoardDTO> list = boardService.listAll(start, end);	// 게시물 목록
		List<BoardDTO> list = boardService.listAll(search_option, keyword, start, end);	// 조건에 따른 게시물 목록
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("list", list);		// map에 자료 저장
		map.put("count", count);	// 게시물 총 갯수
		
		map.put("pager", pager);	// 페이지 네비게이션을 위한 변수
		
		// 페이지 이동 시 검색결과가 초기화되지 않도록 값을 전달
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		
		mav.setViewName("board/list"); // 포워딩할 view의 이름
		mav.addObject("map", map);	//ModelAndView에 map을 저장
		
		return mav; // board/list.jsp로 이동
	}
	
	// sol1. 세션체크하여 로그인여부에 따라 분기
//	@RequestMapping("write.do")
//	public String write(HttpSession session) {
//		if(session.getAttribute("userid") == null) {
//			return "member/login";
//		}
//		// 글쓰기 폼 페이지로 이동
//				return "board/write";
//	}
	
	@RequestMapping("write.do")
	public String write() {
		// 글쓰기 폼 페이지로 이동
		return "board/write";
	}
	
	// write.jsp에서 입력한 내용들이 BoardDTO에 저장됨
	@RequestMapping("insert.do")
	// @ModelAttribute 생략가능
	public String insert(@ModelAttribute BoardDTO dto, HttpSession session) throws Exception{
		// 세션에서 사용자 아이디를 가져옴 
		//		: 화면에서 넘겨주는 폼 데이터에는 아이디가 없음. 저장을 위해서 세션에서 받아야 한다.
		String writer = (String)session.getAttribute("userid");
		dto.setWriter(writer);
		// 레코드 저장
		boardService.create(dto);
		// 게시물 목록으로 이동
		return "redirect:/board/list.do";
	}
	
	@RequestMapping("view.do")
	public ModelAndView view(int bno, HttpSession session) throws Exception{
		// 조회수 증가 처리
		boardService.increaseViewcnt(bno, session);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/view");	//포워딩할 뷰의 이름
		mav.addObject("dto", boardService.read(bno));	// 해당 게시물을 가져와 자료 저장
		return mav; // views/board/view.jsp로 넘어가서 출력됨
	}
	
	//첨부파일 목록을 리턴
	// ArrayList를 json배열로 변환하여 리턴
	@RequestMapping("getAttach/{bno}")
	@ResponseBody // view가 아닌 데이터 자체를 리턴
	public List<String> getAttach(@PathVariable int bno) {
		return boardService.getAttach(bno);
	}
	
	//게시물 내용 수정
	@RequestMapping("update.do")
	public String update(BoardDTO dto) throws Exception {
		if(dto != null) {
			boardService.update(dto); //레코드 수정
		}
		// 수정 완료 후 목록으로 이동
		return "redirect:/board/list.do";
		// 수정 후 해당글 화면으로 이동
		//return "redirect:/board/view.do?bno="+dto.getBno();
	}
	
	//게시물 삭제
	@RequestMapping("delete.do")
	public String delete(int bno) throws Exception {
		boardService.delete(bno); //삭제처리
		return "redirect:/board/list.do";	//목록으로 이동
	}
}
