package com.ssafy.special.domain;

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
public class QueryExceptionKeyword {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;// 기본키
	
	@ManyToOne
	@JoinColumn(name = "query", nullable = false)
	private ProductQuery query;//검색어
	
	private String keyword;//제외키워드
	
}
