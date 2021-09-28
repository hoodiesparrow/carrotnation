package com.ssafy.special.repository;

import java.util.List;
import java.util.Optional;

import com.ssafy.special.dto.DatePriceResponseDTO;

public interface DatePriceRepositoryCustom {
	//해당 제품의 날짜별 가격추이 리턴 
	Optional<List<DatePriceResponseDTO>> getDatePriceResponseDTO(long p);	
}
