package com.example.spring02.controller.upload;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//컨트롤러 bean 설정
@Controller
public class AjaxUploadController {
	//로깅을 위한 변수
	private static final Logger logger = LoggerFactory.getLogger(AjaxUploadController.class);
	
	//업로드 디렉토리 : servlet-context.xml에 설정되어 있음
	//설정파일에서 값을 읽어와 변수에 대입하므로, 결과적으로 String uploadPath="e:/upload";와 동일.
	@Resource(name="uploadPath")
	String uploadPath;
	
	//파일첨부 페이지로 이동
	@RequestMapping(value="/upload/uploadAjax", method=RequestMethod.GET)
	public String uploadAjax() {
		return "/upload/uploadAjax";
	}
}
