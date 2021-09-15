package com.ssafy.special.main;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ssafy.special.domain.ProductQuery;
import com.ssafy.special.service.DaangnCrawlingService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DaangnMultiThreadCrawling implements Runnable{

	private final DaangnCrawlingService daangnCrawlingService;
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
		daangnCrawlingService.crawlingProduct(productQuery, queryExceptionKeywordList);		
	}	
}
