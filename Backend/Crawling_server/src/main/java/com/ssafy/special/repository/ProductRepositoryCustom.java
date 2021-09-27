package com.ssafy.special.repository;

import com.querydsl.core.Tuple;
import com.ssafy.special.domain.Product;

public interface ProductRepositoryCustom{
	//productSellList에서 Product의 최소, 최대, 평균값 가져옴
	public Tuple getProductsMinMaxAvgPrice(Product product);
	
}
