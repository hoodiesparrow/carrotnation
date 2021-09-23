package com.ssafy.special.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.special.domain.ProductSellList;
import com.ssafy.special.domain.QProductSellList;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductSellListRepositoryImpl implements ProductSellListRepositoryCustom{
	private final JPAQueryFactory queryFactory;	
	
	//현재 사이클 이상인 데이터만 가져옴
	@Override
	public Optional<List<ProductSellList>> getRecentProductSellList(Long cycle){
		QProductSellList qpsl= QProductSellList.productSellList;
		
		List<ProductSellList> result= queryFactory.selectFrom(qpsl)
										.where(qpsl.cycle.goe(cycle))
										.fetch();
		
		return Optional.ofNullable(result);
	}
	
}
