package com.ssafy.special.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.special.domain.Product;
import com.ssafy.special.domain.QProduct;
import com.ssafy.special.domain.QProductSellList;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom{

	private final JPAQueryFactory queryFactory;	
	
	public Tuple getProductsMinMaxAvgPrice(Product product) {
		QProductSellList qpsl = QProductSellList.productSellList;
		
		Tuple result = queryFactory.select(qpsl.price.min(),qpsl.price.max(),qpsl.price.avg())
				.from(qpsl).where(qpsl.productId.eq(product)
									.and(qpsl.price.loe(qpsl.productId.releasePrice))//출고가 이하
									.and(qpsl.price.goe(qpsl.productId.releasePrice.divide(10))))//출고가의 10퍼센트 이상
				.fetchOne();
		
		Long tmp = result.get(0,Long.class);
		if(tmp==null)
			return null;
		
		return result;
	}
	
	
	public Optional<List<Long>> getProductIdByQuery(String query) {
		QProduct qp = QProduct.product;
		
		List<Long> result = queryFactory.select(qp.id)
				.from(qp).where(qp.query.query.eq(query)).fetch();
		
		
		return Optional.ofNullable(result);
	}
	
}
