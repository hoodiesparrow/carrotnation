package com.ssafy.special.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {
	private String name;// 품명
	private String title;// 제목
	private long price;// 가격
	private LocalDateTime date;// 작성일자 (년, 월, 일)
	private String time;// 몇분 전
	private String link;// 원본 게시글 링크
	private String img;// 이미지는 어떻게 하지?
	private String location;// 지역
	private String content;//게시글 내용
}