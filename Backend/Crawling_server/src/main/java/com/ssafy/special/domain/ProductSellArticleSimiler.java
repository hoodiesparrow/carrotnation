package com.ssafy.special.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@IdClass(ProductSellArticleSimilerPK.class)
public class ProductSellArticleSimiler {
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="article")
	private ProductSellList articleA;//게시글명
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="article")
	private ProductSellList articleB;//게시글명
	
	private Double similarity;// 유사도
	
}
