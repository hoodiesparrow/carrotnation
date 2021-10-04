package com.ssafy.special.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ssafy.special.dto.ByDistance;
import com.ssafy.special.dto.ProductSellListByDistanceResponseDTO;
import com.ssafy.special.repository.ProductSellListRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class NearProductServiceImpl implements NearProductService {
	
	private final ProductSellListRepository productSellListRepository;
//	 1 : 날짜 내림차순
//  2 : 날짜 오름차순
//  3 : 가격 내림차순
//  4 : 가격 오름차순
//  5 : 거리 가까운순
	@Override
	public List<ByDistance> nearProduct(double lon, double lat, long pid, int page, int sort, int market) {
		List<String> marketList = new ArrayList<String>();
		List<ByDistance> list=null;
		
		if(market==0) {
			marketList.add("joonnaApp");
			marketList.add("joonnaCafe");
			marketList.add("daangn");
			marketList.add("thunder");
		}else if(market==1) {
			marketList.add("daangn");
		}else if(market==2) {
			marketList.add("thunder");
		}else if(market==3) {
			marketList.add("joonnaApp");
			marketList.add("joonnaCafe");
		}
		
		if(sort == 0) {
			list = productSellListRepository.nearProduct(lon, lat, pid, marketList, PageRequest.of(page, 20))
					.orElse(null);
		}else if(sort == 1) {
			list = productSellListRepository.nearProduct(lon, lat, pid, marketList, PageRequest.of(page, 20, Sort.Direction.DESC, "createDate"))
					.orElse(null);
		}else if(sort == 2) {
			list = productSellListRepository.nearProduct(lon, lat, pid, marketList, PageRequest.of(page, 20, Sort.Direction.ASC, "createDate"))
					.orElse(null);
		}else if(sort == 3) {
			list = productSellListRepository.nearProduct(lon, lat, pid, marketList, PageRequest.of(page, 20, Sort.Direction.DESC, "price"))
					.orElse(null);
		}else if(sort == 4) {
			list = productSellListRepository.nearProduct(lon, lat, pid, marketList, PageRequest.of(page, 20, Sort.Direction.ASC, "price"))
					.orElse(null);
		}else if(sort == 5) {
			list = productSellListRepository.nearProduct(lon, lat, pid, marketList, PageRequest.of(page, 20, Sort.Direction.ASC, "distance"))
					.orElse(null);
		}
		
		return list;
	}
	@Override
	public int nearProductCount(double lon, double lat, long pid, int market) {
		List<String> marketList = new ArrayList<String>();
		
		if(market==0) {
			marketList.add("joonnaApp");
			marketList.add("joonnaCafe");
			marketList.add("daangn");
			marketList.add("thunder");
		}else if(market==1) {
			marketList.add("daangn");
		}else if(market==2) {
			marketList.add("thunder");
		}else if(market==3) {
			marketList.add("joonnaApp");
			marketList.add("joonnaCafe");
		}
		return productSellListRepository.nearProductCount(lon, lat, pid, marketList);
				
	}

}
