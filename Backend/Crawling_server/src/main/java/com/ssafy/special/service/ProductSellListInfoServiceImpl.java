package com.ssafy.special.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.special.repository.ProductSellListRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ProductSellListInfoServiceImpl implements ProductSellListInfoService {

	private final ProductSellListRepository productSellListRepository; 
	

//	//ProductSellList테이블 비우기
//	@Override
//	public void truncateProductSellList() {
//		productSellListRepository.truncateProductSellList();
//	}
}
