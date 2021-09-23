package com.ssafy.special;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.special.controller.SSHUtils;
import com.ssafy.special.main.MainApplication;
import com.ssafy.special.repository.ProductSellListRepository;
import com.ssafy.special.service.DaangnCrawlingServiceImpl;
import com.ssafy.special.service.JoongnaCrawlingService;
import com.ssafy.special.service.ThunderCrawlingService;

import lombok.extern.log4j.Log4j2;



@SpringBootTest
@Log4j2
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
	@Autowired
	SSHUtils ssh;
	
//	@Test
//	@Transactional
	void contextLoads() {
//		daangnCrawlingService.crawlingProducts();
//		joongnaCrawlingService.joongnainit();
//		mainApplication.crawlingStart();		
	}

//	@Test
	public void TestSSH() throws Exception {
		String cmd ="ls -lrt";
		ssh.connectSSH();
		String sendFilePath = "C:\\SSAFY\\aws\\test\\sellList.txt";
//		String sendFilePath = "/home/ubuntu/mysqltablefile/sellList.txt";
		String receiveFilePath = "/home/j5d205/receive/";
		ssh.sendFileToOtherServer(sendFilePath,receiveFilePath,"sellList.txt");
		System.out.println(ssh.getSSHResponse("cat "+receiveFilePath+"sellList.txt"));
	}
//	@Test

	public void writedb() {
		String s = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHH"));
		log.info(productSellListRepository.txtProductSellList(s));
	}


	@Test
	void test() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime date_now=LocalDateTime.now();
		System.out.println(date_now.format(DateTimeFormatter.ofPattern("yyMMddHH")));
//		System.out.println(date_now.format(formatter));
//		2021-09-17 21:00:38

	}
}
