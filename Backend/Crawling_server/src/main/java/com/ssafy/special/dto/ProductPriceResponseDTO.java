package com.ssafy.special.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductPriceResponseDTO {

	private long id;//게시글 pid
	private String market;//마켓종류
	private long price;//가격
	private long maxPrice;//최고가
	private long minPrice;//최저가
	
}
