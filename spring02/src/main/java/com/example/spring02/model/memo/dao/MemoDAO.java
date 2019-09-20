package com.example.spring02.model.memo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.spring02.model.memo.dto.MemoDTO;

// mybatis interface mapper
public interface MemoDAO {
	
	// MemoDAO인터페이스에 list()메소드가 완성되지 않았으나 
	// @Select 어노테이션과 sql문의 조합으로 sql이 실행되고 리턴값을 자동으로 만들어 리턴
	// DAO, DAOImpl, mapper.xml이 DAO에 합쳐진 기능. 코드도 축약됨.
	//	--> root-context.xml의 <mybatis-spring:scan >의 base-package로 등록된 패키지 이하에 존재하는 클래스들만 가능.
	@Select("select * from memo order by idx desc")
	public List<MemoDTO> list();
	
	// mybatis query에 전달할 변수는 @Param으로 처리
	// nvl() : oracle 함수.
	// @Insert("insert into memo (idx,writer,memo) values"
	//			+ "((select nvl(max(idx)+1,1) from memo)" + ", #{writer}, #{memo})")
	// coalesce() : 모든 데이터베이스에서 사용 / ifnull() : mysql에서 사용.
	// mysql의 서브쿼리의 테이블과 메인쿼리의 작업테이블이 동일할 때, 오류발생. --> 서브쿼리의 테이블에 별칭을 붙여주면 작동.
	@Insert("insert into memo (idx,writer,memo) values "
			+ "((select ifnull(max(idx)+1,1) from memo a)" + ", #{writer}, #{memo})")
	// 위에 작성된 sql문의 변수명과 @Param("변수명")으로 표시된 변수명이 동일하여야 값이 전달됨.
	public void insert(@Param("writer") String writer, @Param("memo") String memo);
}
