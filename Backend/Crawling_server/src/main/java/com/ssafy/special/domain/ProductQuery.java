package com.ssafy.special.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ProductQuery {
	@Id
	@GeneratedValue
	private long id; //기본키
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	private Product productId;//품명
	
	private String query;//검색어
	
	private String market;//마켓종류
}
