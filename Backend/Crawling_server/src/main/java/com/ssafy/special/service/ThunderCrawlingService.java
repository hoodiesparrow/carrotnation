package com.ssafy.special.service;

import java.util.List;

import com.ssafy.special.domain.ProductQuery;

public interface ThunderCrawlingService {
	public void crawlingProduct(ProductQuery productQuery, List<String> queryExceptionKeywordList);
}
