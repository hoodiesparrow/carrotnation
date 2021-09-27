package com.ssafy.special.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.special.domain.ProductSellList;
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
	
	//게시글 상세보기
	@Override
	public ProductSellListResponseDTO getProductSellDetail(String market, long pid) {
		ProductSellList tmp =productSellListRepository.findByIdAndMarket(pid, market).orElse(null);
		
		if(tmp==null)
			return null;
		
		ProductSellListResponseDTO ret=new ProductSellListResponseDTO();
		
		ret.setId(tmp.getId());
		ret.setMarket(tmp.getMarket());
		ret.setProductId(tmp.getProductId().getId());
		ret.setTitle(tmp.getTitle());
		ret.setContent(tmp.getContent());
		ret.setPrice(tmp.getPrice());
		ret.setCreateDate(tmp.getCreateDate());
		ret.setLink(tmp.getLink());
		ret.setImg(tmp.getImg());
		ret.setLocation(tmp.getLocation());
		ret.setCycle(tmp.getCycle());
		
		return ret;
	}

	
}
