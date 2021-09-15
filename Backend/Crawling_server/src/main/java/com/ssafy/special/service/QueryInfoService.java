package com.ssafy.special.service;

import java.util.List;

import com.ssafy.special.domain.ProductQuery;

public interface QueryInfoService {
	//검색어 리스트 리턴
	public List<ProductQuery> getProductQueryList();
	//해당 검색어의 제외키워드 리턴
	public List<String> getQueryExceptionKeywordList(ProductQuery productQuery);
	//ProductSellList테이블 비우기
	public void truncateProductSellList();
}
