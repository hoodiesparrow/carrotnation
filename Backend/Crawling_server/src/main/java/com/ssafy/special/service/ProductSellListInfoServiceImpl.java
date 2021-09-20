package com.ssafy.special.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.special.domain.ProductSellListStack;
import com.ssafy.special.repository.ProductSellListRepository;
import com.ssafy.special.repository.ProductSellListStackRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ProductSellListInfoServiceImpl implements ProductSellListInfoService {

	private final ProductSellListRepository productSellListRepository; 
	private final ProductSellListStackRepository productSellListStackRepository; 
	

	//ProductSellList테이블 비우기
	@Override
	public void truncateProductSellList() {
		productSellListRepository.truncateProductSellList();
	}
	
	//ProductSellListStack테이블에 ProductSellList테이블 데이터 반영
	@Override
	public void updateProductSellListStack() {
		List<ProductSellListStack> productSellListStack = productSellListRepository.getProductSellListByProductSellListStack().orElse(new ArrayList<ProductSellListStack>());
		log.info(productSellListStack.size()+"개의 데이터를 삽입중입니다");
		productSellListStackRepository.saveAll(productSellListStack);
	}
}
