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
		List<CartDTO> list = cartDao.cartMoney();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for(CartDTO dto : list) {
			dataset.setValue(dto.getMoney(), "과일", dto.getProduct_name());
		}
		JFreeChart chart = null;
		String title = "장바구니 통계";
		try {
			chart = ChartFactory.createBarChart(title, "상품명", "금액",
												dataset, PlotOrientation.VERTICAL, true, true, false);
			// java.awt.Font;
			chart.getTitle().setFont(new Font("돋움", Font.BOLD, 15));
			chart.getLegend().setItemFont(new Font("돋움", Font.PLAIN, 12));
			
			Color color = new Color(0,0,0);
			
			StandardChartTheme chartTheme = (StandardChartTheme)StandardChartTheme.createJFreeTheme();
			chartTheme.setExtraLargeFont(font);
			chartTheme.setLargeFont(font);
			chartTheme.setRegularFont(font);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
