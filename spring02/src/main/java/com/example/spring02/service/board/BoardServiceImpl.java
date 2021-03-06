package com.example.spring02.service.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring02.model.board.dao.BoardDAO;
import com.example.spring02.model.board.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Inject
	BoardDAO boardDao;
	
	//첨부파일 레코드 삭제
	@Override
	public void deleteFile(String fullName) {
		boardDao.deleteFile(fullName);
	}
	
	//첨부파일 목록을 리턴
	@Override
	public List<String> getAttach(int bno) {
		return boardDao.getAttach(bno);
	}

	// 하나의 메서드에 두가지 동작 --> 트랜젝션 처리 필요
	//1. 글쓰기 - 게시물 번호 생성
	//2. 첨부파일 등록 - 게시물 번호 사용
	@Transactional
	@Override
	public void create(BoardDTO dto) throws Exception {
		//board 테이블에 레코드 추가
		boardDao.create(dto);
		
		//attach 테이블에 레코드 추가
		String[] files = dto.getFiles();
		if(files == null) return; //첨부파일이 없으면 skip
		for(String name : files) {
			boardDao.addAttach(name); // attach 테이블에 insert
		}
	}

	// 게시물 가져오기
	@Override
	public BoardDTO read(int bno) throws Exception {
		return boardDao.read(bno);
	}

	// 트랜젝션 처리
	@Transactional
	@Override
	public void update(BoardDTO dto) throws Exception {
		// board 테이블 수정
		boardDao.update(dto);
		
		// attach 테이블 수정
		String[] files = dto.getFiles();
		logger.info("files : " + dto.getFiles());
		if(files == null) return;
		for(String name : files) {
			logger.info("name : "+ name);
			boardDao.updateAttach(name, dto.getBno());
		}
	}

	@Transactional
	@Override
	public void delete(int bno) throws Exception {
		// 1. 전부 삭제하는 경우 : 모든 테이블의 레코드와 해당 파일을 삭제
		//	- reply레코드 삭제
		//	- attach레코드 삭제
		//  - 첨부파일 삭제
		//	- board레코드 삭제
		// 2. 해당 게시물을 숨김처리 : 레코드에 필드를 추가하고 삭제요청 시 레코드값을 업데이트 한다.
		boardDao.delete(bno);
	}

	@Override
//	public List<BoardDTO> listAll(int start, int end) throws Exception {
	public List<BoardDTO> listAll(String search_option, String keyword, int start, int end) throws Exception {
		return boardDao.listAll(search_option, keyword, start, end);
	}

	//조회수 증가 처리
	// session을 이용하여 조회수 증가 제한
	@Override
	public void increaseViewcnt(int bno, HttpSession session) throws Exception {
		long update_time = 0;
		if(session.getAttribute("update_time_"+bno) != null) {
			// 최근에 조회수를 올린 시간
			update_time = (long)session.getAttribute("update_time_"+bno);
		}
		long current_time = System.currentTimeMillis();
		// 일정시간이 경과한 후 조회수 증가처리
		if(current_time - update_time > 5*1000)	{
			// 조회수 증가 처리
			boardDao.increateViewcnt(bno);
			// 조회수를 올린 시간 저장
			session.setAttribute("update_time_"+bno, current_time);
		}
	}

	//레코드 총 갯수
	@Override
//	public int countArticle() throws Exception {
	public int countArticle(String search_option, String keyword) throws Exception {
//		return boardDao.countArticle();
		return boardDao.countArticle(search_option, keyword);
	}

}
