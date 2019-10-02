package com.example.spring02.controller.pdf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("pdf/*")
public class PdfController {

	@RequestMapping("list.do")
	public ModelAndView list() throws Exception {
		
		return null;
	}
}
