package com.example.spring02.model.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.board.dto.BoardDTO;

@Repository //dao bean
public class BoardDAOImpl implements BoardDAO {

	@Inject //의존관계 주입
	SqlSession sqlSession; // mybatis호출하기 위해 주입. root-context.xml에 정의되어 있음
	
	@Override
	public void deleteFile(String fullName) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getAttach(int bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAttach(String fullName) {
		sqlSession.insert("board.addAttach", fullName);

	}

	@Override
	public void updateAttach(String fullName, int bno) {
		// TODO Auto-generated method stub

	}

	@Override
	public void create(BoardDTO dto) throws Exception {
		sqlSession.insert("board.insert", dto);
	}

	@Override
	public void delete(int bno) throws Exception {
		// TODO Auto-generated method stub

	}

	// 게시물 목록 리턴
	@Override
	public List<BoardDTO> listAll(int start, int end) throws Exception {
		// Map<key의 자료형, value의 자료형>
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		// mapper에는 2개 이상의 값을 전달 할 수 없음(dto또는 map사용)
		return sqlSession.selectList("board.listAll", map);
	}

	@Override
	public void increateViewcnt(int bno) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public int countArticle() throws Exception {
		return sqlSession.selectOne("board.countArticle");
	}

}
