package com.ssafy.special.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.special.domain.QDatePrice;
import com.ssafy.special.dto.DatePriceResponseDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DatePriceRepositoryImpl implements DatePriceRepositoryCustom {
	private final JPAQueryFactory queryFactory;	

	@Override
	public Optional<List<DatePriceResponseDTO>> getDatePriceResponseDTO(long p,int size){
		QDatePrice dp = QDatePrice.datePrice;
		
		List<DatePriceResponseDTO> result=
				queryFactory.select(Projections.constructor(DatePriceResponseDTO.class, dp.productId.id, dp.productId.name, dp.pdate, dp.price))
				.from(dp)
				.where(dp.productId.id.eq(p))
				.limit(size)
				.orderBy(dp.pdate.desc())
				.fetch();
		
		
		return Optional.ofNullable(result);
	}
	
}
