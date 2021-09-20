package com.ssafy.special.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.special.domain.ProductSellListStack;
import com.ssafy.special.domain.QProductSellList;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductSellListRepositoryImpl implements ProductSellListRepositoryCustom{
	private final JPAQueryFactory queryFactory;
	
	@Override
	public Optional<List<ProductSellListStack>> getProductSellListByProductSellListStack() {
		QProductSellList qPSL=QProductSellList.productSellList;
		
		List<ProductSellListStack> result=null;
		result=queryFactory.select(Projections.constructor(ProductSellListStack.class,
				qPSL.id,qPSL.market,qPSL.productId,qPSL.title,qPSL.content,qPSL.price,qPSL.createDate,qPSL.link,qPSL.img,qPSL.location))
			.from(qPSL)
			.where()
			.fetch();
		
		return Optional.of(result);
	}
	
}
