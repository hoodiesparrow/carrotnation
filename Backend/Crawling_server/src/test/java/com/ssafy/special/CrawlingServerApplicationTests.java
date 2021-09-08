package com.ssafy.special;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.special.service.DaangnCrawlingService;

@SpringBootTest
class CrawlingServerApplicationTests {

	@Autowired
	DaangnCrawlingService daangnCrawlingService;
	
	@Test
	void contextLoads() {
		daangnCrawlingService.crawlingProducts();
	}

}
