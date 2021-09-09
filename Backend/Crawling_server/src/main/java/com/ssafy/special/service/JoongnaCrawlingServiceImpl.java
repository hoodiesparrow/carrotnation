package com.ssafy.special.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.special.domain.ExceptionKeyword;
import com.ssafy.special.domain.Product;
import com.ssafy.special.domain.ProductQuery;
import com.ssafy.special.domain.QueryExceptionKeyword;
import com.ssafy.special.domain.RequireKeyword;
import com.ssafy.special.dto.ProductDTO;
import com.ssafy.special.exception.PageEndException;
import com.ssafy.special.repository.ExceptionKeywordRepository;
import com.ssafy.special.repository.ProductQueryRepository;
import com.ssafy.special.repository.ProductRepository;
import com.ssafy.special.repository.QueryExceptionKeywordRepository;
import com.ssafy.special.repository.RequireKeywordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoongnaCrawlingServiceImpl implements JoongnaCrawlingService {

	private final QueryExceptionKeywordRepository queryExceptionKeywordRepository;
	private final ProductQueryRepository productQueryRepository;
	private final ProductRepository productRepository;
	private final ExceptionKeywordRepository exceptionKeywordRepository;
	private final RequireKeywordRepository requireKeywordRepository;
	
	
	static String giga;
	static String strUrl = "https://search-api.joongna.com/v25/search/product";
	SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat yearmonthday_format = new SimpleDateFormat("yyyy-MM-dd");
	Date date_now;
	Calendar cal;
	static List<ProductDTO> list = new ArrayList<ProductDTO>();

	@Override
	@Scheduled(fixedRate = 1000000)
	public void joongnainit() {
		int idx = 0;
		date_now = new Date(System.currentTimeMillis());
		cal = Calendar.getInstance();
		cal.setTime(date_now);
		cal.add(Calendar.MONTH, -1);
//		List<ProductQuery> querylist = productQueryRepository.findAll();
		List<String> queryException;
		for (ProductQuery p : productQueryRepository.findAll()) {
			while (true) {
				try {
					queryException = queryExceptionKeywordRepository.findByQuery(p)
							.orElse(new ArrayList<QueryExceptionKeyword>()).stream()
							.map(QueryExceptionKeyword::getKeyword).collect(Collectors.toList());
					joongnaPostCrawling(p.getQuery(), idx++, queryException);

					if (idx == 5)
						break;
				} catch (PageEndException e) {
					break;
				}

			}
			listClassify(p);
		}
		for (ProductDTO pd : list) {
			if(pd.getName()!=null)
				System.out.println(pd.toString());
		}

	}

	@Override
	public void joongnaPostCrawling(String query, int page, List<String> exception) throws PageEndException {
		// TODO Auto-generated method stub
		HashMap<String, List<String>> inandexc = new HashMap<String, List<String>>();
		inandexc.put("except", exception);

//		System.out.println("===============================");
//		System.out.println(fourteen_format.format(date_now)); // 14자리 포멧으로 출력한다
//		System.out.println(yearmonthday_format.format(cal.getTime()));

		String payload = "{\r\n" + "    \"filter\": {\r\n" + "        \"categoryDepth\": 3,\r\n"
				+ "        \"categorySeq\": 1151,\r\n" + "        \"dateFilterParameter\": {\r\n"
				+ "            \"sortEndDate\": \"" + yearmonthday_format.format(date_now) + "\",\r\n"
				+ "            \"sortStartDate\": \"" + yearmonthday_format.format(cal.getTime()) + "\"\r\n"
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
				System.out.println("==========" + page + "============");
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
						if (availablestuff(item.get("title").toString(), inandexc)) {
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

	@Override
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
				st = new StringTokenizer(s, ",");
				while (st.hasMoreTokens()) {
					if (lowertitle.contains(st.nextToken()))
						return false;
				}
				
			}
		}
		//미니, mini & 128
		if (inandexc.containsKey("require")) {
			require = inandexc.get("require");
			check.put("require", true);
			int idx=1;
			boolean in;
			
			for (String s : require) {
				in = false;
				st = new StringTokenizer(s, ",");
				while (st.hasMoreTokens()) {
					if (lowertitle.contains(st.nextToken())) {
						in=true;
						break;
					}
				}
				
				if(in) {
					check.put("require"+idx, true);
					idx++;
				}else {
					check.put("require"+idx, false);
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

	@Override
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

	@Override
	public void listClassify(ProductQuery query) {
		// TODO Auto-generated method stub
		for(ProductDTO pd :list) {
			List<Product> pdlist = productRepository.findByQuery(query).orElse(new ArrayList<Product>());
			for(Product p:pdlist) {
				List<String> commaexcept = exceptionKeywordRepository.findByProductIdAndMarket(p, "")
						.orElse(new ArrayList<ExceptionKeyword>()).stream()
						.map(ExceptionKeyword::getKeyword).collect(Collectors.toList());
				
				List<String> commarequire = requireKeywordRepository.findByProductIdAndMarket(p,"")
						.orElse(new ArrayList<RequireKeyword>()).stream()
						.map(RequireKeyword::getKeyword).collect(Collectors.toList());
				
				HashMap<String, List<String>> inandexc = new HashMap<String, List<String>>();
				inandexc.put("except", commaexcept);
				inandexc.put("require", commarequire);
				
				if(availablestuff(pd.getTitle(), inandexc)) {
					pd.setName(p.getName());
				}else {
					//내용기반 확인
					if("0".equals(pd.getSeq())) {
						
					}else {
						if(pd.getContent()==null) {
							pd.setContent(joongnaapp(pd.getSeq()));
						}
						
						String allContent = pd.getTitle()+pd.getContent();
						if(availablestuff(allContent, inandexc)) {
							pd.setName(p.getName());
						}
					}
				}
				
			}
		}
	}

}
