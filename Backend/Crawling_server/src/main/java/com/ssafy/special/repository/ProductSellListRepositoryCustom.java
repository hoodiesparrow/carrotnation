package com.ssafy.special.repository;

import java.util.List;
import java.util.Optional;

import com.ssafy.special.domain.ProductSellList;

public interface ProductSellListRepositoryCustom {
	//현재 사이클 이상인 데이터만 가져옴
	Optional<List<ProductSellList>> getRecentProductSellList(Long cycle);
}
