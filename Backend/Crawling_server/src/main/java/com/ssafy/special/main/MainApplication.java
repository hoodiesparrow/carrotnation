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
	private final ThunderMultiThreadCrawling thunderMultiThreadCrawling;
	
	@Scheduled(fixedDelay = 1000 * 60 * 60)//1시간
	public void crawlingStart() {
		List<ProductQuery> productQuery = queryInfoService.getProductQueryList();
		queryInfoService.truncateProductSellList();
		
		for (ProductQuery query : productQuery) {
			List<String> queryExceptionKeywordList = queryInfoService.getQueryExceptionKeywordList(query);
			daangnMultiThreadCrawling.setProductQuery(query);
			daangnMultiThreadCrawling.setQueryExceptionKeywordList(queryExceptionKeywordList);
			
			JoongnaMultiThreadCrawling.setProductQuery(query);
			JoongnaMultiThreadCrawling.setQueryexceptionlist(queryExceptionKeywordList);
			
			thunderMultiThreadCrawling.setProductQuery(query);
			thunderMultiThreadCrawling.setQueryExceptionKeywordList(queryExceptionKeywordList);
			
			Thread daangn = new Thread(daangnMultiThreadCrawling);
			Thread joongna = new Thread(JoongnaMultiThreadCrawling);
			Thread thunder = new Thread(thunderMultiThreadCrawling);
			
			daangn.start();
			joongna.start();
			thunder.start();
		}

	}

}
