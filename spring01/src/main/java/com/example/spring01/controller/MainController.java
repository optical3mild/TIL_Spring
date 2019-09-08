package com.example.spring01.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring01.model.dto.ProductDTO;

// 현재클래스를 스프링에서 관리하는 컨트롤러 빈으로 등록함.
@Controller
public class MainController {
	// 로깅을 위한 변수
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	// url mapping
	@RequestMapping("/") // post, get 모두 가능
	public String main(Model model) {
		// 메소드 이름
		// 	> jsp페이지(현재 메소드의 return값) 이름과 연관없음. 
		//	> 리턴값이 void인 경우 호출한 url pattern과 동일한 페이지로 이동됨.
		
		//Model에 자료 지정.(서블릿의 request 객체에 해당됨) map구조로 되어 있음.
		//매개변수 위치에 session, request, response 모두 올수 있고 여러개도 가능. 유연함.
		model.addAttribute("message", "홈페이지 방문을 환영합니다.");
		//main.jsp로 포워딩 됨 
		// --> servlet-context.xml의 viewResolver에 설정된 prefix와 suffix를 참조하여 경로완성, 포워딩
		return "main";
	}
	
	
//	값을 전달받는 방법 1. - 어렵고 코드가 길어짐.
//	@RequestMapping("gugu.do") //url mapping
//	public String gugu(Model model, HttpServletRequest request) { //매개변수 순서 상관없음. 빼도 상관없음.
//		//method가 지정되어 있지 않으므로 주소창에 매개변수를 입력하여도 값이 전달됨
//		// example : localhost/spring01/gugu.do?dan=5
//		int dan = Integer.parseInt(request.getParameter("dan"));
	
//  값을 전달받는 방법 2. - 코드가 간결해짐. : request.getParameter 생략가능
//	@RequestMapping("gugu.do") //url mapping
//	public String gugu(Model model, int dan) { // int dan으로 넣고 따로선언하지 않아도 변수 선언되고 값을 전달받음
//		//url요청에서 '~?변수명=값' 의 변수명과 해당 컨트롤러의 메소드에 작성된 매개변수명이 일치하게 작성.
	
//  값을 전달받는 방법 3. - 주소요청에 값이 설정되지 않는 경우를 대비 @RequestParam 삽입	
	@RequestMapping("gugu.do") //url mapping
	public String gugu(Model model, @RequestParam(defaultValue="2") int dan) { 
			// @RequestParam(defalutValue="기본값")
			// > 일반 매개변수가 아니라 get또는 post방식으로 넘어오는 파라미터 변수로 매개변수를 정의
			// > request.getParameter("변수명")을 대체하는 코드
			// > 생략이 가능하나, 기본값을 설정하기 위해서는 작성하여야 함.
			// > defalutValue="기본값" : 넘어오는 값이 없을 때 매개변수에 저장되는 default값.
		//int dan = 7;
		String result = "";
		for(int i = 1; i<=9; i++) {
			result += dan + "x" + i + "=" + dan*i + "<br>";
		}
		//모델에 자료 저장.
		model.addAttribute("result", result);
		// views/test/gugu.jsp로 포워딩
		return "test/gugu";
	}
	
	@RequestMapping("test.do")
	public void test() { 
		// return type이 void일 경우, 자동으로 호출한 url pattern과 동일한 페이지로 이동
		
		// **리턴타입이 String일 때는 
		// > servlet-context.xml의 viewResolver에 등록된 prefix와 suffix를 이용하여 경로를 완성, test.jsp로 포워딩
		// > 메소드명과 page명이 다른 경우에 리턴타입과 리턴명령을 활용.
		// return "test";
	}
	
	@RequestMapping("test/doA")
	public String doA(Model model) {
		// 1. 예전 dynamicWeb servlet방식
		//		request.setAttribute("message", "홈페이지 방문을......");
		// 		String page = "/test/doA.jsp";
		// 		RequestDispatcher rd = request.getRequestDispatcher(page);
		// 		rd.forward(request, response)
		
		// 2. Spring에서 사용하는 코드 - 축약됨. 기본원리는 동일.
		// 자료저장
		model.addAttribute("message", "doA 방문을 환영합니다.");
		// 포워딩
		return "test/doA";
	}
	
	@RequestMapping("test/doB")
	public void doB() {
		// 리턴타입이 void인 경우
		// @RequestMapping의 경로에 viewResolver의 prefix와 suffix를 이용하여 페이지 경로를 완성, 포워딩
		// = 호출한 url pattern(이 경우에는 test/doB)과 동일한 페이지로 포워딩.
		logger.info("doB 호출..");
	}

// Model 클래스 - 전달할 데이터를 저장하는 저장소 역할
// ModelAndView 클래스 - 데이터와 포워딩할 페이지 정보 포함하는 저장소 역할
	@RequestMapping("test/doC")
	public ModelAndView doC() {
		Map<String, Object> map = new HashMap<>();
		map.put("product", new ProductDTO("샤프",1000));
// new ModelAndView("페이지이름[=포워딩할 주소]","변수이름",값) - 주소와 데이터를 한꺼번에 전송가능
		return new ModelAndView("test/doC","map",map);
	}
	
	@RequestMapping("test/doD")
	public String doD() {
		// 리다이렉트(!= 포워딩) : test/doE로 매핑된 서블릿 요청으로 넘김
		return "redirect:/test/doE";
	}
	@RequestMapping("test/doE")
	public void doE() {
		// test/doE.jsp로 자동으로 포워딩.
	}
	
	// 포워딩이 되지 않도록 작성...
	// --> 뷰를 리턴하는 것이 아닌 데이터 자체를 리턴할 경우
	@ResponseBody //없을경우 포워딩 시도로 브라우저에서 404에러 발생
	@RequestMapping("test/doF")
	public ProductDTO doF() {
		return new ProductDTO("냉장고", 500000);
	}
	
}







