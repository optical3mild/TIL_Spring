package com.example.spring02.model.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.board.dto.ReplyDTO;

//dao bean
@Repository
public class ReplyDAOImpl implements ReplyDAO {

	//의존관계 주입
	@Inject
	SqlSession sqlSession;
	
	//댓글 목록
	@Override
	public List<ReplyDTO> list(int bno) {
		return sqlSession.selectList("reply.listReply", bno);
	}

	@Override
	public int count(int bno) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 댓글 쓰기
	@Override
	public void create(ReplyDTO dto) {
		sqlSession.insert("reply.insertReply", dto);

	}

}
