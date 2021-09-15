package com.ssafy.special.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; //기본키
	
	@ManyToOne
	@JoinColumn(name = "query", nullable = false)
	private ProductQuery query;//검색어
	
	@Column(nullable = false)
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
