package com.ssafy.special.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.special.domain.ProductQuery;
import com.ssafy.special.domain.ProductSellList;

public interface JoongnaCrawlingService {
	public void joongnaPostCrawling(String query, int page, List<String> exception);
	public boolean availablestuff(String title, HashMap<String, List<String>> inandexc);
	public String joongnaapp(String seq);
	public void joongnainit();
	public void listClassify(ProductQuery query);
}
