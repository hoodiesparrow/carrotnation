package com.joongna.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
		Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
		// 년월일시분초 14자리 포멧
		SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(fourteen_format.format(date_now)); // 14자리 포멧으로 출력한다

		String jnURL = "https://search-api.joongna.com/v25/search/product";
		String payload = "{\n" + "  \"filter\": {\n" + "    \"categoryDepth\": 3,\n" + "    \"categorySeq\": 1151,\n"
				+ "    \"dateFilterParameter\": {\n" + "      \"sortEndDate\": \"2021-09-06\",\n"
				+ "      \"sortStartDate\": \"2021-08-07\"\n" + "    },\n" + "    \"productCondition\": -1,\n"
				+ "    \"flawedYn\": 0,\n" + "    \"fullPackageYn\": 0,\n" + "    \"limitedEditionYn\": 0,\n"
				+ "    \"maxPrice\": 2000000000,\n" + "    \"minPrice\": 0,\n" + "    \"tradeType\": 0\n" + "  },\n"
				+ "  \"page\": 0,\n" + "  \"firstQuantity\": 10,\n" + "  \"productFilter\": \"ALL\",\n"
				+ "  \"productStates\": [\n" + "    0,\n" + "    1\n" + "  ],\n" + "  \"quantity\": 10,\n"
				+ "  \"searchQuantity\": 10,\n" + "  \"osType\": 2,\n" + "  \"searchWord\": \"아이폰12\",\n"
				+ "  \"sort\": \"RECENT_SORT\",\n" + "  \"startIndex\": 0,\n" + "  \"searchStartTime\": \""
				+ fourteen_format.format(date_now) + "\"\n" + "}";

		post(jnURL, payload);
	}

	public static void post(String strUrl, String jsonMessage) {
		try {
			HashMap<String, String[]> inandexc = new HashMap<String, String[]>();
			String[] andlist = { "프로" };
//			String[] orlist = { "32","64", "128", "256", "512" };
			String[] gigalist = { "64", "128", "256", "512" };
			String[] except = { "매입", "교신", "업체" };
			inandexc.put("and", andlist);
//			inandexc.put("or", orlist);
			inandexc.put("except", except);
			inandexc.put("gigalist", gigalist);
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
			wr.write(jsonMessage); // json 형식의 message 전달
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
//				System.out.println(sb.toString());
				try {
					JsonNode node = mapper.readTree(sb.toString());
//		            System.out.println("OBJECT : "+node.get("data").get("items"));
					node.get("data").get("items").forEach(item -> {
						// true이면 앱 false이면 카페
						boolean storeapp = true;
						if ("0".equals(item.get("seq").toString())) {
							storeapp = false;
						}
						// 해당 타이틀이 조건에 모든 만족하면 입력 파일에 쓰기
					
						if (availablestuff(item.get("title").toString(), inandexc)) {
							// 파일 입출력으로 변경 예정
							if(storeapp) {
								System.out.println(item.get("seq"));
							}else {
								System.out.println(item.get("articleUrl").toString().replaceAll("\"", ""));
							}
							
							System.out.println("https://m.joongna.com/product-detail/" + item.get("seq"));
							System.out.println(item.get("title").toString().replaceAll("\"", ""));
							System.out.println(
									"https://img2.joongna.com" + item.get("url").toString().replaceAll("\"", ""));
							System.out.println(item.get("locationNames"));
							System.out.println(item.get("articleRegDate"));
							System.out.println(item.get("price"));
							System.out.println();
						} else {

							// 아니라면 내용 참조 이부분은 개인 플랫폼 서비스에 구현
							if (storeapp) {
								System.out.println(item.get("title").toString().replaceAll("\"", ""));
								if (joongnaapp(item.get("seq").toString(), inandexc)) {
									System.out.println("===============");
									System.out.println(item.get("seq"));
									System.out.println("https://m.joongna.com/product-detail/" + item.get("seq"));
									System.out.println(item.get("title").toString().replaceAll("\"", ""));
									System.out.println("https://img2.joongna.com"
											+ item.get("url").toString().replaceAll("\"", ""));
									System.out.println(item.get("locationNames"));
									System.out.println(item.get("articleRegDate"));
									System.out.println(item.get("price"));
									System.out.println("giga: "+giga);
									System.out.println("===============");
									System.out.println();
								} else {
									System.out.println("nonavailable stuff");
								}
							} else {
								
//								if (joongna("0", item.get("articleUrl").toString().replaceAll("\"", ""), inandexc)) {
//
//								} else {
//									System.out.println("nonavailable stuff");
//								}
							}

						}

					});
//		            System.out.println("OBJECT : "+jsonObject.toString());
				} catch (JsonProcessingException err) {
					System.out.println("Exception Json : " + err.toString());
				}

//				System.out.println("" + sb.toString());
			} else {
				System.out.println(con.getResponseMessage());
			}
		} catch (Exception e) {
			System.err.println("POST API: " + e.toString());
		}

	}
	static String giga;
	public static boolean availablestuff(String title, HashMap<String, String[]> inandexc) {
		HashMap<String, Boolean> check = new HashMap<String, Boolean>();
		
		String[] temp, except;
		boolean allcheck=true;
		if (inandexc.containsKey("except")) {
			check.put("except", true);
			except = inandexc.get("except");
			for (String s : except) {
				if (title.contains(s))
//					check.put("except", false);
					return false;
			}
		}

		if (inandexc.containsKey("and")) {
			temp = inandexc.get("and");
			check.put("and", true);
			for (String s : temp) {
				if (!title.contains(s)) {
//					check.put("and", false);
					return false;
				}
			}
		}

		if (inandexc.containsKey("or")) {
			check.put("or", false);
			temp = inandexc.get("or");
			for (String s : temp) {
				if (title.contains(s)) {
					check.put("or", true);
					break;
				}
			}
		}
		
		if (inandexc.containsKey("gigalist")) {
			check.put("gigalist", false);
			temp = inandexc.get("gigalist");
			for (String s : temp) {
				if (title.contains(s)) {
					giga=s;
					check.put("gigalist", true);
					break;
				}
			}
		}
		for(String key : check.keySet()){
            allcheck&=check.get(key);
        }


		if (allcheck) {
			return true;
		}

		return false;

	}

	public static boolean joongnacafe(String cafeurl, HashMap<String, String[]> inandexc) {
		// cafe url
		// https://apis.naver.com/cafe-web/cafe-articleapi/v2/cafes/10050146/articles/864779078
		// cafeurl 예시
//					"https://m.cafe.naver.com/ArticleRead.nhn?clubid=10050146&menuid=424&boardtype=C&page=6&articleid=864779078&referrerAllArticles=false",

		return true;
	}

	public static boolean joongnaapp(String seq, HashMap<String, String[]> inandexc) {
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
					String allcontent = node.get("data").get("productTitle").toString();
					allcontent += node.get("data").get("productDescription").toString();

					if (availablestuff(allcontent, inandexc)) {
						System.out.println("OBJECT : " + node.get("data").get("productDescription"));
						return true;
					}
				} catch (JsonProcessingException err) {
					System.out.println("Exception Json: " + err.toString());
				}

			} else {
				System.out.println(con.getResponseMessage());
			}

		} catch (Exception e) {
			System.err.println("incafe get" + e.toString());
		}

		return false;
	}

}
