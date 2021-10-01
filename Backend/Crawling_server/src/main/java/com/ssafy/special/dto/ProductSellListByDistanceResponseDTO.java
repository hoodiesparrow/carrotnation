package com.ssafy.special.dto;

import java.time.LocalDateTime;

import com.ssafy.special.domain.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductSellListByDistanceResponseDTO {

	private long id;
	
	private long aid;//게시글 pid

	private String market;//마켓종류
		
	private Product productId;//품명
	
	private String title;//제목
	
	private String content;//내용
	
	private long price;//가격
	
	private LocalDateTime createDate;//작성일자
	
	private String link;//게시글 상세보기 링크
	
	private String img;//이미지 링크
	
	private String location;//지역
	
	private double x;
	
	private double y;

	private long cycle;
	
	private double distance;
}
