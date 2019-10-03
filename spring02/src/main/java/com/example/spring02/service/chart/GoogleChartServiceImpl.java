package com.example.spring02.service.chart;

import java.util.List;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.example.spring02.model.shop.dto.CartDTO;
import com.example.spring02.service.shop.CartService;

@Service
public class GoogleChartServiceImpl implements GoogleChartService {
	
	// DI : 장바구니 서비스 주입
	@Inject
	CartService cartService;
	
	// ArrayList --> json객체로 변경
	// {"변수명" : [{},{},{}], "변수명":"값"}의 형태로 만들기 위해 객체를 생성/삽입
	@Override
	public JSONObject getChartData() {
		List<CartDTO> items = cartService.cartMoney();
		
		//리턴할 최종 json객체 : {}
		JSONObject data = new JSONObject(); // {}
		
		// 컬럼을 정의할 json 객체 : {}
		JSONObject col1 = new JSONObject(); // {}
		JSONObject col2 = new JSONObject(); // {}
		
		// json의 배열객체 : []
		JSONArray title = new JSONArray();
		
		col1.put("label", "상품명"); // {"":""}
		col1.put("type", "string"); // {"":"", "":""}
		col2.put("label", "금액"); // {"":""}
		col2.put("type", "number"); // {"":"", "":""} 
		
		// 타이틀행에 컬럼추가 : json배열에 json객체 추가
		title.add(col1);	// [{"":"", "":""} ]
		title.add(col2);	// [{"":"", "":""}, {"":"", "":""}]
		
		// json객체에 타이틀행(table의 header) 추가
		data.put("cols", title);
		// 			--> {"cols":[{"label":"상품명","type":"string"}, {"label":"금액","type":"number"}]}
		
		// rows : table의 각 행
		JSONArray body = new JSONArray();
		
		for(CartDTO dto : items) {
			//상품명
			JSONObject name = new JSONObject();
			name.put("v",dto.getProduct_name());
			//금액
			JSONObject money = new JSONObject();
			money.put("v",dto.getMoney());
			
			JSONArray row = new JSONArray();
			row.add(name);
			row.add(money);
			
			JSONObject cell = new JSONObject();
			cell.put("c", row);
			
			//레코드 1개 추가
			body.add(cell);
		}
		data.put("rows", body);
		
		return data;
	}

}
