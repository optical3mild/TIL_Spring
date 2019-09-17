package com.example.spring01.controller;

import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

// junit4 버전으로 테스트
@RunWith(SpringJUnit4ClassRunner.class)
// webapplication의 설정정보를 가져옴
@WebAppConfiguration
// 환경설정파일 로드
// : 해당 클래스 -컨트롤러- 를 작동시키기 위해 spring의 설정파일이 필요. 설정파일을 가져옴.
// servlet-context, root-context 두 파일을 따로 작성하여도 되나 전체경로를 잡을 수 있으므로, 통합하여 작성함.
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})

public class MainControllerTest {

	// 의존관계 주입(DI), 제어권의 역전(IoC) - 외부(현재 클래스 외부인 spring)에서 인스턴스를 만들어 전달.
	@Inject
	WebApplicationContext wac;
	MockMvc mockMvc; // view없이 mvc 테스트를 위한 객체
	// import시 org.junit.Before와 aspectJ의 두종류가 있음 (aspectJ - spring의 AOP와 관련됨).
	// @Before는 test전에 실행되는 코드이다.
	@Before //JUnit (org.junit.Before)
	public void setup() throws Exception {
		// mvc테스트를 위한 mockMvc 인스턴스 생성
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	// JUnit이 테스트하는 코드. 예외처리 추가 필요
	@Test
	//public void testMain() { // 자동 생성된 경우 예외처리가 되어 있지 않음.
	public void testMain() throws Exception {
		// doA url을 get으로 요청 - 이상이 없으면 통과, 이상이 있으면 에러표시.
		mockMvc.perform(MockMvcRequestBuilders.get("/doA"));
		
		// 아무것도 작성하지 않을 시 default로 생성된 아래 fail코드가 실행, fail로 결과가 나타난다.
		//fail("Not yet implemented");
	}

	@Test
	public void testGugu() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/gugu.do"));
	}

	@Test
	public void testTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/test.do"));
	}

	@Test
	public void testDoA() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/test/doA"));
	}

	@Test
	public void testDoB() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/test/doB"));
	}

	@Test
	public void testDoC() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/test/doC"));
	}

	@Test
	public void testDoD() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/test/doD"));
	}

	@Test
	public void testDoE() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/test/doE"));
	}

}
