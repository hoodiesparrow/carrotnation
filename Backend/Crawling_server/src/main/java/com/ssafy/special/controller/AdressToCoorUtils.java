package com.ssafy.special.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Component
public class AdressToCoorUtils {
	String GEOCODE_URL = "http://dapi.kakao.com/v2/local/search/address.json?query=";
	String GEOCODE_USER_INFO="KakaoAK 4b066a70f29a2124557b960d7c360b80";

	public void AdressToCoorUtilstest(String add) {
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
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(response.toString());
			System.out.println(jsonObject);
			System.out.println(jsonObject.get("documents"));
			Object ob=jsonObject.get("documents");
			System.out.println();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}


