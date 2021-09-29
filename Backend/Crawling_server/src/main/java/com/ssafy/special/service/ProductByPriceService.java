package com.ssafy.special.service;

import java.util.Map;

import com.ssafy.special.dto.PriceStepResponseDTO;

public interface ProductByPriceService {
	public Map<String,PriceStepResponseDTO> byPrice(Long id, Long cycle);
}
