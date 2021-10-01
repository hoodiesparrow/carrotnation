package com.ssafy.special.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.ssafy.special.domain.ProductSellList;
import com.ssafy.special.dto.ProductPriceResponseDTO;
import com.ssafy.special.dto.ProductSellListResponseDTO;

public interface ProductSellListRepositoryCustom {
	//현재 사이클 이상인 데이터만 가져옴(페이징, 프론트 뿌리는용)
	Optional<List<ProductSellListResponseDTO>> getRecentProductSellListWithPaging(Long cycle,Pageable page, long pid, int sort, List<Integer> market);
	
	//현재 사이클 이상인 데이터갯수 가져옴
	Optional<Long> getRecentProductSellListCount(Long cycle, long pid, List<Integer> market);
	 
	//현재 사이클 이상인 데이터만 가져옴(페이징 없음, 분석용 hdfs에 보내는용도)
	Optional<List<ProductSellList>> getRecentProductSellList(Long cycle,long id);
	
	Optional<List<ProductPriceResponseDTO>> getProductByPrice(Long cycle,long id);

	void deletePreCycle(long cycle);
}
