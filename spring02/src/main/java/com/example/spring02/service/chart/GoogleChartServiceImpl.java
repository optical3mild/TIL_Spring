package com.example.spring02.service.chart;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.example.spring02.service.shop.CartService;

@Service
public class GoogleChartServiceImpl implements GoogleChartService {
	
	// DI : 장바구니 서비스 주입
	@Inject
	CartService cartService;
	
	@Override
	public JSONObject getChartData() {
		// TODO Auto-generated method stub
		return null;
	}

}
