package com.ssafy.special.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@IdClass(ProductSellListPK.class)
public class ProductSellList {

	@Id
//	@GenericGenerator(name = "productId", strategy = "com.ssafy.special.jpa.ProductIdGenerator")
//	@GeneratedValue(generator = "productId") 
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;//게시글 pid
	@Id
	private String market;//마켓종류
		
	@ManyToOne
	@JoinColumn(name="productId")
	private Product productId;//품명
	
	private String title;//제목
	
	private long price;//가격
	
	private LocalDateTime createDate;//작성일자
	
	private String link;//게시글 상세보기 링크
	
	private String img;//이미지 링크
	
	private String location;//지역
}