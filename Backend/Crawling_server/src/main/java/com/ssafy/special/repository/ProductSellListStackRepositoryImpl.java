package com.ssafy.special.repository;



import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ProductSellListStackRepositoryImpl implements ProductSellListStackRepositoryCustom{
	private final JPAQueryFactory queryFactory;
	

	
}
