package com.example.spring02.service.shop;

import java.util.List;

import com.example.spring02.model.shop.dto.ProductDTO;

public interface ProductService {
	// R-All 상품목록 출력
		List<ProductDTO> listProduct();
		// R-One 상품상세정보 출력
		ProductDTO detailProduct(int product_id);
		// U
		void updateProduct(ProductDTO dto);
		// D
		void deleteProduct(int product_id);
		// C
		void insertProduct(ProductDTO dto);
		// R-FileInfo
		String fileInfo(int product_id);
}
