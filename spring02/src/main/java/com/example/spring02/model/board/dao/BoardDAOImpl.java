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
		sqlSession.delete("board.deleteFile", fullName);
	}

	// 첨부파일 리스트
	@Override
	public List<String> getAttach(int bno) {
		return sqlSession.selectList("board.getAttach", bno);
	}

	@Override
	public void addAttach(String fullName) {
		sqlSession.insert("board.addAttach", fullName);

	}

	// 첨부파일 정보 수정
	@Override
	public void updateAttach(String fullName, int bno) {
		Map<String, Object> map = new HashMap<>();
		map.put("fullName", fullName); //첨부파일 이름
		map.put("bno", bno); //게시물 번호
		sqlSession.insert("board.updateAttach", map);
	}

	@Override
	public void create(BoardDTO dto) throws Exception {
		sqlSession.insert("board.insert", dto);
	}

	//게시물 레코드 삭제 --> 숨김처리(필드값 업데이트)
	@Override
	public void delete(int bno) throws Exception {
		//sqlSession.delete("board.delete", bno);
		sqlSession.update("board.delete_record", bno);
	}

	// 게시물 목록 리턴
	@Override
//	public List<BoardDTO> listAll(int start, int end) throws Exception {
	public List<BoardDTO> listAll(String search_option, String keyword, int start, int end) throws Exception {
		// Map<key의 자료형, value의 자료형>
		Map<String, Object> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", "%"+keyword+"%");
		map.put("start", start);
		map.put("end", end);
		// mapper에는 2개 이상의 값을 전달 할 수 없음(dto또는 map사용)
		return sqlSession.selectList("board.listAll", map);
	}

	//조회수 증가 처리
	@Override
	public void increateViewcnt(int bno) throws Exception {
		sqlSession.update("board.increaseViewcnt", bno);
	}

	//레코드 총 갯수
	@Override
//	public int countArticle() throws Exception {
	public int countArticle(String search_option, String keyword) throws Exception {
//		return sqlSession.selectOne("board.countArticle");
		
		// 조건검색
		Map<String, String> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", "%"+keyword+"%");
		return sqlSession.selectOne("board.countArticle", map);
	}

	//레코드 조회
	@Override
	public BoardDTO read(int bno) throws Exception {
		return sqlSession.selectOne("board.read", bno);
	}

	//레코드 수정
	@Override
	public void update(BoardDTO dto) throws Exception {
		sqlSession.update("board.update", dto);
		
	}

}
