package com.example.spring02.model.message.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.message.dto.MessageDTO;

@Repository // dao bean으로 등록
public class MessageDAOImpl implements MessageDAO {

	// 의존관계 주입 : 두개 이상의 변수가 필요한 경우, 각각 어노테이션을 붙여주어야 한다.
	@Inject
	SqlSession sqlSession;
//	@Inject
//	CartDAO cartDao;
	
	@Override
	public void create(MessageDTO dto) {
		sqlSession.insert("message.create", dto);

	}

	@Override
	public MessageDTO readMessage(int mid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateState(int mid) {
		// TODO Auto-generated method stub

	}

}
