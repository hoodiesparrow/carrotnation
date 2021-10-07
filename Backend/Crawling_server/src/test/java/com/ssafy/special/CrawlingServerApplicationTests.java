package com.ssafy.special;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.special.controller.AdressToCoorUtils;
import com.ssafy.special.controller.SSHUtils;
import com.ssafy.special.domain.ProductQuery;
//import com.ssafy.special.domain.Product;
import com.ssafy.special.domain.ProductSellList;
import com.ssafy.special.dto.ByDistance;
import com.ssafy.special.dto.PriceStepResponseDTO;
import com.ssafy.special.dto.ProductPriceResponseDTO;
import com.ssafy.special.main.MainApplication;
import com.ssafy.special.repository.CoordinateRepository;
import com.ssafy.special.repository.ProductRepository;
import com.ssafy.special.repository.ProductSellArticleSimilerRepository;
import com.ssafy.special.repository.ProductSellListRepository;
import com.ssafy.special.service.DaangnCrawlingServiceImpl;
import com.ssafy.special.service.JoongnaCrawlingService;
import com.ssafy.special.service.KeywordInfoService;
import com.ssafy.special.service.ProductByPriceService;
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
	KeywordInfoService queryInfoService;
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
	AdressToCoorUtils adresstocoor;
	@Autowired
	SimilarityService similarityService;
	@Autowired
	ProductByPriceService pby;
	@Autowired
	CoordinateRepository coordinateRepository;
//	@Test
//	@Transactional
	void contextLoads() {
//		daangnCrawlingService.crawlingProducts();
//		joongnaCrawlingService.joongnainit();
//		mainApplication.crawlingStart();		
	}

//	@Test
	public void TestSSH() throws Exception {
		String cmd = "ls -lrt";
		ssh.connectSSH();
		String sendFilePath = "C:\\SSAFY\\aws\\test\\sellList.txt";
//		String sendFilePath = "/home/ubuntu/mysqltablefile/sellList.txt";
		String receiveFilePath = "/home/j5d205/receive/";
		ssh.sendFileToOtherServer(sendFilePath, receiveFilePath, "sellList.txt");
		System.out.println(ssh.getSSHResponse("cat " + receiveFilePath + "sellList.txt"));
	}

//	@Test
	void test() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime date_now = LocalDateTime.now();

		System.out.println(date_now.format(DateTimeFormatter.ofPattern("yyMMddHH")));
//		System.out.println(date_now.format(formatter));
//		2021-09-17 21:00:38

	}

	//@Test
	void komoran() {
		Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
		String s = "🔎상품설명🔍\r\n" + 
				" \r\n" + 
				" - 기종 : 아이폰12\r\n" + 
				" - 색상 : 퍼플\r\n" + 
				" - 등급 : SS급 (미세기스 조차 없으며 거의 새폰급입니다.)\r\n" + 
				" \r\n" + 
				" - 배터리효율 : 100%\r\n" + 
				" - 구성 : 핸드폰+방탄필름(5개)+케이스(2종류)+박스\r\n" + 
				"              ❗️충전기는 없습니다❗️\r\n" + 
				" - 리퍼기간 : 2022년 6월 19일\r\n" + 
				" \r\n" + 
				" - 직거래 : 용인시 양지면에 위치해 있는 \"양지면사무소\"\r\n" + 
				"                    에서 거래 가능합니다\r\n" + 
				" - 택배 : (CU&GS25&세븐일레븐)편의점 택배 및\r\n" + 
				"                                                                (CJ대한통운)\r\n" + 
				" \r\n" + 
				" - 통신사 : 모든 3사 통신사 가능\r\n" + 
				" ";
		String strToAnalyze = s.replaceAll("[^\\uAC00-\\uD7AF\\u1100-\\u11FF\\u3130-\\u318F0-9]+", " ");
		System.out.println(strToAnalyze);
		KomoranResult analyzeResultList = komoran.analyze(strToAnalyze);

//		System.out.println(analyzeResultList.getPlainText());

		List<Token> tokenList = analyzeResultList.getTokenList();
		for (Token token : tokenList) {
			if ("NNP".equals(token.getPos()) || "NNG".equals(token.getPos()) || "SN".equals(token.getPos())) {
				System.out.println(token.getMorph());
			}
//            System.out.format("(%2d, %2d) %s/%s\n", token.getBeginIndex(), token.getEndIndex(), token.getMorph(), token.getPos());
		}
	}

	//@Test
	void query() {
		ProductSellList test = productSellListRepository.findById((long) 1).orElse(null);
		System.out.println(test);
//		System.out.println(test.get());
//		Long cycle = Long.parseLong("21092518");
//		List<ProductSellList> list = productSellListRepository
//				.getRecentProductSellList(cycle, test.get().getProductId().getId())
//				.orElse(new ArrayList<ProductSellList>());
//
//		for (ProductSellList psl : list) {
//			System.out.println(psl);
//		}
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
//		similarityService.similarityProduct();
//		String f = "hadoop fs -put /home/j5d205/receive/" + 1 + ".txt "+ "Set/"+1+".txt";
//		System.out.println(f);
	}

	// @Test
	void qq() {
		List<ProductPriceResponseDTO> list = productSellListRepository
				.getProductByPrice(Long.parseLong("21092912"), (long) 10)
				.orElse(new ArrayList<ProductPriceResponseDTO>());
		long maxPrice = list.get(0).getMaxPrice();
		long stepbase = priceRound(maxPrice * 0.1);
		long Fstep = priceRound(maxPrice * 0.28);
		long Sstep = priceRound(maxPrice * 0.46);
		long Thstep = priceRound(maxPrice * 0.64);
		long Fostep = priceRound(maxPrice * 0.82);

		Map<String, PriceStepResponseDTO> pricemap = new HashMap<String, PriceStepResponseDTO>();

		int[] arr = new int[5];
		long[] arr2 = { stepbase, Fstep, Sstep, Thstep, Fostep, maxPrice };
		System.out.println(Fstep + " " + Sstep + " " + Thstep + " " + Fostep + " " + maxPrice);
		for (ProductPriceResponseDTO ppr : list) {
			if (stepbase >= ppr.getPrice()) {
				continue;
			} else if (stepbase < ppr.getPrice() && Fstep >= ppr.getPrice()) {
				arr[0]++;
			} else if (Fstep < ppr.getPrice() && Sstep >= ppr.getPrice()) {
				arr[1]++;
			} else if (Sstep < ppr.getPrice() && Thstep >= ppr.getPrice()) {
				arr[2]++;
			} else if (Thstep < ppr.getPrice() && Fostep >= ppr.getPrice()) {
				arr[3]++;
			} else if (Fostep < ppr.getPrice() && maxPrice >= ppr.getPrice()) {
				arr[4]++;
			}
		}
		PriceStepResponseDTO psr;
		psr = new PriceStepResponseDTO();
		psr.setMin(arr2[0]);
		psr.setMax(arr2[1]);
		psr.setCount(0);
		pricemap.put("1Step", psr);
		for (int i = 1; i < 5; i++) {
			psr = new PriceStepResponseDTO();
			psr.setMin(arr2[i]);
			psr.setMax(arr2[i + 1]);
			psr.setCount(arr[i]);
			pricemap.put(i + 1 + "Step", psr);
		}

	}

	public long priceRound(double price) {
		String temp = Integer.toString((int) Math.round(price));
		int round = (int) Math.round(Integer.parseInt(temp.substring(0, temp.length() - 3)) * 0.1);
		temp = Integer.toString(round) + "0000";
		return Long.parseLong(temp);
	}

	void testadress() {
		String s = "서울 노량진역";
		System.out.println(adresstocoor.AdressToCoorUtilstest(s));
	}

	void adress() {
		String s = "{\"documents\":[{\"address\":{\"mountain_yn\":\"N\",\"h_code\":\"2820069000\",\"region_3depth_name\":\"\",\"main_address_no\":\"\",\"x\":\"126.729305079841\",\"sub_address_no\":\"\",\"y\":\"37.4057349526159\",\"address_name\":\"인천 남동구 논현1동\",\"region_2depth_name\":\"남동구\",\"region_3depth_h_name\":\"논현1동\",\"region_1depth_name\":\"인천\",\"b_code\":\"\"},\"address_type\":\"REGION\",\"x\":\"126.729305079841\",\"y\":\"37.4057349526159\",\"address_name\":\"인천 남동구 논현1동\",\"road_address\":null}],\"meta\":{\"total_count\":1,\"is_end\":true,\"pageable_count\":1}}";
		String d = "{\"documents\":[],\"meta\":{\"is_end\":true,\"pageable_count\":0,\"total_count\":0}}";
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node;
		JsonNode sgg;
		try {
			node = mapper.readTree(s);
			sgg = node.get("documents").get(0).get("address");
			System.out.println(sgg.get("x"));
			System.out.println(sgg.get("y"));
			node = mapper.readTree(d);
			sgg = node.get("documents"); // []
			System.out.println(sgg);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//@Test
	void qquu() {
		List<ProductQuery> productQuery = queryInfoService.getProductQueryList();
		
		for (ProductQuery query : productQuery) {
			List<String> queryExceptionKeywordList = queryInfoService.getQueryExceptionKeywordList(query);
			joongnaCrawlingService.joongnainit(query,queryExceptionKeywordList);
		}
	}
	
	//@Test
	void coordinate() {
//		126.939276884816, 37.5651508821041,
		List<String> tte = new ArrayList<String>();
		tte.add("joonnaApp");
		tte.add("daangn");
		List<ByDistance> list = productSellListRepository.nearProduct(126.939276884816, 37.5651508821041, (long) 52, tte, PageRequest.of(0, 20, Sort.Direction.ASC, "createDate"))
				.orElse(null);
//		List<ProductSellListByDistanceResponseDTO> products = new ArrayList<ProductSellListByDistanceResponseDTO>();

//		ProductSellListByDistanceResponseDTO dto;
		for (ByDistance bd : list) {
//			dto = new ProductSellListByDistanceResponseDTO();
//			dto.setId(bd.getId());
//			dto.setAid(bd.getAid());
//			dto.setMarket(bd.getMarket());
//			dto.setProductId(bd.getProductId());
//			dto.setTitle(bd.getTitle());
//			dto.setContent(bd.getTitle());
//			dto.setPrice(bd.getPrice());
			System.out.println(bd.getPrice());
			System.out.println(bd.getMarket());
//			System.out.println(bd.getDistance());
//			System.out.println(bd.getProductId());
			System.out.println(bd.getCreateDate());
//			dto.setCreateDate(bd.getCreateDate());
//			dto.setLink(bd.getLink());
//			dto.setImg(bd.getImg());
//			dto.setLocation(bd.getLocation());
//			dto.setX(bd.getX());
//			dto.setY(bd.getY());
//			dto.setCycle(bd.getCycle());
//			dto.setDistance(bd.getDistance());
			
//			products.add(dto);
		}
		
	}
	//@Test
	void count() {
		List<String> tte = new ArrayList<String>();
		tte.add("joonnaApp");
		tte.add("daangn");
		int s =productSellListRepository.nearProductCount(126.939276884816, 37.5651508821041, (long) 52, tte);
		System.out.println(s);
	}
	//@Test
	void TT() {
		System.out.println(pby.byPrice((long) 5, Long.parseLong("21100519")));
	}
}
