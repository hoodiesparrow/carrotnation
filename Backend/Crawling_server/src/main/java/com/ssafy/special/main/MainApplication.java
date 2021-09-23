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
	private final SSHUtils ssh;
	private final ProductSellListRepository productSellListRepository;

	private final String sendFilePath = "/home/ubuntu/mysqltablefile/sellList.txt";
	private final String receiveFilePath = "/home/j5d205/receive/";

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
				
				File file = new File("/home/ubuntu/mysqltablefile/sellList.txt");
				if (file.exists()) {
					if (file.delete()) {
						log.info("파일삭제 성공");
						writedb();
					} else {
						log.info("파일삭제 실패");
					}
				} else {
					log.info("파일이 존재하지 않습니다.");
					writedb();
				}
				
				try {
					ssh.connectSSH();
					log.info("********전송시작*******");
					ssh.sendFileToOtherServer(sendFilePath, receiveFilePath, "sellList.txt");
					System.out.println(ssh.getSSHResponse("sudo cat " + receiveFilePath + "sellList.txt"));
					log.info("********전송끝 *******");
				} catch (JSchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			} else
				log.info("현재 " + (threadcnt - cnt) + "개의 크롤링이 진행중입니다");
		}

	}

	public void writedb() {
		LocalDateTime time = LocalDateTime.now().minusHours(1);
		String s = time.format(DateTimeFormatter.ofPattern("yyMMddHH"));
		StringBuilder sb = new StringBuilder();
		String fileName = "/home/ubuntu/mysqltablefile/sellList.txt";
		Product p;
		for (ProductSellList psl : productSellListRepository.txtProductSellList(Long.parseLong(s))) {
			p = psl.getProductId();
			sb.append(psl.getId()).append("|").append(psl.getMarket()).append("|").append(psl.getContent()).append("|")
					.append(psl.getCreateDate()).append("|").append(psl.getCycle()).append("|")
					.append(psl.getLocation()).append("|").append(psl.getPrice()).append("|").append(psl.getTitle())
					.append("|").append(p.getName()).append("\n");
		}
		try {

			// 파일 객체 생성
			File file = new File(fileName);

			// true 지정시 파일의 기존 내용에 이어서 작성
			FileWriter fw = new FileWriter(file, true);

			// 파일안에 문자열 쓰기
			fw.write(sb.toString());
			fw.flush();

			// 객체 닫기
			fw.close();
			log.info("*******파일쓰기 완료*********");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
