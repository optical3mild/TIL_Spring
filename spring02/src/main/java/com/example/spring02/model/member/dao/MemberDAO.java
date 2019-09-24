package com.example.spring02.model.member.dao;

import com.example.spring02.model.member.dto.MemberDTO;

public interface MemberDAO {
	
	// dto를 받아서 쿼리를 실행, 결과값이 없으면 false/ 결과값이 있으면 true를 반환받는 방식.
	public boolean loginCheck(MemberDTO dto);
	
	public MemberDTO viewMember(String userid);
}
