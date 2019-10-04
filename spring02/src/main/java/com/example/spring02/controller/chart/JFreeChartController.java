package com.example.spring02.controller.chart;

import java.io.FileOutputStream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.service.chart.JFreeChartService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

// controller bean으로 등록
@Controller
// 공통적인 url mapping
@RequestMapping("jchart/*")
public class JFreeChartController {
	
	@Inject
	JFreeChartService chartService;
	
	// 세부적인 url mapping
	@RequestMapping("chart1.do")
	// HttpServletResponse : JFreeChart를 이용하기 위한 매개변수 형. servlet에서 사용하는 일반적 매개변수 형
	public void createChart1(HttpServletResponse response) {
		try {
			//차트 객체 리턴
			JFreeChart chart = chartService.createChart();
			// 차트를 png로 export
			// writeChartAsPNG(출력스트림, 차트객체, 가로, 세로)
			ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 900, 550);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("chart2.do")
	public ModelAndView createChart2(HttpServletResponse response) {
		String message = "";
		
		try {
			// 차트 객체를 리턴
			JFreeChart chart = chartService.createChart();
			// pdf 문서 객체
			Document document = new Document(); // itextpdf
			// pdf 생성 객체
			PdfWriter.getInstance(document, new FileOutputStream("e:/test.pdf"));
			// 문서에 데이터를 넣을 수 있도록 open
			document.open();
			// 차트를 itextpdf 라이브러리에서 지원하는 이미지 형식으로 변환
			// : BufferedImage를 png로 변환한 값을 매개값으로 하여 itextpdf의 이미지 객체를 생성
			Image png = Image.getInstance(ChartUtilities.encodeAsPNG(chart.createBufferedImage(500, 500)));
			// pdf 문서에 이미지를 추가
			document.add(png);
			// pdf 문서 저장
			document.close();
			
			message = "pdf파일이 생성되었습니다.";
		} catch (Exception e) {
			message = "pdf파일 생성 실패.";
			e.printStackTrace();
		}
		return new ModelAndView("chart/jchart02", "message", message); 
	}
	
}
