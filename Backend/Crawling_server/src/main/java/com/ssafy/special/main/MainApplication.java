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
import com.ssafy.special.service.KeywordInfoService;
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
	private final SimilarityHadoopThread similarityHadoopThread;
//	private final SSHUtils ssh;
	private final ProductSellListRepository productSellListRepository;
	private final SimilarityService similarityService;
//	private final String sendFilePath = "/home/ubuntu/mysqltablefile/sellList.txt";
//	private final String receiveFilePath = "/home/j5d205/receive/";
	static Thread hadoopExecThred=null;
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
				
//				similarityService.similarityProduct();
				
				break;
			} else
				log.info("현재 " + (threadcnt - cnt) + "개의 크롤링이 진행중입니다");
		}

	}
	public void similarityThread() {
		
		if(hadoopExecThred!=null && hadoopExecThred.getState()==Thread.State.TERMINATED) {
			hadoopExecThred=null;
		}
		//리스트에 목록이 있고 스레드가 널일경우
		if(hadoopExecThred==null) {
//			product list
//			similarityHadoopThread.setProduct(product);
			hadoopExecThred = new Thread(similarityHadoopThread);
			hadoopExecThred.run();
		}
		
	}


}
