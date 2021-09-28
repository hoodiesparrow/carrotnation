package com.ssafy.special.service;

import java.util.List;

import com.ssafy.special.domain.Product;

public interface ProductService {
	//크롤링한 게시글에서 해당 제품의 최저가, 최고가, 평균가 리턴
	void setProductsMinMaxAvgPrice();
	
	Product getProduct(long pid);
	
	//쿼리에 해당한느 제품들 pid받아옴
	List<Long> getProductidByQuery(String query);
}
