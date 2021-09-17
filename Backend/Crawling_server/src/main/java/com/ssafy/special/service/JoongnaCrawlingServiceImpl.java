package com.ssafy.special.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.special.domain.ExceptionKeyword;
import com.ssafy.special.domain.Product;
import com.ssafy.special.domain.ProductQuery;
import com.ssafy.special.domain.ProductSellList;
import com.ssafy.special.domain.RequireKeyword;
import com.ssafy.special.dto.ProductDTO;
import com.ssafy.special.exception.PageEndException;
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

	static String giga;
	static String strUrl = "https://search-api.joongna.com/v25/search/product";
	SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat yearmonthday_format = new SimpleDateFormat("yyyy-MM-dd");
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
		List<ProductDTO> pdlist = new ArrayList<ProductDTO>();
		log.info("(중고나라)"+ productQuery.getQuery()+" 상품 목록을 크롤링 중입니다");
		while (true) {
			try {
				joongnaPostCrawling(productQuery.getQuery(), page++, exception, pdlist);
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO: handle exception
//				}
//				if (page == 5)
//					break;
			} catch (PageEndException e) {
				break;
			}

		}
		listClassify(productQuery, pdlist);
		log.info("[end] (중고나라)"+ productQuery.getQuery()+" 상품 목록을 크롤링 끝났습니다.");

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

	public void joongnaPostCrawling(String query, int page, List<String> exception, List<ProductDTO> list)
			throws PageEndException {
		// TODO Auto-generated method stub

		String categorySeq;
		if(query.contains("아이폰")) {
			categorySeq=APPLE;
		}else {
			categorySeq=SAMSUNG;
		}
		
		String payload = "{\r\n" + "    \"filter\": {\r\n" + "        \"categoryDepth\": 3,\r\n"
				+ "        \"categorySeq\": "+categorySeq+",\r\n" + "        \"dateFilterParameter\": {\r\n"
				+ "            \"sortEndDate\": \"" + yearmonthday_format.format(date_now) + "\",\r\n"
				+ "            \"sortStartDate\": \"" + yearmonthday_format.format(cal) + "\"\r\n"
				+ "        },\r\n" + "        \"productCondition\": -1,\r\n" + "        \"flawedYn\": 0,\r\n"
				+ "        \"fullPackageYn\": 0,\r\n" + "        \"limitedEditionYn\": 0,\r\n"
				+ "        \"maxPrice\": 2000000000,\r\n" + "        \"minPrice\": 0,\r\n"
				+ "        \"tradeType\": 0\r\n" + "    },\r\n" + "    \"page\":" + page + ",\r\n"
				+ "    \"firstQuantity\": 10,\r\n" + "    \"productFilter\": \"ALL\",\r\n"
				+ "    \"productStates\": [\r\n" + "        0,\r\n" + "        1\r\n" + "    ],\r\n"
				+ "    \"quantity\": 10,\r\n" + "    \"searchQuantity\": 10,\r\n" + "    \"osType\": 2,\r\n"
				+ "    \"searchWord\": \"" + query + "\",\r\n" + "    \"sort\": \"RECENT_SORT\",\r\n"
				+ "    \"startIndex\": " + page + ",\r\n" + "    \"searchStartTime\": \""
				+ fourteen_format.format(date_now) + "\"\r\n" + "}";
		try {
			URL url = new URL(strUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(5000); // 서버에 연결되는 Timeout 시간 설정
			con.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
			con.setRequestMethod("POST");

			// json으로 message를 전달하고자 할 때
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoInput(true);
			con.setDoOutput(true); // POST 데이터를 OutputStream으로 넘겨 주겠다는 설정
			con.setUseCaches(false);
			con.setDefaultUseCaches(false);

			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			wr.write(payload); // json 형식의 message 전달
			wr.flush();

			StringBuilder sb = new StringBuilder();
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// Stream을 처리해줘야 하는 귀찮음이 있음.
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				br.close();
				ObjectMapper mapper = new ObjectMapper();

				try {
					JsonNode node = mapper.readTree(sb.toString());

					if ("[]".equals(node.get("data").get("items").toString())) {
						throw new PageEndException("non page");
					}
					node.get("data").get("items").forEach(item -> {
						// 해당 제목으로 1차 선별하여 리스트화
						if (stuffexception(item.get("title").toString(), exception)) {
							ProductDTO product = new ProductDTO();
							String seq = item.get("seq").toString();
							if ("0".equals(seq)) {
								product.setSeq("0");
								product.setLink(item.get("articleUrl").toString().replaceAll("\"", ""));
							} else {
								product.setSeq(seq);
								product.setLink("https://m.joongna.com/product-detail/" + seq);
							}
							product.setTitle(item.get("title").toString().replaceAll("\"", ""));
							product.setImg(
									"https://img2.joongna.com" + item.get("url").toString().replaceAll("\"", ""));
							product.setPrice(Long.parseLong(item.get("price").toString()));

							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
							LocalDateTime date = LocalDateTime
									.parse(item.get("articleRegDate").toString().replaceAll("\"", ""), formatter);
							product.setDate(date);

							String locationParse = item.get("locationNames").toString().replaceAll("[\"\\[\\]]", "");
							if ("null".equals(locationParse) || locationParse.trim().isEmpty()) {

							} else {
								StringTokenizer st2 = new StringTokenizer(locationParse, ",");
								product.setLocation(st2.nextToken());
							}
							list.add(product);
						}

					});
				} catch (JsonProcessingException err) {
					System.out.println("Exception Json : " + err.toString());

				}
			} else {
				System.out.println(con.getResponseMessage());
			}
		} catch (Exception e) {
			System.err.println("POST API: " + e.toString());
			if (e.toString().equals("com.ssafy.special.exception.PageEndException: non page")) {
				throw new PageEndException("non page");
			}

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

	public boolean availablestuff(String title, HashMap<String, List<String>> inandexc) {
		HashMap<String, Boolean> check = new HashMap<String, Boolean>();

		List<String> require, except;
		StringTokenizer st;
		boolean allcheck = true;
		String lowertitle = title.toLowerCase();

		if (inandexc.containsKey("except")) {
			check.put("except", true);
			except = inandexc.get("except");
			for (String s : except) {
				if (lowertitle.contains(s))
					return false;

			}
		}
		// 미니, mini & 128
		if (inandexc.containsKey("require")) {
			require = inandexc.get("require");
			int idx = 1;
			boolean in;

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

				if (in) {
					check.put("require" + idx, true);
					idx++;
				} else {
					check.put("require" + idx, false);
					idx++;
				}

			}
		}

		for (String key : check.keySet()) {
			allcheck &= check.get(key);
		}

		if (allcheck) {
			return true;
		}
		return false;
	}

	public String joongnaapp(String seq) {
		String basicurl = "https://edge-live.joongna.com/api/product/basic/" + seq;

		try {
			URL url = new URL(basicurl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(5000); // 서버에 연결되는 Timeout 시간 설정
			con.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
			con.setRequestMethod("GET");

//				URLConnection에 대한 doOutput 필드값을 지정된 값으로 설정한다. 
//				URL 연결은 입출력에 사용될 수 있다. URL 연결을 출력용으로 사용하려는 경우 DoOutput 플래그를 true로 설정하고, 
//				그렇지 않은 경우는 false로 설정해야 한다. 기본값은 false이다.
			con.setDoOutput(false);

			StringBuilder sb = new StringBuilder();
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// Stream을 처리해줘야 하는 귀찮음이 있음.
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				br.close();
				ObjectMapper mapper = new ObjectMapper();
				try {
					JsonNode node = mapper.readTree(sb.toString());

					// 제목과 본문내용 기준 있는지 없는지 확인
					return node.get("data").get("productDescription").toString();

				} catch (JsonProcessingException err) {
					System.out.println("Exception Json: " + err.toString());
				}

			} else {
				System.out.println(con.getResponseMessage());
			}

		} catch (Exception e) {
			System.err.println("incafe get" + e.toString());
		}
		return null;
	}

	public void listClassify(ProductQuery query, List<ProductDTO> list) {
		// TODO Auto-generated method stub
		List<Product> pdlist = productRepository.findByQuery(query).orElse(new ArrayList<Product>());
		Map<Long, List<String>> exceptionKeyword=new HashMap<Long, List<String>>();
		Map<Long, List<String>> requireKeyword=new HashMap<Long, List<String>>();
		
		
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
		for (ProductDTO pd : list) {
			
			for (Product p : pdlist) {
				List<String> commaexcept = exceptionKeyword.get(p.getId());

				List<String> commarequire = requireKeyword.get(p.getId());

				HashMap<String, List<String>> inandexc = new HashMap<String, List<String>>();
				inandexc.put("except", commaexcept);
				inandexc.put("require", commarequire);
				if (!stuffexception(pd.getTitle(), commaexcept)) {
					continue;
				}
				if (stuffrequire(pd.getTitle(), commarequire)) {
					pd.setName(p.getName());
				} else {
					// 내용기반 확인
					if ("0".equals(pd.getSeq())) {
						if (pd.getContent() == null) {
							pd.setContent(joongnacafe(pd.getLink()));
						}
						String allContent = pd.getTitle() + pd.getContent();
						if (stuffrequire(allContent, commarequire)) {
							pd.setName(p.getName());
						}
					} else {
						if (pd.getContent() == null) {
							pd.setContent(joongnaapp(pd.getSeq()));
						}

						String allContent = pd.getTitle() + pd.getContent();
						if (stuffrequire(allContent, commarequire)) {
							pd.setName(p.getName());
						}
					}
				}
				
				if (pd.getName() != null && !cal.isBefore(pd.getDate())) {
					ProductSellList sellList = new ProductSellList();
					if ("0".equals(pd.getSeq())) {

						try {
							URL url = new URL(pd.getLink());
							HashMap<String, String> map = getQueryMap(url.getQuery());
							sellList.setId(Integer.parseInt(map.get("articleid")));
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						sellList.setMarket("joonnaCafe");
						if (pd.getContent() == null) {
							pd.setContent(joongnacafe(pd.getLink()));
						}
					} else {
						sellList.setId(Integer.parseInt(pd.getSeq()));
						sellList.setMarket("joonnaApp");
						if (pd.getContent() == null) {
							pd.setContent(joongnaapp(pd.getSeq()));
						}
					}
					
					sellList.setProductId(p);
					sellList.setTitle(pd.getTitle());
					sellList.setContent(pd.getContent());
					sellList.setPrice(pd.getPrice());
					sellList.setCreateDate(pd.getDate());
					sellList.setLink(pd.getLink());
					sellList.setLocation(pd.getLocation());
					sellList.setImg(pd.getImg());
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
		try {
			productSellListRepository.save(sellList);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public String joongnacafe(String appurl) {
		// TODO Auto-generated method stub
		HashMap<String, String> querymap = getQueryMap(appurl);

		// clubid=10050146&menuid=424&boardtype=C&page=14&articleid=865510328&referrerAllArticles=false
		String apiurl = "https://apis.naver.com/cafe-web/cafe-articleapi/v2/cafes/" + querymap.get("clubid")
				+ "/articles/" + querymap.get("articleid") + "?";
		querymap.remove("clubid");
		querymap.remove("articleid");
		// menuid=424&boardtype=C&page=14&referrerAllArticles=false&useCafeId=true
		StringBuilder sb;
//		for(String key : querymap.keySet()) {
//			sb.append(key).append("=").append(querymap.get(key)).append("&");
//		}
//		sb.deleteCharAt(sb.length()-1);

		URL url;
		HttpURLConnection con;
		try {
			url = new URL(apiurl);
			con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(5000); // 서버에 연결되는 Timeout 시간 설정
			con.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
			con.setRequestMethod("GET");
			con.setDoOutput(false);
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// Stream을 처리해줘야 하는 귀찮음이 있음.
				sb = new StringBuilder();
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				br.close();
				ObjectMapper mapper = new ObjectMapper();
				JsonNode node = mapper.readTree(sb.toString());

				Document doc = Jsoup.parse(node.get("result").get("article").get("contentHtml").toString());
				Elements spantag = doc.select("span");
				sb = new StringBuilder();
				for (Element e : spantag) {
					sb.append(e.text()).append(" ");
				}

				return sb.toString();

			} else {
				System.out.println(con.getResponseMessage());
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

}
