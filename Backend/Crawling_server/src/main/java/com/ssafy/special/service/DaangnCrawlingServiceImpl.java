package com.ssafy.special.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.special.controller.AdressToCoorUtils;
import com.ssafy.special.domain.Coordinate;
import com.ssafy.special.domain.ExceptionKeyword;
import com.ssafy.special.domain.Product;
import com.ssafy.special.domain.ProductQuery;
import com.ssafy.special.domain.ProductSellList;
import com.ssafy.special.domain.RequireKeyword;
import com.ssafy.special.dto.ProductDTO;
import com.ssafy.special.repository.CoordinateRepository;
import com.ssafy.special.repository.ExceptionKeywordRepository;
import com.ssafy.special.repository.ProductRepository;
import com.ssafy.special.repository.ProductSellListRepository;
import com.ssafy.special.repository.RequireKeywordRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class DaangnCrawlingServiceImpl implements DaangnCrawlingService{

	private final ProductRepository productRepository; 
	private final ExceptionKeywordRepository exceptionKeywordRepository; 
	private final RequireKeywordRepository requireKeywordRepository; 
//	private final ProductQueryRepository productQueryRepository; 
//	private final QueryExceptionKeywordRepository queryExceptionKeywordRepository; 
	private final ProductSellListRepository productSellListRepository; 
	private final AdressToCoorUtils adresstoCoorUrils;
	private final CoordinateRepository coordinateRepository;
	// carrot1 + "검색어" + caroot2 + 페이지번호(1부터 시작)
	private static String carrot1 = "https://www.daangn.com/search/";
	private static String carrot2 = "/more/flea_market?page=";
	private static String carrotDetail = "https://www.daangn.com";
	
	// MainApplication에서 쓰래드 처리해 주면서 필요 없어짐 (테스트할때를 위해 당근마켓에서만 개별로 동작하는 코드 일단 남겨둠)
//	@Scheduled(fixedDelay = 1000 * 60 * 30)//30분
//	@Override
//	public void crawlingProducts() {
//		//productQuery테이블에서 query컬럼 리스트를 가져옴
//		List<ProductQuery> productQueryList = productQueryRepository.findAll();
//	
//		for(ProductQuery productQuery : productQueryList) {
//			//크롤링결과(productDTO)를 쿼리제외키워드로 필터링함(케이스, 필름 이런거 거름)
//			List<String> queryExceptionKeywordList = queryExceptionKeywordRepository.findByQuery(productQuery).orElse(new ArrayList<QueryExceptionKeyword>()).stream().map(QueryExceptionKeyword::getKeyword).collect(Collectors.toList());
//			crawlingProduct(productQuery, queryExceptionKeywordList);
//		}			
//	}
	
	@Override
	public void crawlingProduct(ProductQuery productQuery, List<String> queryExceptionKeywordList) {		
		
		List<ProductDTO> productList=new ArrayList<ProductDTO>();
		log.info("(당근)해당 검색어를 크롤링 중입니다 "+ productQuery.getQuery());
		
		//productQuery로 크롤링을 진행하여 검색결과(productDTO)를 쿼리제외키워드로 필터링함
		int page=1;
		int endpage=10;
		boolean isOldDate=false;// 1달 넘어가는 데이터면 탈출시켜
		while(true) {
			if(page!=0 && page%200==0 && page!=endpage) {
				log.info("(당근)"+ productQuery.getQuery()+" : " +page+"/"+endpage);
			}
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
			
//			try{
//			    Thread.sleep(10);//0.01초 쉬게함
//			}catch(InterruptedException e){
//			    e.printStackTrace();
//			}
		}
		List<Product> products= new ArrayList<Product>();
		Map<Long, List<String>> exceptionKeyword=new HashMap<Long, List<String>>();
		Map<Long, List<String>> requireKeyword=new HashMap<Long, List<String>>();

		getKeyword(productQuery, products, exceptionKeyword, requireKeyword);		
		
		log.info("(당근)"+ productQuery.getQuery()+" : 부적합한 물건을 제외하는 중입니다");
		
//		int i=0;
		List<ProductSellList> psllist;
		Coordinate address;
		ProductSellList pslcheck;
		for(ProductDTO p : productList) {
			isOldDate=false;// 1달 넘어가는 데이터면 탈출시켜
//			if(i!=0 && i%2000==0)
//				log.info("(당근)"+ productQuery.getQuery()+" : "+i+"번째 물건분류중입니다");
//			i++;			
			for(Product product:products) {	
				
				//제외 키워드가지고 있으면 패스
				if(hasExceptionKeyword(p.getTitle(), exceptionKeyword.get(product.getId()))) {
					continue;
				}
				
				//제목에서 필수 키워드를 가지고 있으면 품목 지정함
				if(hasRequireKeyword(p.getTitle(),requireKeyword.get(product.getId()))) {
					p.setName(product.getName());//품목 지정
					try {
						if(p.getDate()==null)
							isOldDate=detailCrawling(p);//게시글 날짜, 내용 지정
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					try {//게시글 날짜, 내용 가져옴
						isOldDate=detailCrawling(p);
					} catch (IOException e1) {
						e1.printStackTrace();
					}					
					if(hasRequireKeyword(p.getContent(), requireKeyword.get(product.getId()))){//제목에서 필수 키워드가 없으면 내용에서 필수 키워드를 가지고 있는지 확인함
						p.setName(product.getName());//품목 지정			
					}
				}
				if(isOldDate)
					break;
				
				
				//name이 지정되었으면 ProductSellList테이블에 삽입
				if(p.getName()!=null) {					
					ProductSellList sellList=new ProductSellList();
					sellList.setAid(Long.parseLong(p.getSeq()));
					sellList.setMarket("daangn");
					sellList.setProductId(product);
					sellList.setTitle(p.getTitle());
					sellList.setContent(p.getContent());					
					sellList.setPrice(p.getPrice());
					sellList.setCreateDate(p.getDate());
					sellList.setLink(p.getLink());
					sellList.setImg(p.getImg());
					sellList.setLocation(p.getLocation());
					
					
					psllist =productSellListRepository.getcoordinate(sellList.getAid(), sellList.getMarket()).orElse(new ArrayList<ProductSellList>());
					if(psllist.size()!=0) {
						pslcheck = psllist.get(0);
						sellList.setX(pslcheck.getX());
						sellList.setY(pslcheck.getY());
					}else {
						if(p.getLocation()!=null&&!"".equals(p.getLocation().trim())){
							address = coordinateRepository.findByaddress(p.getLocation()).orElse(null);
							if(address!=null) {
								sellList.setX(Double.parseDouble(address.getLon()));
								sellList.setY(Double.parseDouble(address.getLat()));
							}else {
								Map<String,Double> coordnt = adresstoCoorUrils.AdressToCoorUtilstest(p.getLocation());
								if(coordnt!=null) {
									sellList.setX(coordnt.get("x"));
									sellList.setY(coordnt.get("y"));
								}
							}
							
						}
					}
					
					
					if(sellList.getCreateDate()==null)
						continue;
					boolean result = insertProductSellList(sellList);
					if(result) {
						break;
					}else {
						log.info("(당근)"+ productQuery.getQuery()+" : 데이터 삽입에 실패 했습니다");
					}
				}
			}
			if(isOldDate)
				break;
			
		}
		log.info("(당근)"+ productQuery.getQuery()+" : 크롤링 및 분류가 완료되었습니다");
	}
	
	
	
	// 목록 크롤링을 통해 검색어에 해당하는 게시글 id를 가져옴
	private boolean listCrawling(List<ProductDTO> productList, ProductQuery productQuery, int page, List<String> queryExceptionKeywordList) throws IOException {
		String url = carrot1 + productQuery.getQuery() + carrot2 + page;
		Document doc=null;
		Elements contents=null;
		try {
			doc = Jsoup.connect(url).get();
			contents = doc.select("article");
		}catch (Exception e) {
			log.info("해당 URL을 크롤링하던중 에러가 발생하였습니다 "+ url);
			return false;
		}
		//s21처럼 검색결과가 아얘 없는경우
		if(contents.size()==0) {
			return true;
		}
	
		
		for (Element content : contents) {
			// 걸러진 데이터들만 저장되게함			
			// 시간정보말고는 리스트에서 저장
			ProductDTO product = new ProductDTO();
			
			// 제목
			String title=content.select(".article-title").text();
			product.setTitle(title);
			
			//제목에 제외키워드가 들어가있으면 productList에 안넣음(케이스, 필름, 커버 이런것들)
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
//			product.setContent(content.select(".article-content").text());//여기선 개행 제대로 못받음		
			// 원본 게시글 링크
			product.setLink(carrotDetail+content.select("a").attr("href"));			
			// 원본 게시글 pid
			product.setSeq(content.select("a").attr("href").split("/")[2]);			
			// 이미지 링크
			product.setImg(content.select(".card-photo > img").attr("src"));			
			//지역
			product.setLocation(content.select(".article-region-name").text());
			
			
			//시간정보는 상세 게시글에 나와있음	
			//50페이지마다 디테일 크롤링해서 1달 넘어가면 크롤링 멈추게 함
			
			boolean isOldDate=false;// 1달 넘어가는 데이터면 탈출시켜
			
			if(page!=0 && page%50==0)
				isOldDate=detailCrawling(product);
			
			if(isOldDate)
				return true;
			
			productList.add(product);
		}
		return false;
	}
	
	private boolean detailCrawling(ProductDTO product)throws IOException{		
		// 게시글 상세보기 크롤링
		Document doc;
		Element content;
		String time;
		String articleDetail;
		
		try {
			doc = Jsoup.connect(product.getLink()).get();
			content = doc.selectFirst("#content > #article-description");
			time=content.select("#article-category time").text();
			articleDetail = content.select("#article-detail").html();
		}catch (Exception e) {
			log.info("해당 URL을 상세 크롤링하던중 에러가 발생하였습니다 "+ product.getLink());
			return false;
		}

		// 날짜, 몇시간 전인지
		String[] tmp= time.split(" ");
		time= tmp[tmp.length-2];
		
		LocalDateTime dateTime;
		
		if(time.contains("년")) {
			dateTime=LocalDateTime.now().minusYears(Long.parseLong(time.replace("년", "")));
			return true;
		}else if(time.contains("달")) {
			dateTime=LocalDateTime.now().minusMonths(Long.parseLong(time.replace("달", "")));
			return true;
		}else if(time.contains("일")) {
			dateTime=LocalDateTime.now().minusDays(Long.parseLong(time.replace("일", "")));
		}else if(time.contains("시간")) {
			dateTime=LocalDateTime.now().minusHours(Long.parseLong(time.replace("시간", "")));
		}else if(time.contains("분")) {
			dateTime=LocalDateTime.now().minusMinutes(Long.parseLong(time.replace("분", "")));
		}else {
			try {
				dateTime=LocalDateTime.now().minusSeconds(Long.parseLong(time.replace("초", "")));
			} catch (Exception e) {
				e.printStackTrace();
				dateTime=null;
			}			
		}
		
		product.setTime(time.replace("끌올", ""));
		product.setDate(dateTime);
		articleDetail = articleDetail.replaceAll("<br>|</br>|</p>", "\n");
		articleDetail = articleDetail.replaceAll("<p>", "");
		
		product.setContent(articleDetail);
		
		return false;	
	}
	
	//제외키워드 포함하고 있는지 확인
	private boolean hasExceptionKeyword(String str, List<String> exceptionKeyword) {
		for(String keyword : exceptionKeyword) {
			if(str.toLowerCase().contains(keyword.toLowerCase())) {
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
				if(str.toLowerCase().contains(keyword.toLowerCase())) {
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
		//이미 존재하는 데이터이면 안집어넣음
		if(productSellListRepository.countByCycleAndAidAndMarket(sellList.getCycle(), sellList.getAid(), sellList.getMarket())>0)
			return true;
		try {
			productSellListRepository.save(sellList);
		}catch (Exception e ) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Transactional(readOnly = true)
	private void getKeyword(ProductQuery productQuery, List<Product> products, Map<Long, List<String>> exceptionKeyword, Map<Long, List<String>> requireKeyword) {
		final String market = "daangn";
		final String commonMarket = "common";
		
        products.addAll(productRepository.findByQuery(productQuery).orElse(new ArrayList<Product>()));
        
		for(Product product:products) {
			List<String> exception=new ArrayList<>();
			List<String> require=new ArrayList<String>(); 
			//데이터 베이스에 productName을 검색해서 해당 제품을 찾기위한 검색 query, 제외키워드, 필수키워드들을 가져옴			
			exception=exceptionKeywordRepository.findByProductIdAndMarket(product, commonMarket).orElse(new ArrayList<ExceptionKeyword>()).stream().map(ExceptionKeyword::getKeyword).collect(Collectors.toList());
			exception.addAll(exceptionKeywordRepository.findByProductIdAndMarket(product, market).orElse(new ArrayList<ExceptionKeyword>()).stream().map(ExceptionKeyword::getKeyword).collect(Collectors.toList()));
			
			exceptionKeyword.put(product.getId(), exception);
			
			//공통 필수 키워드
			require=requireKeywordRepository.findByProductIdAndMarket(product, commonMarket).orElse(new ArrayList<RequireKeyword>()).stream().map(RequireKeyword::getKeyword).collect(Collectors.toList());
			//당근마켓 필수 키워드
			require.addAll(requireKeywordRepository.findByProductIdAndMarket(product, market).orElse(new ArrayList<RequireKeyword>()).stream().map(RequireKeyword::getKeyword).collect(Collectors.toList()));
		
			requireKeyword.put(product.getId(), require);
		}
	}
}
