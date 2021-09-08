package com.ssafy.special.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Product {

	@Id
	@GeneratedValue
	private long id; //기본키
	
	private String name;//품명
	
	private long minPrice;//최저가
	private long avgPrice;//평균가
	private long maxPrice;//최고가
	
	//제외키워드
//	@OneToMany(mappedBy = "productId", cascade = CascadeType.ALL)
//	private List<ExceptionKeyword> exceptionKeywordList= new ArrayList<>();
//	
//	//포함키워드
//	@OneToMany(mappedBy = "productId", cascade = CascadeType.ALL)
//	private List<RequireKeyword> requireKeywordList= new ArrayList<>();
//
//	//검색 키워드
//	@OneToMany(mappedBy = "productId", cascade = CascadeType.ALL)
//	private List<ProductQuery> productQueryList= new ArrayList<>();
	
}
