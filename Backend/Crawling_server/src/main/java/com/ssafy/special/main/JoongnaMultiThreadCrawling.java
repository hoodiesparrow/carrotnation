package com.ssafy.special.main;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ssafy.special.domain.ProductQuery;
import com.ssafy.special.service.JoongnaCrawlingService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JoongnaMultiThreadCrawling implements Runnable{
	
	private final JoongnaCrawlingService joongnaCrawlingService;
	private ProductQuery productQuery;
	private List<String> queryexceptionlist;
	
	public void setProductQuery(ProductQuery productQuery) {
		this.productQuery = productQuery;
	}
	
	public void setQueryexceptionlist(List<String> queryexceptionlist) {
		this.queryexceptionlist = queryexceptionlist;
	}

	@Override
	public void run() {
		joongnaCrawlingService.joongnainit(productQuery,queryexceptionlist);
	}

}
