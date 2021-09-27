package com.ssafy.special.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProductSellArticleSimilerPK.class)
public class ProductSellArticleSimiler {
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
        @JoinColumn(name="idA"),
        @JoinColumn(name="marketA")
	})
	private ProductSellList articleA;//게시글명
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
        @JoinColumn(name="idB"),
        @JoinColumn(name="marketB")
	})
	private ProductSellList articleB;//게시글명
	
	private Double similarity;// 유사도
	
}
