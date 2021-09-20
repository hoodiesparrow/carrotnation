package com.ssafy.special.service;

public interface ProductSellListInfoService {
	//ProductSellList테이블 비우기
	void truncateProductSellList();
	
	//ProductSellListStack테이블에 ProductSellList테이블 데이터 반영
	void updateProductSellListStack();
	
}
