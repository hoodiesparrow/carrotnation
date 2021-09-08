package com.ssafy.special.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProductQuery {
	@Id
	@GeneratedValue
	private long id; //기본키
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Product productId;//품명
	
	private String query;//검색어
	
	private String market;//마켓종류
}
