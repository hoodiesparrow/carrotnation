package com.ssafy.special.dto;

import java.time.LocalDateTime;

import com.ssafy.special.domain.Product;

public interface ByDistance {

	long getId();

	long getAid();// 게시글 pid

	String getMarket();// 마켓종류

	Product getProductId();// 품명

	String getTitle();// 제목

	String getContent();// 내용

	long getPrice();// 가격

	LocalDateTime getCreateDate();// 작성일자

	String getLink();// 게시글 상세보기 링크

	String getImg();// 이미지 링크

	String getLocation();// 지역

	double getX();

	double getY();

	long getCycle();

	double getDistance();
}
