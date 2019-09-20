package com.exmaple.spring02.model.memo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.exmaple.spring02.model.memo.dto.MemoDTO;

// mybatis interface mapper
public interface MemoDAO {
	
	// MemoDAO인터페이스에 list()메소드가 완성되지 않았으나 
	// @Select 어노테이션과 sql문의 조합으로 sql이 실행되고 리턴값을 자동으로 만들어 리턴
	// DAO, DAOImpl, mapper.xml이 DAO에 합쳐진 기능. 코드도 축약됨.
	//	--> root-context.xml의 <mybatis-spring:scan >의 base-package로 등록된 패키지 이하에 존재하는 클래스들만 가능.
	@Select("select * from memo order by idx desc")
	public List<MemoDTO> list();
}
