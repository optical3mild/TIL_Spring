package com.example.spring01.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring01.model.dao.MemberDAO;
import com.example.spring01.model.dto.MemberDTO;

// 현재클래스를 스프링에서 관리하는 service bean으로 설정.
@Service
public class MemberServiceImpl implements MemberService {
	
	//dao 인스턴스를 주입시킴 
	// 어노테이션을 붙여 spring에서 관리하도록 만든 클래스는 new로 생성이 불가능하다
	// (= spring에서 lifecycle을 관리. new로 생성, close()등 임의로 관여불가. spring에서 생성하여 넣어주어야 사용가능.) 
	// MemberDAO memberDao = new MemberDAOImpl();로 작성되는 것을 아래코드로 축약
	// 자료형을 인터페이스와 동일하게 작성하여도 컴파일러에서는 구현 클래스를 찾아 인스턴스를 생성하여 주입시켜 준다.
	// 	: MemberDAO, MemberDAOImpl 둘다 사용가능하다. 
	@Inject
	MemberDAO memberDao;

	@Override
	public List<MemberDTO> memberList() {
		// memberDao 객체에서 메소드 호출, 리턴.
		return memberDao.memberList();
	}

	@Override
	public void insertMember(MemberDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public MemberDTO viewMember(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMember(String userid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMember(MemberDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkPw(String userid, String passwd) {
		// TODO Auto-generated method stub
		return false;
	}

}
