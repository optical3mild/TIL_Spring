package com.example.spring02.service.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring02.model.board.dao.BoardDAO;
import com.example.spring02.model.board.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	BoardDAO boardDao;
	
	@Override
	public void deleteFile(String fullName) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getAttach(int bno) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public void update(BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int bno) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BoardDTO> listAll(int start, int end) throws Exception {
		return boardDao.listAll(start, end);
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
	public int countArticle() throws Exception {
		return boardDao.countArticle();
	}

}
