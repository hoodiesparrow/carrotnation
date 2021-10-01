package com.ssafy.special.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.special.domain.ProductSellList;
//import com.ssafy.special.domain.QProductSellList;
import com.ssafy.special.domain.QProductSellList;
import com.ssafy.special.dto.ProductPriceResponseDTO;
import com.ssafy.special.dto.ProductSellListResponseDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductSellListRepositoryImpl implements ProductSellListRepositoryCustom{
	private final JPAQueryFactory queryFactory;	
	
	//현재 사이클 이상인 데이터만 가져옴(페이징, 프론트 뿌리는용)
	@Override
	public Optional<List<ProductSellListResponseDTO>> getRecentProductSellListWithPaging(Long cycle,Pageable page, long pid, int sort, List<Integer> market){
		QProductSellList qpsl= QProductSellList.productSellList;
		
		List<ProductSellListResponseDTO> result= queryFactory.select(
											Projections.constructor(ProductSellListResponseDTO.class,
													qpsl.aid,qpsl.market,qpsl.productId.id,qpsl.title,qpsl.content,
													qpsl.price,qpsl.createDate,qpsl.link,qpsl.img,qpsl.location,qpsl.cycle)
										)
										.from(qpsl)
										.where(qpsl.cycle.eq(cycle).and(qpsl.productId.id.eq(pid)),
												getMarketContition(market, qpsl))
										.orderBy(getSortedColumn(sort))
										.offset(page.getOffset())
										.limit(page.getPageSize())										
										.fetch();
		
		return Optional.ofNullable(result);
	}
	
	private OrderSpecifier<?> getSortedColumn(int sort){
		String fieldName = "createDate";
		Order order = Order.DESC;
		if(sort==1) {//날짜 내림차순
			fieldName = "createDate";
			order = Order.DESC;
		}else if(sort == 2) {//날짜 오름차순
			fieldName = "createDate";
			order = Order.ASC;
		}else if(sort == 3) {//가격 내림차순
			fieldName = "price";
			order = Order.DESC;
		}else if(sort == 4) {//가격 오름차순
			fieldName = "price";
			order = Order.ASC;
		}
	    Path<Object> fieldPath = Expressions.path(Object.class, QProductSellList.productSellList, fieldName);     
	    return new OrderSpecifier(order, fieldPath);
	}
	
	private BooleanBuilder getMarketContition(List<Integer> market,QProductSellList qpsl) {
		BooleanBuilder bb= new BooleanBuilder();
		
		for(Integer i : market) {
			if(i==1) {
				bb.or(qpsl.market.eq("daangn"));
			}else if(i==2) {
				bb.or(qpsl.market.eq("thunder"));
			}else if(i==3) {
				bb.or(qpsl.market.like("joonna%"));
			}
		}
		return bb;
	}
	
	//현재 사이클 이상인 데이터갯수 가져옴
	@Override
	public Optional<Long> getRecentProductSellListCount(Long cycle, long pid){
		QProductSellList qpsl= QProductSellList.productSellList;
		
		Long result= queryFactory.selectFrom(qpsl)
										.where(qpsl.cycle.eq(cycle).and(qpsl.productId.id.eq(pid)))							
										.fetchCount();
		
		return Optional.ofNullable(result);
	}
	
	
	//현재 사이클 이상인 데이터만 가져옴(페이징 없음, 분석용 hdfs에 보내는용도)
	@Override
	public Optional<List<ProductSellList>> getRecentProductSellList(Long cycle, long id){
		QProductSellList qpsl= QProductSellList.productSellList;
		
		List<ProductSellList> result= queryFactory.selectFrom(qpsl)
										.where(qpsl.cycle.eq(cycle).and(qpsl.productId.id.eq(id)))
										.orderBy(qpsl.createDate.desc())									
										.fetch();
		
		return Optional.ofNullable(result);
	}

	@Override
	public Optional<List<ProductPriceResponseDTO>> getProductByPrice(Long cycle, long id) {
		// TODO Auto-generated method stub
		QProductSellList qpsl= QProductSellList.productSellList;
		List<ProductPriceResponseDTO> result = queryFactory.select(
										Projections.constructor(ProductPriceResponseDTO.class,
												qpsl.aid, qpsl.market,qpsl.price,qpsl.productId.maxPrice)
										)
										.from(qpsl)
										.where(qpsl.cycle.eq(cycle).and(qpsl.productId.id.eq(id)))
										.fetch();
		return Optional.ofNullable(result);

	}	
	
	// cycle 보다 작은 데이터들 삭제
	@Override
	public void deletePreCycle(long cycle) {
		QProductSellList qpsl= QProductSellList.productSellList;
		
		queryFactory.delete(qpsl).where(qpsl.cycle.lt(cycle)).execute();	
	}
	
}
