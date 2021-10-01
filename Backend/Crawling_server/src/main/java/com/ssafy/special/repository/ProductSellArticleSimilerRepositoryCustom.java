package com.ssafy.special.repository;

import java.util.List;
import java.util.Optional;

import com.ssafy.special.dto.ProductSellArticleSimilerResponseDTO;

public interface ProductSellArticleSimilerRepositoryCustom{
	// 해당 market, pid를 가지는 게시글과 유사한 게시글리스트 리턴
	Optional<List<ProductSellArticleSimilerResponseDTO>> getProductSellArticleSimiler(long id, Long cycle);
	
	// cycle 보다 작은 데이터들 삭제
	void deletePreCycle(long cycle);
	
	//해당 article을 가지는 데이터들 삭제
	public void deleteByProductSellListIds(List<Long> article);
}
