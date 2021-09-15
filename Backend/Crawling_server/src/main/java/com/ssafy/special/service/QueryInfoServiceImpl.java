package com.ssafy.special.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.special.domain.ProductQuery;
import com.ssafy.special.domain.QueryExceptionKeyword;
import com.ssafy.special.repository.ExceptionKeywordRepository;
import com.ssafy.special.repository.ProductQueryRepository;
import com.ssafy.special.repository.ProductRepository;
import com.ssafy.special.repository.ProductSellListRepository;
import com.ssafy.special.repository.QueryExceptionKeywordRepository;
import com.ssafy.special.repository.RequireKeywordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QueryInfoServiceImpl implements QueryInfoService {

	private final ProductRepository productRepository; 
	private final ExceptionKeywordRepository exceptionKeywordRepository; 
	private final RequireKeywordRepository requireKeywordRepository; 
	private final ProductQueryRepository productQueryRepository; 
	private final QueryExceptionKeywordRepository queryExceptionKeywordRepository; 
	private final ProductSellListRepository productSellListRepository; 
	
	//검색어 리스트 리턴
	public List<ProductQuery> getProductQueryList(){
		return productQueryRepository.findAll();
	}
	
	//해당 검색어의 제외키워드 리턴
	public List<String> getQueryExceptionKeywordList(ProductQuery productQuery){
		return queryExceptionKeywordRepository.findByQuery(productQuery).orElse(new ArrayList<QueryExceptionKeyword>()).stream().map(QueryExceptionKeyword::getKeyword).collect(Collectors.toList());
	}
	
}
