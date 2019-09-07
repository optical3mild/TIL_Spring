package com.example.spring01.test;
// 의존관계 : MemberUse는 Member에 의존한다.

import javax.inject.Inject;

// 강한 결합관계
class Member {
	String userid;
	String passwd;
	String name;
	//public Member() {}
	
	//singleton 패턴
	private Member() {} //외부에서 new로 인스턴스를 만들 수 없음.
	private static Member instance;
	public static Member getInstance() {
		if(instance == null) {
			instance = new Member();
		}
		return instance;
	}
}

// 느슨한 결합관계
class Member2 {
	String userid;
	String passwd;
	String name;
	//public Member() {}
	
	//singleton 패턴
	private Member2() {} //외부에서 new로 인스턴스를 만들 수 없음.
}

public class MemberUse {
	//강한 결합관계
	public MemberUse() {
		//Member m = new Member();
		Member m = Member.getInstance();
	}
	
	//느슨한 결합관계 -- 인스턴스를 외부에서 만들어서 전달
	// --> "의존관계 주입(Dependency Injection, DI)" 필요.
	@Inject // @Inject가 있는 경우, spring이 member2라는 인스턴스를 생성하여 연결.
	Member2 m;
	public MemberUse(Member2 m) {
		
	}
}


