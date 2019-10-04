package com.example.spring02.service.chart;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.inject.Inject;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Service;

import com.example.spring02.model.shop.dao.CartDAO;
import com.example.spring02.model.shop.dto.CartDTO;

//import com.itextpdf.text.Font; <-- 폰트 주의.

@Service
public class JFreeChartServiceImpl implements JFreeChartService {

	//의존관계 주입
	@Inject
	CartDAO cartDao;
	
	@Override
	public JFreeChart createChart() {
		// 장바구니 상품목록 가져옴
		List<CartDTO> list = cartDao.cartMoney();
		// 1. 차트에 입력할 데이터 셋 객체 (파이차트가 아닌경우)
		//DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// 데이터 셋에 값을 넣음
		//for(CartDTO dto : list) {
		//	dataset.setValue(dto.getMoney(), "과일", dto.getProduct_name()); // (값, 제목, 이름)
		//}

		// 2.파이차트용 데이터 셋 생성
		DefaultPieDataset dataset = new DefaultPieDataset();
		for(CartDTO dto : list) {
			dataset.setValue(dto.getProduct_name(), dto.getMoney());
		}
		
		// 차트 객체 : 차트를 실제로 그려주는 객체
		JFreeChart chart = null;
		// 차트 제목
		String title = "장바구니 통계";
		try {
			// 차트 생성
			
			// 1. 막대그래프 : createBarChart(제목, x축 라벨, y축 라벨, 데이터셋, 그래프 방향, 범례, 툴팁, url)
			//chart = ChartFactory.createBarChart(title, "상품명", "금액",
			//									dataset, PlotOrientation.VERTICAL, true, true, false);
			
			// 2. 선 그래프
			//chart = ChartFactory.createLineChart(title, "상품명", "금액",
			//									dataset, PlotOrientation.VERTICAL, true, true, false);
			
			// 3. 파이차트 : createPieChart(제목, 데이터 셋, 범례, 툴팁, url)
			chart = ChartFactory.createPieChart(title, dataset, true, true, false);
			
		// >> 이하 chart의 style
			// java.awt.Font; : 한글설정
			chart.getTitle().setFont(new Font("돋움", Font.BOLD, 15)); // 차트 제목 폰트
			chart.getLegend().setItemFont(new Font("돋움", Font.PLAIN, 10)); // 범례 폰트
			
			// 그 외의 폰트지정과 색상지정을 위한 객체. 아래에 각 경우마다 세부지정.
			Font font = new Font("돋움", Font.PLAIN, 12);
			Color color = new Color(0,0,0); // black
			
			StandardChartTheme chartTheme = (StandardChartTheme)StandardChartTheme.createJFreeTheme();
			chartTheme.setExtraLargeFont(font);
			chartTheme.setLargeFont(font);
			chartTheme.setRegularFont(font);
			chartTheme.setSmallFont(font);
			chartTheme.setAxisLabelPaint(color);
			chartTheme.setLegendItemPaint(color);
			chartTheme.setItemLabelPaint(color);
			// 폰트, 컬러를 차트에 적용
			chartTheme.apply(chart);
		// <<
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chart;
	}

}
