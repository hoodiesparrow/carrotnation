package com.ssafy.special.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.special.controller.AdressToCoorUtils;
import com.ssafy.special.domain.Coordinate;
import com.ssafy.special.domain.ExceptionKeyword;
import com.ssafy.special.domain.Product;
import com.ssafy.special.domain.ProductQuery;
import com.ssafy.special.domain.ProductSellList;
import com.ssafy.special.domain.RequireKeyword;
import com.ssafy.special.dto.ProductDTO;
import com.ssafy.special.exception.NotPageException;
import com.ssafy.special.exception.PageEndException;
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
public class JoongnaCrawlingServiceImpl implements JoongnaCrawlingService {

	private final ProductRepository productRepository;
	private final ExceptionKeywordRepository exceptionKeywordRepository;
	private final RequireKeywordRepository requireKeywordRepository;
	private final ProductSellListRepository productSellListRepository;
	private final AdressToCoorUtils adresstoCoorUrils;
	private final CoordinateRepository coordinateRepository;
	static String giga;
	static String strUrl = "https://search-api.joongna.com/v25/search/product";
//	SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	SimpleDateFormat yearmonthday_format = new SimpleDateFormat("yyyy-MM-dd");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	LocalDateTime date_now;
	LocalDateTime cal;
	String APPLE = "1151";
	String SAMSUNG = "1150";

//	@Scheduled(fixedDelay = 1000 * 60 * 30)
	@Override
	public void joongnainit(ProductQuery productQuery, List<String> exception) {
		int page = 0;
		date_now = LocalDateTime.now();
		cal = date_now.minusMonths(1);
		cal = date_now.minusDays(14);
		List<ProductDTO> pdlist = new ArrayList<ProductDTO>();
		log.info("(중고나라)" + productQuery.getQuery() + " 상품 목록을 크롤링 중입니다");
		while (true) {
			try {
				joongnaPostCrawling(productQuery.getQuery(), page++, exception, pdlist);
			} catch (PageEndException e) {
				break;
			}

		}
		listClassify(productQuery, pdlist);
		log.info("[end] (중고나라)" + productQuery.getQuery() + " 상품 목록을 크롤링 끝났습니다.");

	}

	public static HashMap<String, String> getQueryMap(String query) {
		if (query == null)
			return null;

		int pos1 = query.indexOf("?");
		if (pos1 >= 0) {
			query = query.substring(pos1 + 1);
		}

		String[] params = query.split("&");
		HashMap<String, String> map = new HashMap<String, String>();
		for (String param : params) {
			String name = param.split("=")[0];
			String value = param.split("=")[1];
			map.put(name, value);
		}
		return map;
	}

	public String urlconnection(String payload, String method, String linkUrl) throws NotPageException {
		String methodlower = method.toLowerCase();
		StringBuilder sb = new StringBuilder();
		OutputStreamWriter wr = null;
		OutputStream os=null;
		try {
			// 공통
			URL url = new URL(linkUrl);
			System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(7000); // 서버에 연결되는 Timeout 시간 설정
			con.setReadTimeout(8000); // InputStream 읽어 오는 Timeout 시간 설정
			
			if ("post".equals(methodlower)) {
				con.setRequestMethod("POST");
				// json으로 message를 전달하고자 할 때
				con.setRequestProperty("Content-Type", "application/json");
				con.setDoInput(true);
				con.setDoOutput(true); // POST 데이터를 OutputStream으로 넘겨 주겠다는 설정
				con.setUseCaches(false);
				con.setDefaultUseCaches(false);
				wr = new OutputStreamWriter(con.getOutputStream());
				wr.write(payload); // json 형식의 message 전달
				wr.flush();
				wr.close();
			} else if ("get".equals(methodlower)) {
				con.setRequestMethod("GET");
				con.setDoOutput(false);
			} else {
				return null;
			}
			try {
				if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
					// Stream을 처리해줘야 하는 귀찮음이 있음.
					BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
					String line;
					while ((line = br.readLine()) != null) {
						sb.append(line).append("\n");
					}
					br.close();
				} else {
//				System.out.println(con.getResponseMessage());
					throw new NotPageException(con.getResponseMessage());
				}
			} catch (SocketTimeoutException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	public void joongnaPostCrawling(String query, int page, List<String> exception, List<ProductDTO> list)
			throws PageEndException {
		// TODO Auto-generated method stub
		
		String categorySeq;
		if (query.contains("아이폰")) {
			categorySeq = APPLE;
		} else {
			categorySeq = SAMSUNG;
		}

		String payload = "{\r\n" + "    \"filter\": {\r\n" + "        \"categoryDepth\": 0,\r\n"
				+ "        \"categorySeq\": " + 0 + ",\r\n" + "        \"dateFilterParameter\": {\r\n"
				+ "            \"sortEndDate\": \"" + date_now.toLocalDate() + "\",\r\n"
				+ "            \"sortStartDate\": \"" + cal.toLocalDate() + "\"\r\n" + "        },\r\n"
				+ "        \"productCondition\": -1,\r\n" + "        \"flawedYn\": 0,\r\n"
				+ "        \"fullPackageYn\": 0,\r\n" + "        \"limitedEditionYn\": 0,\r\n"
				+ "        \"maxPrice\": 2000000000,\r\n" + "        \"minPrice\": 0,\r\n"
				+ "        \"tradeType\": 0\r\n" + "    },\r\n" + "    \"page\":" + page + ",\r\n"
				+ "    \"firstQuantity\": 10,\r\n" + "    \"productFilter\": \"ALL\",\r\n"
				+ "    \"productStates\": [\r\n" + "        0,\r\n" + "        1\r\n" + "    ],\r\n"
				+ "    \"quantity\": 10,\r\n" + "    \"searchQuantity\": 10,\r\n" + "    \"osType\": 2,\r\n"
				+ "    \"searchWord\": \"" + query + "\",\r\n" + "    \"sort\": \"RECENT_SORT\",\r\n"
				+ "    \"startIndex\": " + page + ",\r\n" + "    \"searchStartTime\": \"" + date_now.format(formatter)
				+ "\"\r\n" + "}";

		JSONParser jsonParser;
		JSONObject jsonObject, ss;
		String sb = urlconnection(payload, "post", strUrl);
		try {

			jsonParser = new JSONParser();
			jsonObject = (JSONObject) jsonParser.parse(sb.toString());
			ss = (JSONObject) jsonObject.get("data");
			JSONArray productInfoArray = (JSONArray) ss.get("items");
			if (productInfoArray.size() == 0) {
				throw new PageEndException("non page");
			}
			JSONArray location;
			for (Object pObject : productInfoArray) {
				JSONObject item = (JSONObject) pObject;

				if (stuffexception(item.get("title").toString(), exception)) {
					ProductDTO product = new ProductDTO();
					String seq = item.get("seq").toString();
					if ("0".equals(seq)) {
						product.setSeq("0");
						product.setLink(item.get("articleUrl").toString().replaceAll("\"", ""));
						try {
							product.setContent(joongnacafe(product.getLink()));
						
						}catch(NotPageException e){
							continue;
						}

					} else {
						product.setSeq(seq);
						product.setLink("https://m.joongna.com/product-detail/" + seq);
						try {
							product.setContent(joongnaapp(product.getSeq()));
						} catch (NotPageException e) {
							continue;
						}
					}
					product.setTitle(item.get("title").toString().replaceAll("\"", ""));
					product.setImg("https://img2.joongna.com" + item.get("url").toString().replaceAll("\"", ""));
					product.setPrice(Long.parseLong(item.get("price").toString()));

					LocalDateTime date = LocalDateTime.parse(item.get("articleRegDate").toString().replaceAll("\"", ""),
							formatter);
					product.setDate(date);
					location = (JSONArray) item.get("locationNames");

					if (location == null || location.size() == 0) {

					} else {
						product.setLocation((String) location.get(0));
					}
					list.add(product);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public boolean stuffexception(String title, List<String> except) {
		String lowertitle = title.toLowerCase();

		for (String s : except) {
			if (lowertitle.contains(s))
				return false;

		}
		return true;
	}

	public boolean stuffrequire(String title, List<String> require) {
		StringTokenizer st;
		boolean in;
		String lowertitle = title.toLowerCase();

		for (String s : require) {
			in = false;
			st = new StringTokenizer(s, ",");
			while (st.hasMoreTokens()) {
				String stt = st.nextToken();
				if (lowertitle.contains(stt)) {
					in = true;
					break;
				}
			}
			if (!in) {
				return false;
			}
		}
		return true;
	}

	public String joongnaapp(String seq) throws NotPageException {
		String basicurl = "https://edge-live.joongna.com/api/product/basic/" + seq;
		String sb = urlconnection("", "get", basicurl);
		if (sb == null || "".equals(sb))
			return null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(sb.toString());
			String content = node.get("data").get("productDescription").toString();

			return content;

		} catch (JsonProcessingException err) {
			System.out.println("Exception Json: " + err.toString());
		}

		return null;
	}

	public void listClassify(ProductQuery query, List<ProductDTO> list) {
		// TODO Auto-generated method stub
		List<Product> pdlist = productRepository.findByQuery(query).orElse(new ArrayList<Product>());
		Map<Long, List<String>> exceptionKeyword = new HashMap<Long, List<String>>();
		Map<Long, List<String>> requireKeyword = new HashMap<Long, List<String>>();

		for (Product p : pdlist) {
			List<String> commaexcept = exceptionKeywordRepository.findByProductIdAndMarket(p, "common")
					.orElse(new ArrayList<ExceptionKeyword>()).stream().map(ExceptionKeyword::getKeyword)
					.collect(Collectors.toList());

			List<String> commarequire = requireKeywordRepository.findByProductIdAndMarket(p, "common")
					.orElse(new ArrayList<RequireKeyword>()).stream().map(RequireKeyword::getKeyword)
					.collect(Collectors.toList());

			exceptionKeyword.put(p.getId(), commaexcept);
			requireKeyword.put(p.getId(), commarequire);

		}
		HashMap<String, String> map;
		List<ProductSellList> psllist =null;
		Coordinate address;
		ProductSellList pslcheck;
		for (ProductDTO pd : list) {

			for (Product p : pdlist) {
				List<String> commaexcept = exceptionKeyword.get(p.getId());

				List<String> commarequire = requireKeyword.get(p.getId());

//				HashMap<String, List<String>> inandexc = new HashMap<String, List<String>>();
//				inandexc.put("except", commaexcept);
//				inandexc.put("require", commarequire);
				if (!stuffexception(pd.getTitle(), commaexcept)) {
					continue;
				}
				if (stuffrequire(pd.getTitle(), commarequire)) {
					pd.setName(p.getName());
				} else {
					// 내용기반 확인
					String allContent = pd.getTitle() + pd.getContent();
					if (stuffrequire(allContent, commarequire)) {
						pd.setName(p.getName());
					}
				}

				if (pd.getName() != null && cal.isBefore(pd.getDate())) {
					ProductSellList sellList = new ProductSellList();
					if ("0".equals(pd.getSeq())) {
						map = getQueryMap(pd.getLink());
						sellList.setAid(Integer.parseInt(map.get("articleid")));
						sellList.setMarket("joonnaCafe");

					} else {
						sellList.setAid(Integer.parseInt(pd.getSeq()));
						sellList.setMarket("joonnaApp");
					}

					sellList.setProductId(p);
					sellList.setTitle(pd.getTitle());
					sellList.setContent(pd.getContent());
					sellList.setPrice(pd.getPrice());
					sellList.setCreateDate(pd.getDate());
					sellList.setLink(pd.getLink());
					sellList.setLocation(pd.getLocation());
					sellList.setImg(pd.getImg());
					
					psllist =productSellListRepository.getcoordinate(sellList.getAid(), sellList.getMarket()).orElse(new ArrayList<ProductSellList>());
					if(psllist.size()!=0) {
						pslcheck = psllist.get(0);
						sellList.setX(pslcheck.getX());
						sellList.setY(pslcheck.getY());
					}else {
						if(pd.getLocation()!=null&&!"".equals(pd.getLocation().trim())){
							address = coordinateRepository.findByaddress(pd.getLocation()).orElse(null);
							if(address!=null) {
								sellList.setX(Double.parseDouble(address.getLon()));
								sellList.setY(Double.parseDouble(address.getLat()));
							}else {
								Map<String,Double> coordnt = adresstoCoorUrils.AdressToCoorUtilstest(pd.getLocation());
								if(coordnt!=null) {
									sellList.setX(coordnt.get("x"));
									sellList.setY(coordnt.get("y"));
								}
							}
							
						}
					}
					
					boolean result = insertProductSellList(sellList);
					if (!result) {
						log.info("(중고나라)데이터 삽입에 실패 했습니다");
					}
					break;
				}

			}

		}
	}

	@Transactional
	private boolean insertProductSellList(ProductSellList sellList) {
		//이미 존재하는 데이터이면 안집어넣음
		if(productSellListRepository.findFirstByCycleAndAidAndMarketOrderByIdDesc(sellList.getCycle(), sellList.getAid(), sellList.getMarket())!=null)
			return true;
		try {
			productSellListRepository.save(sellList);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public String joongnacafe(String appurl) throws NotPageException {
		// TODO Auto-generated method stub
		HashMap<String, String> querymap = getQueryMap(appurl);

		// clubid=10050146&menuid=424&boardtype=C&page=14&articleid=865510328&referrerAllArticles=false
		String apiurl = "https://apis.naver.com/cafe-web/cafe-articleapi/v2/cafes/" + querymap.get("clubid")
				+ "/articles/" + querymap.get("articleid") + "?";
		querymap.remove("clubid");
		querymap.remove("articleid");
		// menuid=424&boardtype=C&page=14&referrerAllArticles=false&useCafeId=true
//		for(String key : querymap.keySet()) {
//			sb.append(key).append("=").append(querymap.get(key)).append("&");
//		}
//		sb.deleteCharAt(sb.length()-1);
		String t = urlconnection("", "get", apiurl);
		if (t == null || "".equals(t)) {
			return null;
		}
		StringBuilder sb = null;
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node;
		try {
			node = mapper.readTree(t.toString());

			try {
				Document doc = Jsoup.parse(node.get("result").get("article").get("contentHtml").toString());
				Elements spantag = doc.select("span");
				sb = new StringBuilder();
				for (Element e : spantag) {
					sb.append(e.text()).append(" ");
				}

			}catch (NullPointerException e) {
				System.out.println(t);
				System.out.println(node);
			}

		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return sb.toString();

	}

}
