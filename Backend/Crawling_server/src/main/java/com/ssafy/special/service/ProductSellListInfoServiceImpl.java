package com.ssafy.special.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ssafy.special.dto.ProductSellListResponseDTO;
import com.ssafy.special.repository.ProductSellListRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ProductSellListInfoServiceImpl implements ProductSellListInfoService {
	private final ProductSellListRepository productSellListRepository;

	// 현재 사이클 이상인 데이터만 가져옴
	@Override
	public List<ProductSellListResponseDTO> getProductSellLists(int page, long pid) {
		LocalDateTime now = LocalDateTime.now().minusHours(1);
		Long cycle = Long.parseLong(now.format(DateTimeFormatter.ofPattern("yyMMddHH")));
		return productSellListRepository.getRecentProductSellListWithPaging(cycle, PageRequest.of(page, 20), pid)
				.orElse(new ArrayList<ProductSellListResponseDTO>());
	}

	// 현재 사이클 이상인 데이터 갯수 가져옴
	@Override
	public Long getProductSellListCount(long pid) {
		LocalDateTime now = LocalDateTime.now().minusHours(1);
		Long cycle = Long.parseLong(now.format(DateTimeFormatter.ofPattern("yyMMddHH")));
		return productSellListRepository.getRecentProductSellListCount(cycle, pid)
				.orElse(0l);
	}

}
