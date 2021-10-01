package com.ssafy.special.service;

import java.util.ArrayList;
import java.util.List;

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
	
	@Override
	public List<ByDistance> nearProduct(double lon, double lat, long pid) {
		List<ByDistance> list= productSellListRepository.nearProduct(lon, lat, pid).orElse(null);
		return list;
	}

}
