package com.example.spring01.service;

import java.util.List;

import com.example.spring01.model.dto.MemberDTO;

public interface MemberService {
	// service는 dao의 집합으로, 하나의 service는 여러 dao와 연결되어 호출할 수 있음.
	// 이번코드는 이러한 기능이 크게 필요없으므로 MemberDAO와 동일하게 작성.
	
	public List<MemberDTO> memberList();
	
	public void insertMember(MemberDTO dto);
	
	public MemberDTO viewMember(String userid);
	
	public void deleteMember(String userid);
	
	public void updateMember(MemberDTO dto);
	
	//비밀번호 체크
	public boolean checkPw(String userid, String passwd);
}
