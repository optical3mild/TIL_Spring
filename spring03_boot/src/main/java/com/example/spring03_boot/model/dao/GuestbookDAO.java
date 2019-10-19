package com.example.spring03_boot.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.spring03_boot.model.dto.GuestbookDTO;

// MyBatis interface mapper (SQL 명령어가 들어간 코드)
public interface GuestbookDAO {
	
	@Select("select * from guestbook order by idx desc")
	public List<GuestbookDTO> list();
}
