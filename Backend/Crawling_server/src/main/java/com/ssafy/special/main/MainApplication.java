package com.ssafy.special.main;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.JSchException;
import com.ssafy.special.controller.SSHUtils;
import com.ssafy.special.repository.ProductRepository;
import com.ssafy.special.repository.ProductSellListRepository;
import com.ssafy.special.service.KeywordInfoService;

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
	private final ProductRepository productRepository;
	private final SSHUtils ssh;
	private final ProductSellListRepository productSellListRepository;

	private final String sendFilePath = "/home/ubuntu/mysqltablefile/sellList.txt";
	private final String receiveFilePath = "/home/j5d205/receive/";

	@Scheduled(fixedRate = 1000 * 60 * 60) // 1시간
	public void crawlingStart() {
		LocalDateTime time = LocalDateTime.now().minusHours(1);
		String s = time.format(DateTimeFormatter.ofPattern("yyMMddHH"));
		
		
//		File file = new File("/home/ubuntu/mysqltablefile/sellList.txt");
//		if (file.exists()) {
//			if (file.delete()) {
//				log.info("파일삭제 성공");
//			} else {
//				log.info("파일삭제 실패");
//			}
//		} else {
//			log.info("파일이 존재하지 않습니다.");
//		}
		productSellListRepository.txtProductSellList(Long.parseLong(s));
		try {
			ssh.connectSSH();
//			System.out.println(ssh.getSSHResponse("ls -al"));
			
			log.info("********DB sellList txt변환했습니다.*******");
			log.info("********전송시작*******");
			ssh.sendFileToOtherServer(sendFilePath,receiveFilePath,"sellList.txt");
			System.out.println(ssh.getSSHResponse("sudo cat "+receiveFilePath+"sellList.txt"));
			log.info("********전송끝 *******");
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		List<ProductQuery> productQuery = queryInfoService.getProductQueryList();
//		
//		List<Thread> threadList=new ArrayList<Thread>();
//		
//		for (ProductQuery query : productQuery) {
//			List<String> queryExceptionKeywordList = queryInfoService.getQueryExceptionKeywordList(query);
//			daangnMultiThreadCrawling.setProductQuery(query);
//			daangnMultiThreadCrawling.setQueryExceptionKeywordList(queryExceptionKeywordList);
//			
//			JoongnaMultiThreadCrawling.setProductQuery(query);
//			JoongnaMultiThreadCrawling.setQueryexceptionlist(queryExceptionKeywordList);
//			
//			thunderMultiThreadCrawling.setProductQuery(query);
//			thunderMultiThreadCrawling.setQueryExceptionKeywordList(queryExceptionKeywordList);
//			
//			Thread daangn = new Thread(daangnMultiThreadCrawling);
//			Thread joongna = new Thread(JoongnaMultiThreadCrawling);
//			Thread thunder = new Thread(thunderMultiThreadCrawling);
//			
//			daangn.start();
//			joongna.start();
//			thunder.start();
//			
//			threadList.add(daangn);
//			threadList.add(joongna);
//			threadList.add(thunder);
//		}
//		
//		int threadcnt=threadList.size();
//		while(true) {
//			int cnt=0;
//			try {
//				Thread.sleep(1000 * 60);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			for(int i=0;i<threadList.size();i++) {
//				Thread thread=threadList.get(i);
//				if(thread==null) {
//					cnt++;
//					continue;
//				}
//				if(thread.getState() == Thread.State.TERMINATED) {
//					threadList.set(i, null);
//					cnt++;
//				}
//			}
//			if(cnt == threadcnt) {
//				log.info("크롤링이 모두 끝났습니다");
//				LocalDateTime time = LocalDateTime.now().minusHours(1);
//				String s = time.format(DateTimeFormatter.ofPattern("yyMMddHH"));
//				try {
//					ssh.connectSSH();
//					ssh.getSSHResponse("sudo rm sellList.txt");
//					productSellListRepository.txtProductSellList(Long.parseLong(s));
//					log.info("********DB sellList txt변환했습니다.*******");
//					log.info("********전송시작*******");
//					ssh.sendFileToOtherServer(sendFilePath,receiveFilePath,"sellList.txt");
//					System.out.println(ssh.getSSHResponse("sudo cat "+receiveFilePath+"sellList.txt"));
//					log.info("********전송끝 *******");
//				} catch (JSchException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				break;
//			}else
//				log.info("현재 "+ (threadcnt-cnt) + "개의 크롤링이 진행중입니다");
//		}

	}
}
