package com.example.spring01;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MysqlConnectionTest {
	// 로깅을 위한 변수선언
	private static final Logger logger =
			LoggerFactory.getLogger(MysqlConnectionTest.class);
	private static final String Driver =
			"com.mysql.cj.jdbc.Driver";
	
	// DB연결문자열 jdbc:oracle:thin:@호스트:포트:sid(DB id)
	private static final String URL =
			"jdbc:mysql://localhost:3306/spring?characterEncoding=UTF-8&serverTimezone=UTC";
	private static final String USER = "spring";
	private static final String PW = "1234";
	
	@Test //JUnit이 테스트하는 method
	public void test() throws Exception {
		Class.forName(Driver); //드라이버 로딩
		try(Connection conn 
				= DriverManager.getConnection(URL, USER, PW)) {
			logger.info("MySQL에 연결되었습니다.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
