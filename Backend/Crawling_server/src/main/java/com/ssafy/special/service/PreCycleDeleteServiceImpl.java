package com.ssafy.special.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.special.repository.ProductSellArticleSimilerRepository;
import com.ssafy.special.repository.ProductSellListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PreCycleDeleteServiceImpl implements PreCycleDeleteService {
	private final ProductSellArticleSimilerRepository productSellArticleSimilerRepository;
	private final ProductSellListRepository productSellListRepository;
	
	
	//현재시간보다 1시간전 미만인 사이클 삭제
	@Override
	@Transactional
	public void deleteProductSellArticleSimiler() {
		//cycle 보다 작은 데이터들 삭제
		LocalDateTime now = LocalDateTime.now().minusHours(1);
		Long cycle = Long.parseLong(now.format(DateTimeFormatter.ofPattern("yyMMddHH")));	
		productSellArticleSimilerRepository.deletePreCycle(cycle);
	}
	
	//현재시간보다 1시간전 미만인 사이클 삭제
	@Override
	@Transactional
	public void deleteProductSellList() {
		//cycle 보다 작은 데이터들 삭제
		LocalDateTime now = LocalDateTime.now().minusHours(1);
		Long cycle = Long.parseLong(now.format(DateTimeFormatter.ofPattern("yyMMddHH")));	
		
	  	List<Long> list = productSellListRepository.findByCycleLessThan(cycle).orElse(new ArrayList<>()).stream().map((a)->a.getId()).collect(Collectors.toList());
	  	productSellArticleSimilerRepository.deleteByProductSellListIds(list);
	  	productSellListRepository.deletePreCycle(cycle);
	}
	
}
