package com.ssafy.special;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CrawlingServerApplicationTests {

//	@Autowired
//	DaangnCrawlingServiceImpl daangnCrawlingService;
//	@Autowired
//	JoongnaCrawlingService joongnaCrawlingService;
//	@Autowired
//	ThunderCrawlingService thunderCrawlingService;
//	@Autowired
//	MainApplication mainApplication;
//	@Autowired
//	ProductSellListRepository productSellListRepository; 
	
//	@Test
//	@Transactional
	void contextLoads() {
//		daangnCrawlingService.crawlingProducts();
//		joongnaCrawlingService.joongnainit();
//		mainApplication.crawlingStart();		
	}

	@Test
	void test() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime date_now=LocalDateTime.now();
		System.out.println(date_now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		System.out.println(date_now.format(formatter));
//		2021-09-17 21:00:38
	}
}
