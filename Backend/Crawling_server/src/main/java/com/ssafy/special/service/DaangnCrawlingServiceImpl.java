package com.ssafy.special.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.special.domain.ExceptionKeyword;
import com.ssafy.special.domain.Product;
import com.ssafy.special.domain.ProductQuery;
import com.ssafy.special.domain.ProductSellList;
import com.ssafy.special.domain.QueryExceptionKeyword;
import com.ssafy.special.domain.RequireKeyword;
import com.ssafy.special.dto.ProductDTO;
import com.ssafy.special.repository.ExceptionKeywordRepository;
import com.ssafy.special.repository.ProductQueryRepository;
import com.ssafy.special.repository.ProductRepository;
import com.ssafy.special.repository.ProductSellListRepository;
import com.ssafy.special.repository.QueryExceptionKeywordRepository;
import com.ssafy.special.repository.RequireKeywordRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class DaangnCrawlingServiceImpl implements DaangnCrawlingService {

	private final ProductRepository productRepository; 
	private final ExceptionKeywordRepository exceptionKeywordRepository; 
	private final RequireKeywordRepository requireKeywordRepository; 
	private final ProductQueryRepository productQueryRepository; 
	private final QueryExceptionKeywordRepository queryExceptionKeywordRepository; 
	private final ProductSellListRepository productSellListRepository; 
	
	
	
	
	// carrot1 + "검색어" + caroot2 + 페이지번호(1부터 시작)
	private static String carrot1 = "https://www.daangn.com/search/";
	private static String carrot2 = "/more/flea_market?page=";
	private static String carrotDetail = "https://www.daangn.com";
	
	private static List<ProductDTO> productList;
	
	
	@Scheduled(fixedDelay = 1000 * 60 * 30)
	@Transactional
	public void crawlingProducts() {
		
		//productQuery테이블에서 query컬럼 리스트를 가져옴
		List<ProductQuery> productQueryList = productQueryRepository.findAll();
	
		for(ProductQuery productQuery : productQueryList) {
			productList=new ArrayList<ProductDTO>();
			log.info("(당근)해당 검색어를 크롤링 중입니다 "+ productQuery.getQuery());
			crawlingProduct(productQuery);
		}			
	}
	

	@Transactional(readOnly = true)
	public void crawlingProduct(ProductQuery productQuery) {
		final String market = "daangn";
		final String commonMarket = "common";
		
		//productQuery로 크롤링을 진행하여 검색결과(productDTO)를 쿼리제외키워드로 필터링함
		int page=1;
		log.info("(당근)상품 목록을 크롤링 중입니다");
		while(true) {
			try {
				listCrawling(productQuery, page);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			page++;
			
			if(page>3)
				break;
		}
		
		
		log.info("(당근)부적합한 물건을 제외하는 중입니다");
		for(ProductDTO p : productList) {
			List<Product> products=productRepository.findByQuery(productQuery).orElse(new ArrayList<Product>());
			for(Product product:products) {
				//데이터 베이스에 productName을 검색해서 해당 제품을 찾기위한 검색 query, 제외키워드, 필수키워드들을 가져옴
				List<String> exceptionKeyword=new ArrayList<>();
				List<String> requireKeyword=new ArrayList<String>(); 
				exceptionKeyword=exceptionKeywordRepository.findByProductIdAndMarket(product, market).orElse(new ArrayList<ExceptionKeyword>()).stream().map(ExceptionKeyword::getKeyword).collect(Collectors.toList());
				//공통 필수 키워드
				requireKeyword=requireKeywordRepository.findByProductIdAndMarket(product, commonMarket).orElse(new ArrayList<RequireKeyword>()).stream().map(RequireKeyword::getKeyword).collect(Collectors.toList());
				//당근마켓 필수 키워드
				requireKeyword.addAll(requireKeywordRepository.findByProductIdAndMarket(product, market).orElse(new ArrayList<RequireKeyword>()).stream().map(RequireKeyword::getKeyword).collect(Collectors.toList()));
				
				//제외 키워드가지고 있으면 패스
				if(hasExceptionKeyword(p.getTitle(), exceptionKeyword)) {
					continue;
				}
				
				//제목에서 필수 키워드를 가지고 있으면 품목 지정함
				if(hasRequireKeyword(p.getTitle(),requireKeyword)) {
					p.setName(product.getName());//품목 지정
					try {
						detailCrawling(p);//게시글 날짜 지정
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(hasRequireKeyword(p.getContent(), requireKeyword)){//제목에서 필수 키워드가 없으면 내용에서 필수 키워드를 가지고 있는지 확인함
					p.setName(product.getName());//품목 지정
					try {
						detailCrawling(p);//게시글 날짜 지정
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}
				
				//name이 지정되었으면 ProductSellList테이블에 삽입
				if(p.getName()!=null) {
					
					ProductSellList sellList=new ProductSellList();
					sellList.setId(Integer.parseInt(p.getSeq()));
					sellList.setMarket(market);
					sellList.setProductId(product);
					sellList.setTitle(p.getTitle());
					sellList.setPrice(p.getPrice());
					sellList.setCreateDate(p.getDate());
					sellList.setLink(p.getLink());
					sellList.setLocation(p.getLocation());				
					System.out.println(Integer.parseInt(p.getSeq()) + "    "+sellList); // 디버깅용으로 만듬 나중에 지워야함 - 추헌국
					boolean result = insertProductSellList(sellList);
					if(!result) {
						log.info("(당근)데이터 삽입에 실패 했습니다");
					}
				}
			}
		}
		log.info("(당근)크롤링 및 분류가 완료되었습니다");
	}
	
	@Transactional(readOnly = true)
	// 목록 크롤링을 통해 검색어에 해당하는 게시글 id를 가져옴
	private void listCrawling(ProductQuery productQuery, int page) throws IOException {
		String url = carrot1 + productQuery.getQuery() + carrot2 + page;
		Document doc = Jsoup.connect(url).get();

		Elements contents = doc.select("article");
		
		//크롤링결과(productDTO)를 쿼리제외키워드로 필터링함(케이스, 필름 이런거 거름)
		List<String> queryExceptionKeywordList = queryExceptionKeywordRepository.findByQuery(productQuery).orElse(new ArrayList<QueryExceptionKeyword>()).stream().map(QueryExceptionKeyword::getKeyword).collect(Collectors.toList());
		
		for (Element content : contents) {
			// 걸러진 데이터들만 저장되게함			
			// 시간정보말고는 리스트에서 저장
			ProductDTO product = new ProductDTO();
			
			// 제목
			String title=content.select(".article-title").text();
			product.setTitle(title);
			
			//제목에 제외키워드가 들어가있으면 productList에 안넣음
			if(hasExceptionKeyword(title, queryExceptionKeywordList)) {
				continue;
			}
			
			// 가격
			String price = content.select(".article-price ").text();
			if(price.contains("나눔"))
				continue;
			price = price.substring(0, price.length() - 1);// 원 제거
			price = price.replace(",", "");// 콤마 제거
			if(price.length()==0)// 가격 없음(-)인 경우는 패스
				continue;
			product.setPrice(Long.parseLong(price));
			
			//게시글 내용
			product.setContent(content.select(".article-content").text());
			
			// 원본 게시글 링크
			product.setLink(carrotDetail+content.select("a").attr("href"));
			
			// 원본 게시글 pid
			product.setSeq(content.select("a").attr("href").split("/")[2]);
			
			// 이미지 링크
			product.setImg(content.select(".card-photo > img").attr("src"));
			
			//지역
			product.setLocation(content.select(".article-region-name").text());
			
			productList.add(product);
			//시간정보는 상세 게시글에 나와있음			
		}
		return;
	}
	
	private void detailCrawling(ProductDTO product)throws IOException{		
		// 게시글 상세보기 크롤링
		Document doc = Jsoup.connect(product.getLink()).get();

		Element content = doc.selectFirst("#content > #article-description");

		// 날짜, 몇시간 전인지
		String time=content.select("#article-category time").text();

		String[] tmp= time.split(" ");
		time= tmp[tmp.length-2];
		
		LocalDateTime dateTime;
		
		if(time.contains("년")) {
			dateTime=LocalDateTime.now().minusYears(Long.parseLong(time.replace("년", "")));
		}else if(time.contains("달")) {
			dateTime=LocalDateTime.now().minusMonths(Long.parseLong(time.replace("달", "")));
		}else if(time.contains("일")) {
			dateTime=LocalDateTime.now().minusDays(Long.parseLong(time.replace("일", "")));
		}else if(time.contains("시간")) {
			dateTime=LocalDateTime.now().minusHours(Long.parseLong(time.replace("시간", "")));
		}else if(time.contains("분")) {
			dateTime=LocalDateTime.now().minusMinutes(Long.parseLong(time.replace("분", "")));
		}else {
			dateTime=LocalDateTime.now().minusSeconds(Long.parseLong(time.replace("초", "")));
		}
		
		product.setTime(time.replace("끌올", ""));
		product.setDate(dateTime);
		
		return;	
	}
	
	//제외키워드 포함하고 있는지 확인
	private boolean hasExceptionKeyword(String str, List<String> exceptionKeyword) {
		for(String keyword : exceptionKeyword) {
			if(str.toLowerCase().contains(keyword)) {
				//제외 키워드를 포함하고 있으면 버려야함
				return true;
			}
		}		
		return false;
	}
	private boolean hasRequireKeyword(String str, List<String> requireKeyword) {
		//andOr키워드
		/* 예시) 아이폰 12 프로를 검색하고 싶은경우 검색키워드는 아이폰 12 & (프로 | pro)가 될 것이다
		 * 이런 경우에 크게는 And연산이지만 내부적으로 or연산이 필요한 경우가 존재한다 이를 위해 구현함
		 * 
		 * 용량의 경우 : 아이폰 12 & (프로|pro) & (128)
		 */
		//필수 키워드는 제목뿐 아니라 내용도 확인하도록 함 (용량은 제목에 안적힌 경우가 많아서 내용도 확인함)
		for(String orKeyword : requireKeyword) {
			boolean isContain=false;//or연산이기 때문에 하나라도 포함하면 됨
			for(String keyword: orKeyword.split(",")) {
				//제목 소문자로 변환시키고 비교하도록함
				if(str.toLowerCase().contains(keyword)) {
					isContain=true;
					break;
				}
			}
			if(!isContain) {
				//하나도 포함안하면 버려야함
				return false;
			}
		}
		return true;
	}
	
	@Transactional
	private boolean insertProductSellList(ProductSellList sellList) {
		try {
			productSellListRepository.save(sellList);
		} catch (Exception e) {
			return false;
		}		
		return true;
	}
}
