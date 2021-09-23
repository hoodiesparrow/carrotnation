package com.ssafy.special.service;

import java.util.List;

import com.ssafy.special.domain.ProductSellList;

public interface ProductSellListInfoService {
	//현재 사이클 이상인 데이터만 가져옴
	List<ProductSellList> getProductSellLists();
}
