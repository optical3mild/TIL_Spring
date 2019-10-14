package com.example.spring02.service.board;

import java.util.List;

import javax.inject.Inject;

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

	@Override
	public BoardDTO read(int bno) throws Exception {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public void increaseViewcnt() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public int countArticle() throws Exception {
		return boardDao.countArticle();
	}

}
