package com.ssafy.special;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.special.domain.ProductSellList;
import com.ssafy.special.main.MainApplication;
import com.ssafy.special.repository.ProductSellListRepository;
import com.ssafy.special.service.DaangnCrawlingServiceImpl;
import com.ssafy.special.service.JoongnaCrawlingService;
import com.ssafy.special.service.ThunderCrawlingService;

@SpringBootTest
class CrawlingServerApplicationTests {

	@Autowired
	DaangnCrawlingServiceImpl daangnCrawlingService;
	@Autowired
	JoongnaCrawlingService joongnaCrawlingService;
	@Autowired
	ThunderCrawlingService thunderCrawlingService;
	@Autowired
	MainApplication mainApplication;
	@Autowired
	ProductSellListRepository productSellListRepository; 
	
//	@Test
//	@Transactional
	void contextLoads() {
//		daangnCrawlingService.crawlingProducts();
//		joongnaCrawlingService.joongnainit();
//		mainApplication.crawlingStart();
		
		ProductSellList sellList=new ProductSellList();
		sellList.setId(123);
		sellList.setMarket("daangn");
		sellList.setPrice(111);
		ProductSellList sellList2=new ProductSellList();
		sellList2.setId(123);
		sellList2.setMarket("daangn");
		sellList2.setPrice(222);
//		insertProductSellList(sellList);
//		insertProductSellList(sellList2);

		Thread thread1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				insertProductSellList(sellList);				
			}
		});
		Thread thread2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				insertProductSellList(sellList2);				
			}
		});
//		thread1.start();
//		thread2.start();
		
	}

	
//	@Transactional
	private boolean insertProductSellList(ProductSellList sellList) {
		try {
			productSellListRepository.save(sellList);
		} catch (DataIntegrityViolationException e) {
			return false;
		}catch (Exception e ) {
			return false;
		}
		return true;
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
