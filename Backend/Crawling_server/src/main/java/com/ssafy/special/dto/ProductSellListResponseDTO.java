package com.ssafy.special.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSellListResponseDTO {
	private long id;//id
	private long aid;//게시글 aid
	private String market;//마켓종류
		
	private long productId;//품명
	
	private String title;//제목
	
	private String content;//내용
	
	private long price;//가격
	
	private LocalDateTime createDate;//작성일자
	
	private String link;//게시글 상세보기 링크
	
	private String img;//이미지 링크
	
	private String location;//지역

	private Long cycle;//크롤링 주기 파악을 위해 만듬
}
