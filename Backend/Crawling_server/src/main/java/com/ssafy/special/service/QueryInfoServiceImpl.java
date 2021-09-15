package com.ssafy.special.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.special.domain.ProductQuery;
import com.ssafy.special.domain.QueryExceptionKeyword;
import com.ssafy.special.repository.ProductQueryRepository;
import com.ssafy.special.repository.ProductSellListRepository;
import com.ssafy.special.repository.QueryExceptionKeywordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class QueryInfoServiceImpl implements QueryInfoService {


	private final ProductQueryRepository productQueryRepository; 
	private final QueryExceptionKeywordRepository queryExceptionKeywordRepository; 
	private final ProductSellListRepository productSellListRepository; 
	
//	private final ProductRepository productRepository; 
//	private final ExceptionKeywordRepository exceptionKeywordRepository; 
//	private final RequireKeywordRepository requireKeywordRepository; 
	
	//검색어 리스트 리턴
	public List<ProductQuery> getProductQueryList(){
		return productQueryRepository.findAll();
	}
	
	//해당 검색어의 제외키워드 리턴
	public List<String> getQueryExceptionKeywordList(ProductQuery productQuery){
		return queryExceptionKeywordRepository.findByQuery(productQuery).orElse(new ArrayList<QueryExceptionKeyword>()).stream().map(QueryExceptionKeyword::getKeyword).collect(Collectors.toList());
	}
	
	//ProductSellList테이블 비우기
	public void truncateProductSellList() {
		productSellListRepository.truncateProductSellList();
	}
	
}
