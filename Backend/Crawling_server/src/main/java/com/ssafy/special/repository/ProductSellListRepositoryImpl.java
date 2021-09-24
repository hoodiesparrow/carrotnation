package com.ssafy.special.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.special.domain.ProductSellList;
//import com.ssafy.special.domain.QProductSellList;
import com.ssafy.special.domain.QProductSellList;
import com.ssafy.special.dto.ProductSellListResponseDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductSellListRepositoryImpl implements ProductSellListRepositoryCustom{
	private final JPAQueryFactory queryFactory;	
	
	//현재 사이클 이상인 데이터만 가져옴(페이징, 프론트 뿌리는용)
	@Override
	public Optional<List<ProductSellListResponseDTO>> getRecentProductSellListWithPaging(Long cycle,Pageable page, long pid){
		QProductSellList qpsl= QProductSellList.productSellList;
		
		List<ProductSellListResponseDTO> result= queryFactory.select(
											Projections.constructor(ProductSellListResponseDTO.class,
													qpsl.id,qpsl.market,qpsl.productId.id,qpsl.title,qpsl.content,
													qpsl.price,qpsl.createDate,qpsl.link,qpsl.img,qpsl.location,qpsl.cycle)
										)
										.from(qpsl)
										.where(qpsl.cycle.goe(cycle).and(qpsl.productId.id.eq(pid)))
										.orderBy(qpsl.createDate.asc())
										.offset(page.getOffset())
										.limit(page.getPageSize())										
										.fetch();
		
		return Optional.ofNullable(result);
	}
	
	//현재 사이클 이상인 데이터갯수 가져옴
	@Override
	public Optional<Long> getRecentProductSellListCount(Long cycle, long pid){
		QProductSellList qpsl= QProductSellList.productSellList;
		
		Long result= queryFactory.selectFrom(qpsl)
										.where(qpsl.cycle.goe(cycle).and(qpsl.productId.id.eq(pid)))
										.orderBy(qpsl.createDate.asc())								
										.fetchCount();
		
		return Optional.ofNullable(result);
	}
	
	
	//현재 사이클 이상인 데이터만 가져옴(페이징 없음, 분석용 hdfs에 보내는용도)
	@Override
	public Optional<List<ProductSellList>> getRecentProductSellList(Long cycle){
		QProductSellList qpsl= QProductSellList.productSellList;
		
		List<ProductSellList> result= queryFactory.selectFrom(qpsl)
										.where(qpsl.cycle.goe(cycle))
										.orderBy(qpsl.createDate.asc())									
										.fetch();
		
		return Optional.ofNullable(result);
	}
	
}
