package com.ssafy.special.main;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.JSchException;
import com.ssafy.special.controller.SSHUtils;
import com.ssafy.special.domain.Product;
import com.ssafy.special.domain.ProductQuery;
import com.ssafy.special.domain.ProductSellList;
import com.ssafy.special.repository.ProductSellListRepository;
import com.ssafy.special.service.DatePriceService;
import com.ssafy.special.service.KeywordInfoService;
import com.ssafy.special.service.ProductService;
import com.ssafy.special.service.SimilarityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class MainApplication {
	private final KeywordInfoService queryInfoService;

	private final DaangnMultiThreadCrawling daangnMultiThreadCrawling;
	private final JoongnaMultiThreadCrawling JoongnaMultiThreadCrawling;
	private final ThunderMultiThreadCrawling thunderMultiThreadCrawling;
//	private final SSHUtils ssh;
	private final ProductSellListRepository productSellListRepository;
	private final SimilarityService similarityService;
//	private final String sendFilePath = "/home/ubuntu/mysqltablefile/sellList.txt";
//	private final String receiveFilePath = "/home/j5d205/receive/";

	private final ProductService productService;
	private final DatePriceService datePriceService;
	
	
	
	@Scheduled(fixedRate = 1000 * 60 * 60) // 1시간
	public void crawlingStart() {
		
		List<ProductQuery> productQuery = queryInfoService.getProductQueryList();

		List<Thread> threadList = new ArrayList<Thread>();

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

			threadList.add(daangn);
			threadList.add(joongna);
			threadList.add(thunder);
		}

		int threadcnt = threadList.size();
		while (true) {
			int cnt = 0;
			try {
				Thread.sleep(1000 * 60);
			} catch (Exception e) {
				// TODO: handle exception
			}
			for (int i = 0; i < threadList.size(); i++) {
				Thread thread = threadList.get(i);
				if (thread == null) {
					cnt++;
					continue;
				}
				if (thread.getState() == Thread.State.TERMINATED) {
					threadList.set(i, null);
					cnt++;
				}
			}
			if (cnt == threadcnt) {
				log.info("크롤링이 모두 끝났습니다");				
				break;
			} else
				log.info("현재 " + (threadcnt - cnt) + "개의 크롤링이 진행중입니다");
		}
		//크롤링 끝난뒤 크롤링한 데이터를 Komoran으로 형태소 분석후 NNP, NNG만 클러스터 서버에 FTP로 파일전송
		//전송후 해당 파일을 HDFS에 집어넣고 하둡을 돌려서 유사도 뽑아낸뒤 그걸 DB에 저장
		similarityService.similarityProduct();

		//현재 판매 물품 중 최저가부터 고가, 평균가 구함
		productService.setProductsMinMaxAvgPrice();
	
		//과거 품목 가격 추이
		//매일 매일 평균가격을 쌓아 나가면서 누적되게함
		datePriceService.setDatePrice();
		
		//가격별 판매 건수 조회
		
	
		
	}
}
