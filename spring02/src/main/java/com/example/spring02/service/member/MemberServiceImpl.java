package com.example.spring02.service.member;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.spring02.model.member.dao.MemberDAO;
import com.example.spring02.model.member.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject
	MemberDAO memberDao;
	
	@Override
	public boolean loginCheck(MemberDTO dto, HttpSession session) {
		boolean result = memberDao.loginCheck(dto);
		if(result) { //로그인 성공
			// 세션변수에 값 저장 : id, name
			// 1. DAO의 loginCheck method를 수정하여 이름을 리턴받는다. (현재 리턴값 true/false)
			// 2. 기능 추가.
			MemberDTO dto2 = viewMember(dto.getUserid());
			// setAttribute(변수명, 값)
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", dto2.getName());
		}
		return result;
	}

	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}

	@Override
	public MemberDTO viewMember(String userid) {
		return memberDao.viewMember(userid);
	}

}
