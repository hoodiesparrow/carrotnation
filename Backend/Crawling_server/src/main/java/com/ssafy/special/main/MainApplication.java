package com.ssafy.special.main;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ssafy.special.domain.ProductQuery;
import com.ssafy.special.service.QueryInfoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MainApplication {
	private final QueryInfoService queryInfoService;
	private final DaangnMultiThreadCrawling daangnMultiThreadCrawling;	
	private final JoongnaMultiThreadCrawling JoongnaMultiThreadCrawling;
	
	@Scheduled(fixedDelay = 1000 * 60 * 30)//30분
	public void crawlingStart() {
		List<ProductQuery> productQuery = queryInfoService.getProductQueryList();

		for (ProductQuery query : productQuery) {
			List<String> queryExceptionKeywordList = queryInfoService.getQueryExceptionKeywordList(query);
			daangnMultiThreadCrawling.setProductQuery(query);
			daangnMultiThreadCrawling.setQueryExceptionKeywordList(queryExceptionKeywordList);
			
			JoongnaMultiThreadCrawling.setProductQuery(query);
			JoongnaMultiThreadCrawling.setQueryexceptionlist(queryExceptionKeywordList);
			
			
			Thread daangn = new Thread(daangnMultiThreadCrawling);
			Thread joongna = new Thread(JoongnaMultiThreadCrawling);
			daangn.start();
			joongna.start();
		}

	}

}
