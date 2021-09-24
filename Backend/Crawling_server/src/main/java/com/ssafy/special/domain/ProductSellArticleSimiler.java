package com.ssafy.special.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@IdClass(ProductSellArticleSimilerPK.class)
public class ProductSellArticleSimiler {
	
	@Id
	private long idA;//게시글 pid
	@Id
	private String marketA;//마켓종류
	
	@Id
	private long idB;//게시글 pid
	@Id
	private String marketB;//마켓종류
	
	private Double similarity;// 유사도
	
}
