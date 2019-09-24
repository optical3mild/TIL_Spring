package com.example.spring02.service.shop;

import java.util.List;

import com.example.spring02.model.shop.dto.CartDTO;

public interface CartService {
	public List<CartDTO> cartMoney();
	
	//C
	public void insert(CartDTO dto);
	//R
	public List<CartDTO> listCart(String userid);
	//D
	public void delete(int cart_id);
	public void deleteAll(String userid);
	//U
	public void update(int cart_id);
	public int sumMoney(String userid);
	
	//장바구니에 이미 상품이 담겨있는지 확인
	public int countCart(String userid, int product_id);
	//장바구니 수량 변경
	public void updateCart(CartDTO dto);
	public void modifyCart(CartDTO dto);
}
