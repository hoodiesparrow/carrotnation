package com.ssafy.special.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AdressToCoorUtils {
	String GEOCODE_URL = "http://dapi.kakao.com/v2/local/search/address.json?query=";
	String GEOCODE_USER_INFO="KakaoAK 37a231de28b658f70d03dc7470b67ef4";

	public Map<String, Double> AdressToCoorUtilstest(String add) {
		Map<String, Double> coord = new HashMap<String, Double>();
		try
		{
			String address = URLEncoder.encode(add, "UTF-8");
			URL obj = new URL(GEOCODE_URL + address);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", GEOCODE_USER_INFO);
			con.setRequestProperty("content-type", "application/json");
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setDefaultUseCaches(false);
			Charset charset = Charset.forName("UTF-8");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			} 
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node;
			JsonNode getxy;
			JsonNode doc;
			try {
				node = mapper.readTree(response.toString());
				doc = node.get("documents");
				if("[]".equals(doc.toString())) {
					return null;
				} 
				getxy =node.get("documents").get(0).get("address");
				coord.put("x", Double.parseDouble(getxy.get("x").toString().replace("\"", "")));
				coord.put("y", Double.parseDouble(getxy.get("y").toString().replace("\"", "")));
//				System.out.println(getxy.get("x"));
//				System.out.println(getxy.get("y"));
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return coord;
	}
	
}


