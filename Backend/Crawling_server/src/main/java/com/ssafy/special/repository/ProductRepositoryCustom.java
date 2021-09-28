package com.ssafy.special.repository;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.Tuple;
import com.ssafy.special.domain.Product;

public interface ProductRepositoryCustom{
	//productSellList에서 Product의 최소, 최대, 평균값 가져옴
	Tuple getProductsMinMaxAvgPrice(Product product);
	
	Optional<List<Long>> getProductIdByQuery(String query);
	
}
