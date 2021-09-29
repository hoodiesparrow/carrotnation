package com.ssafy.special.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.special.domain.QProductSellArticleSimiler;
import com.ssafy.special.dto.ProductSellArticleSimilerResponseDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductSellArticleSimilerRepositoryImpl implements ProductSellArticleSimilerRepositoryCustom {
	private final JPAQueryFactory queryFactory;	
	
	// 해당 market, pid를 가지는 게시글과 유사한 게시글리스트 리턴
	@Override
	public Optional<List<ProductSellArticleSimilerResponseDTO>> getProductSellArticleSimiler(long pid, String market, Long cycle){
		QProductSellArticleSimiler qpsas = QProductSellArticleSimiler.productSellArticleSimiler;
		
		List<ProductSellArticleSimilerResponseDTO> result=null;
		result = queryFactory.select(
				Projections.constructor(ProductSellArticleSimilerResponseDTO.class,
						qpsas.articleB.id,qpsas.articleB.market,qpsas.articleB.productId.id,qpsas.articleB.title,qpsas.articleB.content,
						qpsas.articleB.price,qpsas.articleB.createDate,qpsas.articleB.link,qpsas.articleB.img,qpsas.articleB.location,qpsas.articleB.cycle, qpsas.similarity)
			).from(qpsas)
			.where(qpsas.articleA.market.eq(market).and(qpsas.articleA.id.eq(pid)).and(qpsas.cycle.goe(cycle)),
					qpsas.articleB.market.eq(market).and(qpsas.similarity.between(30, 50))
						.or(qpsas.articleB.market.ne(market).and(qpsas.similarity.between(30, 70))))
			.fetch();
		List<ProductSellArticleSimilerResponseDTO> tmp=
		queryFactory.select(
				Projections.constructor(ProductSellArticleSimilerResponseDTO.class,
						qpsas.articleA.id,qpsas.articleA.market,qpsas.articleA.productId.id,qpsas.articleA.title,qpsas.articleA.content,
						qpsas.articleA.price,qpsas.articleA.createDate,qpsas.articleA.link,qpsas.articleA.img,qpsas.articleA.location,qpsas.articleA.cycle,qpsas.similarity)
			).from(qpsas)
			.where(qpsas.articleB.market.eq(market).and(qpsas.articleB.id.eq(pid)).and(qpsas.cycle.goe(cycle)),
					qpsas.articleA.market.eq(market).and(qpsas.similarity.between(30, 50))
						.or(qpsas.articleA.market.ne(market).and(qpsas.similarity.between(30, 70))))
			.fetch();
		result.addAll(tmp);
		return Optional.ofNullable(result);
	}
	
	
	// cycle 보다 작은 데이터들 삭제
	@Override
	public void deletePreCycle(long cycle) {
		QProductSellArticleSimiler qpsas = QProductSellArticleSimiler.productSellArticleSimiler;
		
		queryFactory.delete(qpsas).where(qpsas.cycle.lt(cycle)).execute();	
	}

}