package com.example.spring01.model.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring01.model.dto.MemberDTO;

// 현재클래스를 dao bean으로 등록시킴. (현재클래스를 스프링에서 관리하는 dao bean으로 설정.)
// --> @Repository 가 붙은 클래스는 직접 생성시킬 수 없게되며 spring에 lifecycle을 맡기게 됨.
// --> 서버가 시작될 때 자동으로 싱글톤으로 생성되어 메모리에 로드됨.
@Repository
public class MemberDAOImpl implements MemberDAO {
	
//>> MyBatis manager를 사용하여 offsession으로(?) 인스턴스를 생성하는 방법 대신에 @Inject를 사용하여 주입.
	
	// MyBatis의 SqlSession객체를 스프링에서 생성하여 주입시킴.
	// 의존관계 주입(Dependancy Injection, DI)
	// 느슨한 결합
	// IoC(Inversion of Control, 제어의 역전)
	@Inject
	SqlSession sqlSession;

	@Override
	public List<MemberDTO> memberList() {
		// memberMapper.xml에서 member네임스페이스의 id가 memberList로 등록되어 있는 태그의 내용을 호출
		return sqlSession.selectList("member.memberList");
	}

	@Override
	public void insertMember(MemberDTO vo) {
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
	public void updateMember(MemberDTO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkPw(String userid, String passwd) {
		// TODO Auto-generated method stub
		return false;
	}

}
