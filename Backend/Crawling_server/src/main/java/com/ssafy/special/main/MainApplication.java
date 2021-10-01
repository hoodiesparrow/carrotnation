package com.ssafy.special.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ssafy.special.domain.ProductQuery;
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
	private final SimilarityThread similarityThread;

//	private final SSHUtils ssh;
	private final ProductSellListRepository productSellListRepository;
	private final SimilarityService similarityService;
//	private final String sendFilePath = "/home/ubuntu/mysqltablefile/sellList.txt";
//	private final String receiveFilePath = "/home/j5d205/receive/";


	private final ProductService productService;
	private final DatePriceService datePriceService;
	
	private static class Pair{
		private String query;
		private Thread thread;
		public Pair(String query, Thread thread) {
			this.query = query;
			this.thread = thread;
		}
		public String getQuery() {
			return query;
		}
		public void setQuery(String query) {
			this.query = query;
		}
		public Thread getThread() {
			return thread;
		}
		public void setThread(Thread thread) {
			this.thread = thread;
		}		
	}
	

//	@Scheduled(fixedRate = 1000 * 60 * 60) // 1시간
	@Async
	private void crawlingStart() {
		log.info("Schedule Start ----------------------------------------------------------------------------------");
		
		
		List<ProductQuery> productQuery = queryInfoService.getProductQueryList();


		List<Pair> threadList = new ArrayList<>();

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

			threadList.add(new Pair(query.getQuery(), daangn));
			threadList.add(new Pair(query.getQuery(), joongna));
			threadList.add(new Pair(query.getQuery(), thunder));
		}
		
		Thread similarity = null;
		Map<String, Integer> queryCount= new HashMap<String, Integer>();
		List<Long> pidList = new ArrayList<Long>();
		int threadcnt = threadList.size();
		while (true) {
			int cnt = 0;
			
			//1분마다 크롤링 끝났는지 체크함
			try {
				Thread.sleep(1000 * 60);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			
			//앞에서 저장해둔 쓰레드 리스트를 반복문 돌려서 상태를 확인함
			for (int i = 0; i < threadList.size(); i++) {
				Thread thread = threadList.get(i).getThread();
				if (thread == null) {
					cnt++;
					continue;
				}
				//해당 키워드의 쓰래드가 끝이나면 queryCount의 갯수를 증가시킴		
				if (thread.getState() == Thread.State.TERMINATED) {
					String query =threadList.get(i).getQuery();
					if(queryCount.containsKey(query)) {
						int count = queryCount.get(query);
						queryCount.put(query,count+1);
						
						//queryCount에서 해당 쿼리키워드가 3이 되면 그 제품들을 리스트에 저장해둠(3개의 마켓이 크롤링 끝난거니까 다 끝난거임)
						if(count+1 >=3) {
							pidList.addAll(productService.getProductidByQuery(query));
							queryCount.put(query, 0);
						}
					}else
						queryCount.put(query, 1);
						
					//끝난 쓰래드는 쓰레드 리스트에서 지움
					threadList.get(i).setThread(null);					
					cnt++;
				}
			}
			
			//pidList를 유사도 체크를 위해 SimilarityTherad로 실행 시킴
			//유사도 쓰래드가 끝났을때만 실행시켜줌
			if(similarity==null) {
				similarityThread.setPidList(pidList);
				pidList = new ArrayList<Long>();
				
				similarity=new Thread(similarityThread);
				similarity.start();
			}else if(similarity.getState() == Thread.State.TERMINATED) {
				similarityThread.setPidList(pidList);
				pidList = new ArrayList<Long>();
				
				similarity=new Thread(similarityThread);
				similarity.start();
			}
			
			if (cnt == threadcnt) {

				log.info("크롤링이 모두 끝났습니다");		
				break;
			} else
				log.info("현재 " + (threadcnt - cnt) + "개의 크롤링이 진행중입니다");
		}


		//현재 판매 물품 중 최저가부터 고가, 평균가 구함
		productService.setProductsMinMaxAvgPrice();
	
		//과거 품목 가격 추이
		//매일 매일 평균가격을 쌓아 나가면서 누적되게함
		datePriceService.setDatePrice();
		
		//가격별 판매 건수 조회
		
		
		//아직 pidList에 pid가 남아있으면 유사도 체크 해줘야되니까 쓰래드 끝날때까지 기다렸다가 실행되게함
		if(pidList.size()!=0) {
			while(similarity.getState() != Thread.State.TERMINATED) {
				try {
					Thread.sleep(1000 * 10);
					log.info(" 180 line while similarity");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			similarityThread.setPidList(pidList);
			pidList = new ArrayList<Long>();
			
			similarity=new Thread(similarityThread);
			similarity.start();		
		}
		
		log.info("Schedule end ----------------------------------------------------------------------------------");
	}



}
