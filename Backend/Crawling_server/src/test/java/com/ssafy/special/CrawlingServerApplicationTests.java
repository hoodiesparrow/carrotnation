package com.ssafy.special;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.special.controller.SSHUtils;
import com.ssafy.special.domain.Product;
import com.ssafy.special.domain.ProductSellArticleSimiler;
//import com.ssafy.special.domain.Product;
import com.ssafy.special.domain.ProductSellList;
import com.ssafy.special.main.MainApplication;
import com.ssafy.special.repository.ProductRepository;
import com.ssafy.special.repository.ProductSellArticleSimilerRepository;
import com.ssafy.special.repository.ProductSellListRepository;
import com.ssafy.special.service.DaangnCrawlingServiceImpl;
import com.ssafy.special.service.JoongnaCrawlingService;
import com.ssafy.special.service.SimilarityService;
import com.ssafy.special.service.ThunderCrawlingService;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;


//import lombok.extern.log4j.Log4j2;

@SpringBootTest
//@Log4j2
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
	ProductSellArticleSimilerRepository productSellArticleSimilerRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	SSHUtils ssh;
	
	@Autowired
	SimilarityService similarityService;
	
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
	void test() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime date_now=LocalDateTime.now();
	
		System.out.println(date_now.format(DateTimeFormatter.ofPattern("yyMMddHH")));
//		System.out.println(date_now.format(formatter));
//		2021-09-17 21:00:38

	}
//	@Test
	void komoran() {
		Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
		String s = "갤럭시 톰브라운 버즈 / 와치 단품,세트판매/새상품\n🍅🍅🍅🍅근거리 퀵배송가능~~🍅🍅🍅🍅\n😊미개봉\n😊버즈만 구매시 :30만원\n😊와치만 구매시 :85만원\n😊버즈 + 와치 구매 :110만원\n\n간지 납니다:)\n직거래 지역은 👉1대1 챗 👈주세요^^\n\n👌👌와치는 가죽 스트랩 같이 드려요!";
        String strToAnalyze = s.replaceAll("[^\\uAC00-\\uD7AF\\u1100-\\u11FF\\u3130-\\u318F]+", " ");
        System.out.println(strToAnalyze);
        KomoranResult analyzeResultList = komoran.analyze(strToAnalyze);

        System.out.println(analyzeResultList.getPlainText());

        List<Token> tokenList = analyzeResultList.getTokenList();
        for (Token token : tokenList) {
        	if("NNP".equals(token.getPos())||"NNG".equals(token.getPos())) {
        		System.out.println(token.getMorph());
        	}
//            System.out.format("(%2d, %2d) %s/%s\n", token.getBeginIndex(), token.getEndIndex(), token.getMorph(), token.getPos());
        }
	}
	
//	@Test
	void query() {
		Optional<ProductSellList> test =productSellListRepository.findByIdAndMarket((long)20290899, "joonnaApp");
		System.out.println(test.get());
		Long cycle = Long.parseLong("21092518");
		List<ProductSellList> list = productSellListRepository.getRecentProductSellList(cycle, test.get().getProductId().getId()).orElse(new ArrayList<ProductSellList>());
		
		for(ProductSellList psl:list) {
			System.out.println(psl);
		}
	}
//	@Test
	void Service() {
//		if (!ssh.checksession()) {
//			try {
//				ssh.connectSSH();
//			} catch (JSchException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		System.out.println(ssh.getSSHResponse("/usr/local/hadoop/bin/hadoop fs -mkdir Test"));
//		String f = "/usr/local/hadoop/bin/hadoop fs -put /home/j5d205/receive/" + 1 + ".txt "+ "Test/"+1+".txt";
//		ssh.getSSHResponse(f);
//		System.out.println(ssh.getSSHResponse("/usr/local/hadoop/bin/hadoop fs -ls"));

//		ssh.getSSHResponse(f);
//		ssh.getSSHResponse("hadoop fs -ls Set");
		similarityService.similarityProduct();
//		String f = "hadoop fs -put /home/j5d205/receive/" + 1 + ".txt "+ "Set/"+1+".txt";
//		System.out.println(f);
	}
	
//	@Test
	void qq() {
		List<Product> aa =productRepository.findAll();
		for(Product p:aa) {
			System.out.println(p);
		}
	}
	
//	@Test
	void sef() {
		String s = "164274556|thunder	164517194|thunder	100\r\n" + 
				"164335475|thunder	164539903|thunder	97\r\n" + 
				"164335880|thunder	164539903|thunder	92\r\n" + 
				"164335880|thunder	164580213|thunder	95\r\n" + 
				"164340425|thunder	164567687|thunder	96\r\n" + 
				"164340425|thunder	164581126|thunder	96\r\n" + 
				"164342091|thunder	164461957|thunder	96\r\n" + 
				"164359845|thunder	164370705|thunder	92\r\n" + 
				"164370705|thunder	164461957|thunder	95\r\n" + 
				"164370705|thunder	164564593|thunder	95\r\n" + 
				"164407603|thunder	164547946|thunder	96\r\n" + 
				"164444819|thunder	164507338|thunder	95\r\n" + 
				"164461957|thunder	164507338|thunder	95\r\n" + 
				"164492996|thunder	164498696|thunder	96\r\n" + 
				"164492996|thunder	164505577|thunder	100\r\n" + 
				"164494853|thunder	164547946|thunder	95\r\n" + 
				"164498287|thunder	164502180|thunder	97\r\n" + 
				"164498287|thunder	164599610|thunder	100\r\n" + 
				"164502180|thunder	164567687|thunder	93";
		
		StringTokenizer st = new StringTokenizer(s,"\r\n");
		StringTokenizer ss,temp;

		while(st.hasMoreTokens()) {
			ss = new StringTokenizer(st.nextToken(),"\t");
			ProductSellArticleSimiler p = new ProductSellArticleSimiler();
			temp = new StringTokenizer(ss.nextToken(),"|");
			p.setArticleA(new ProductSellList(Long.parseLong(temp.nextToken()),temp.nextToken()));

			
			temp = new StringTokenizer(ss.nextToken(),"|");
			p.setArticleB(new ProductSellList(Long.parseLong(temp.nextToken()), temp.nextToken()));
			
			p.setSimilarity(Double.parseDouble(ss.nextToken()));
			insertProductSellArticleSimiler(p);
		}
	}
	@Test
	void contain() {
		String s ="아이폰 기가 풀 박스 실버 정상 해지 완료 공기 상태 특성 테두리 정도 고장 수리 내역 교체 적도 배터리 요즘 사설 프로 케이스 사진 액정 보호 필름 드릴 휴대폰 연락 인치 아이패드 프로 필요 추 금 신도";
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(s);
		
		while(st.hasMoreTokens()) {
			String ss = st.nextToken();
			if(sb.toString().contains(ss)) {
				System.out.println(ss);
			}else {
				sb.append(ss).append(" ");
			}
		}
		
	}
	@Transactional
	private boolean insertProductSellArticleSimiler(ProductSellArticleSimiler psas) {
		try {
			productSellArticleSimilerRepository.save(psas);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
