package com.ssafy.special.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.special.domain.ExceptionKeyword;
import com.ssafy.special.domain.Product;
import com.ssafy.special.domain.ProductQuery;
import com.ssafy.special.domain.ProductSellList;
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
public class ThunderCrawlingServiceImpl implements ThunderCrawlingService {
	
	private final ProductRepository productRepository; 
	private final ExceptionKeywordRepository exceptionKeywordRepository; 
	private final RequireKeywordRepository requireKeywordRepository; 
	private final ProductQueryRepository productQueryRepository; 
	private final QueryExceptionKeywordRepository queryExceptionKeywordRepository; 
	private final ProductSellListRepository productSellListRepository;

	//https://api.bunjang.co.kr/api/1/find_v2.json?q=%EC%95%84%EC%9D%B4%ED%8F%B012&page=0
	
	// thunder1 + "검색어" + thunder2 + 페이지번호(1부터 시작)
	private static String thunder1 = "https://api.bunjang.co.kr/api/1/find_v2.json?q=";
	private static String thunder2 = "&page=";
	private static String thunderDetail = "https://api.bunjang.co.kr/api/1/product/";
	
	@Override
	public void crawlingProduct(ProductQuery productQuery, List<String> queryExceptionKeywordList) {
		final String market = "thunder";
		final String commonMarket = "common";
		
		List<ProductDTO> productList=new ArrayList<ProductDTO>();

		//productQuery로 크롤링을 진행하여 검색결과(productDTO)를 쿼리제외키워드로 필터링함
		int page=1;
		int endpage=200;
		boolean isOldDate=false;// 1달 넘어가는 데이터면 탈출시켜
		log.info("(번개)상품 목록을 크롤링 중입니다");
		while(true) {
			try {
				isOldDate=listCrawling(productList, productQuery, page, queryExceptionKeywordList);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(isOldDate)
				break;
			page++;
			if(page>endpage)
				break;
		}
//		System.out.println("productList : "+productList);
		
		List<Product> products=productRepository.findByQuery(productQuery).orElse(new ArrayList<Product>());
		Map<Long, List<String>> exceptionKeyword=new HashMap<Long, List<String>>();
		Map<Long, List<String>> requireKeyword=new HashMap<Long, List<String>>();

		for(Product product:products) {
			List<String> exception=new ArrayList<>();
			List<String> require=new ArrayList<String>(); 
			exception=exceptionKeywordRepository.findByProductIdAndMarket(product, commonMarket).orElse(new ArrayList<ExceptionKeyword>()).stream().map(ExceptionKeyword::getKeyword).collect(Collectors.toList());
			exception.addAll(exceptionKeywordRepository.findByProductIdAndMarket(product, market).orElse(new ArrayList<ExceptionKeyword>()).stream().map(ExceptionKeyword::getKeyword).collect(Collectors.toList()));

			exceptionKeyword.put(product.getId(), exception);
			
			require=requireKeywordRepository.findByProductIdAndMarket(product, commonMarket).orElse(new ArrayList<RequireKeyword>()).stream().map(RequireKeyword::getKeyword).collect(Collectors.toList());
			require.addAll(requireKeywordRepository.findByProductIdAndMarket(product, market).orElse(new ArrayList<RequireKeyword>()).stream().map(RequireKeyword::getKeyword).collect(Collectors.toList()));
		
			requireKeyword.put(product.getId(), require);
		}
		
		int i=0;
		for(ProductDTO p : productList) {
			if(i!=0 && i%100==0)
			i++;		
			for(Product product:products) {	
				if(hasExceptionKeyword(p.getTitle(), exceptionKeyword.get(product.getId()))) {
					continue;
				}
				
				//제목에서 필수 키워드를 가지고 있으면 품목 지정함
				if(hasRequireKeyword(p.getTitle(),requireKeyword.get(product.getId()))) {
					p.setName(product.getName());//품목 지정
				}else if(hasRequireKeyword(p.getContent(), requireKeyword.get(product.getId()))){//제목에서 필수 키워드가 없으면 내용에서 필수 키워드를 가지고 있는지 확인함
					p.setName(product.getName());//품목 지정
				}
				//name이 지정되었으면 ProductSellList테이블에 삽입
				if(p.getName()!=null) {		
					ProductSellList sellList=new ProductSellList();
					sellList.setId(Integer.parseInt(p.getSeq()));
					sellList.setMarket(market);
					sellList.setProductId(product);
					sellList.setTitle(p.getTitle());
					sellList.setContent(p.getContent().replaceAll("\n", " "));					
					sellList.setImg(p.getImg());
					sellList.setPrice(p.getPrice());
					sellList.setCreateDate(p.getDate());
					sellList.setLink(p.getLink());
					sellList.setLocation(p.getLocation());
					boolean result = insertProductSellList(sellList);
					if(result) {
						break;
					}else {
						log.info("(번개)"+ productQuery.getQuery()+" : 데이터 삽입에 실패 했습니다");
					}
				}
			}
		}
		log.info("(번개)"+ productQuery.getQuery()+" : 크롤링 및 분류가 완료되었습니다");
	}
	
	@Transactional(readOnly = true)
	// 목록 크롤링을 통해 검색어에 해당하는 게시글 id를 가져옴
	private boolean listCrawling(List<ProductDTO> productList, ProductQuery productQuery, int page, List<String> queryExceptionKeywordList) throws IOException {
		String url = thunder1 + productQuery.getQuery() + thunder2 + page;
		
//		https://api.bunjang.co.kr/api/1/find_v2.json?q=아이폰12&page=0
		String jsonInfo = Jsoup.connect(url).ignoreContentType(true).execute().body();
		
		try {
			JSONParser jsonParser = new JSONParser();
			
			//JSON데이터를 넣어 JSON Object로 만들어 준다.
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonInfo);
			
			//list의 배열을 추출
			JSONArray productInfoArray = (JSONArray) jsonObject.get("list");

			for(Object pObject : productInfoArray) {
				JSONObject productObject = (JSONObject) pObject;
				
				if(productObject.get("ad").equals(true))	//광고글
					continue;
				if(productObject.get("bizseller").equals(true))	//업자
					continue;
				
				ProductDTO product =  new ProductDTO();

				//제목
				product.setTitle((String)productObject.get("name"));
				
				//제목에 제외키워드가 들어가있으면 productList에 안넣음(케이스, 필름, 커버 이런것들)
				if(hasExceptionKeyword((String)productObject.get("name"), queryExceptionKeywordList)) {
					continue;
				}
				
				//제목에 제외키워드가 들어가있으면 productList에 안넣음
				if(hasExceptionKeyword((String)productObject.get("name"), queryExceptionKeywordList)) {
					continue;
				}
				
				//가격
				String price = (String)productObject.get("price");
				if(price.equals("0"))	//가격 0원
					continue;
				product.setPrice(Long.parseLong(price));
				
				//원본 게시글 pid
				product.setSeq((String)productObject.get("pid"));
				
				//이미지 링크
				product.setImg((String)productObject.get("product_image"));
				
				//지역
				product.setLocation((String)productObject.get("location"));
				
				String time = String.valueOf(productObject.get("update_time"));
				LocalDateTime dateTime = null;
				
				//시간 (1631171587) 이런 식 나중에 바꿔야됨
				String dateStr = getTimestampToDate((String)time);

				//시간 (1631171587) 이런 식 나중에 바꿔야됨
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				dateTime = LocalDateTime.parse(dateStr,formatter);
				//2019-07-17 13:07:19
				LocalDateTime preOneMonth=LocalDateTime.now().minusMonths(1);
				
				//한달 넘은 게시글인 경우 크롤링 중단
				if(preOneMonth.isAfter(dateTime)) {
					return true;
				}
				product.setDate(dateTime);
				
				
				//디테일 url
				//https://api.bunjang.co.kr/api/1/product/164042247/detail_info.json?version=4
				// pid = "164042247"
				
				//원본 게시글 링크 
				product.setLink("https://m.bunjang.co.kr/products/" + (String)productObject.get("pid"));
				//내용 뽑아내기
				String detailUrl = thunderDetail + (String)productObject.get("pid") + "/detail_info.json?version=4";
				String jsonDetailInfo = Jsoup.connect(detailUrl).ignoreContentType(true).execute().body();
				JSONObject jsonDetailObject = (JSONObject) jsonParser.parse(jsonDetailInfo);
				JSONObject item_info = (JSONObject) jsonDetailObject.get("item_info");
				
				//게시글 내용
				product.setContent((String) item_info.get("description_for_detail"));
				
				productList.add(product);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
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
	private static String getTimestampToDate(String timestampStr){
	    long timestamp = Long.parseLong(timestampStr);
	    Date date = new Date(timestamp*1000L); 
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    sdf.setTimeZone(TimeZone.getTimeZone("GMT+9")); 
	    String formattedDate = sdf.format(date);
	    return formattedDate;
	}
}
