package com.ssafy.special.main;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ssafy.special.domain.ProductQuery;
import com.ssafy.special.service.ThunderCrawlingService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ThunderMultiThreadCrawling implements Runnable{

	private final ThunderCrawlingService thunderboltCrawlingService;
	private ProductQuery productQuery;
	private List<String> queryExceptionKeywordList;
	
	public void setProductQuery(ProductQuery productQuery) {
		this.productQuery = productQuery;
	}

	public void setQueryExceptionKeywordList(List<String> queryExceptionKeywordList) {
		this.queryExceptionKeywordList = queryExceptionKeywordList;
	}

	@Override
	public void run() {
		thunderboltCrawlingService.crawlingProduct(productQuery, queryExceptionKeywordList);		
	}	
}
