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

import com.ssafy.special.domain.ExceptionKeyword;
import com.ssafy.special.domain.Product;
import com.ssafy.special.domain.ProductQuery;
import com.ssafy.special.domain.RequireKeyword;
import com.ssafy.special.dto.ProductDTO;
import com.ssafy.special.repository.ExceptionKeywordRepository;
import com.ssafy.special.repository.ProductQueryRepository;
import com.ssafy.special.repository.ProductRepository;
import com.ssafy.special.repository.RequireKeywordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DaangnCrawlingServiceImpl implements DaangnCrawlingService {

	private final ProductRepository productRepository; 
	private final ExceptionKeywordRepository exceptionKeywordRepository; 
	private final RequireKeywordRepository requireKeywordRepository; 
	private final ProductQueryRepository productQueryRepository; 
	
	
	
	// carrot1 + "검색어" + caroot2 + 페이지번호(1부터 시작)
	private static String carrot1 = "https://www.daangn.com/search/";
	private static String carrot2 = "/more/flea_market?page=";
	
	private static String carrotDetail = "https://www.daangn.com";
	
	@Scheduled(fixedRate = 10000)
	public void crawlingProducts() {
		
		//product 테이블에서 제품목록 가져옴
		List<Product> productList=productRepository.findAll();
		
		for(Product product: productList) {
			crawlingProduct(product);
		}		
	}
	


	public void crawlingProduct(Product product) {
		String market = "daangn";

		List<ProductDTO> productListAll= new ArrayList<ProductDTO>();
		List<ProductDTO> productList= null;
		
		//데이터 베이스에 productName을 검색해서 해당 제품을 찾기위한 검색 query, 제외키워드, 필수키워드들을 가져옴
		List<String> exceptionKeyword=new ArrayList<>();
		List<String> requireKeyword=new ArrayList<String>(); 
		String productQuery;
		
		//제외키워드나 필수키워드가 없는 경우 null이 넘어오는걸 대비해 optional을 리턴하도록 함
		//도메인List를 리턴하는걸 stream함수를 통해 string리스트로 바꾼뒤 변수에 넣어줌
		exceptionKeyword=exceptionKeywordRepository.findByProductIdAndMarket(product, market).orElse(new ArrayList<ExceptionKeyword>()).stream().map(ExceptionKeyword::getKeyword).collect(Collectors.toList());
		requireKeyword=requireKeywordRepository.findByProductIdAndMarket(product, market).orElse(new ArrayList<RequireKeyword>()).stream().map(RequireKeyword::getKeyword).collect(Collectors.toList());
		productQuery=productQueryRepository.findByProductIdAndMarket(product, market).orElse(new ProductQuery()).getQuery();

		int page=1;
		//페이지관리를 위한 반복문
		while(true) {	
			productList=null;
			//목록에서 물품 리스트 가져옴
			try {
				productList=listCrawling(productQuery, product.getName(), Integer.toString(page));
				page++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			
			//필터링을 통해 필요없는 품목들 제외시킴		
			//제외키워드
//			exceptionKeyword.add("미니");			
//			exceptionKeyword.add("교신");
//			exceptionKeyword.add("교환");		
//			exceptionKeyword.add("삽니다");
//			exceptionKeyword.add("매입");
//			
//			exceptionKeyword.add("업체");
//			exceptionKeyword.add("케이스");
//			exceptionKeyword.add("케에스");
//			
//			exceptionKeyword.add("커버");
//			exceptionKeyword.add("필름");
//			exceptionKeyword.add("강화유리");
//			
//			
//			//필수 키워드
//			phoneCategory.add("프로");
//			phoneCategory.add("pro");	
//			
//			phoneCategory2.add("맥스");
//			phoneCategory2.add("멕스");
//			phoneCategory2.add("max");
			
			
			keywordFilter(productList, exceptionKeyword, requireKeyword);
			
			//상세 페이지크롤링을 통해 날짜, 시간데이터 받아옴
			try {
				detailCrawling(productList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			for(ProductDTO productDTO: productList) {
				if(productDTO.getName()!=null)
					productListAll.add(productDTO);
			}
			if(page>1)
				break;
		}
		for(ProductDTO productDTO:productListAll) {
			System.out.println(productDTO);
		}
		System.out.println("갯수: "+productListAll.size());
	}
	
	// 목록 크롤링을 통해 검색어에 해당하는 게시글 id를 가져옴
	private List<ProductDTO> listCrawling(String query, String productName, String page) throws IOException {
		String url = carrot1 + query + carrot2 + page;
		Document doc = Jsoup.connect(url).get();

		Elements contents = doc.select("article");

		List<ProductDTO> productList = new ArrayList<ProductDTO>();
		

		for (Element content : contents) {
			// 걸러진 데이터들만 저장되게함			
			// 시간정보말고는 리스트에서 저장
			ProductDTO product = new ProductDTO();
			// 품명
			product.setName(productName);
			
			// 제목
			product.setTitle(content.select(".article-title").text());
			
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
			
			// 이미지 링크
			product.setImg(content.select(".card-photo > img").attr("src"));
			
			//지역
			product.setLocation(content.select(".article-region-name").text());
			
			productList.add(product);
			//시간정보는 상세 게시글에 나와있음			
		}
		return productList;
	}
	
	private List<ProductDTO> detailCrawling(List<ProductDTO> productList)throws IOException{
		
		// 게시글 상세보기 크롤링
		for (ProductDTO product : productList) {
			if(product.getName()==null)
				continue;
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
		}
		return productList;		
	}
	
	private List<ProductDTO> keywordFilter(List<ProductDTO> productList, List<String> exceptionKeyword, List<String> requireKeyword){
		for(ProductDTO product : productList) {
			//제외키워드
			for(String keyword : exceptionKeyword) {
				if(product.getTitle().contains(keyword)) {
					//제외 키워드를 포함하고 있으면 버려야함
					product.setName(null);//arraylist라서 remove는 연산시간 너무오래걸림
					product.setTitle(null);
					product.setPrice(0);
					product.setDate(null);
					product.setTime(null);
					product.setLink(null);
					product.setImg(null);
					product.setLocation(null);
					break;
				}
			}
			if(product.getName()==null)
				continue;
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
					if(product.getTitle().toLowerCase().contains(keyword)) {
						isContain=true;
						break;
					}else if(product.getContent().toLowerCase().contains(keyword)) {
						isContain=true;
						break;
					}
				}
				if(!isContain) {
					//하나도 포함안하면 버려야함
					product.setName(null);
					product.setTitle(null);
					product.setPrice(0);
					product.setDate(null);
					product.setTime(null);
					product.setLink(null);
					product.setImg(null);
					product.setLocation(null);				
					
					break;
				}
			}
		}
		return productList;
	}
}
