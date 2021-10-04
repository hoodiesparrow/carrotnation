package com.ssafy.special.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.special.dto.PriceStepResponseDTO;
import com.ssafy.special.dto.ProductPriceResponseDTO;
import com.ssafy.special.repository.ProductSellListRepository;

@Service
public class ProductByPriceServiceImpl implements ProductByPriceService {

	@Autowired
	ProductSellListRepository productSellListRepository;

	@Override
	public Map<String, PriceStepResponseDTO> byPrice(Long id, Long cycle) {
		List<ProductPriceResponseDTO> list = productSellListRepository.getProductByPrice(cycle,id).orElse(new ArrayList<ProductPriceResponseDTO>());
		int[] arr = new int[5];
		long[] stepPrice = new long[6];
		stepPrice[5] = list.get(0).getMaxPrice();
		stepPrice[0] = priceRound(stepPrice[5]*0.1);
		stepPrice[1] = priceRound(stepPrice[5]*0.28);
		stepPrice[2] = priceRound(stepPrice[5]*0.46);
		stepPrice[3] = priceRound(stepPrice[5]*0.64);
		stepPrice[4] = priceRound(stepPrice[5]*0.82);
		
		Map<String,PriceStepResponseDTO> pricemap = new HashMap<String, PriceStepResponseDTO>();
		
		for(ProductPriceResponseDTO ppr:list) {
			if(stepPrice[0]>=ppr.getPrice()) {
				continue;
			}else if(stepPrice[0]<ppr.getPrice() && stepPrice[1]>=ppr.getPrice()) {
				arr[0]++;
			}else if(stepPrice[1]<ppr.getPrice() && stepPrice[2]>=ppr.getPrice()) {
				arr[1]++;
			}else if(stepPrice[2]<ppr.getPrice() && stepPrice[3]>=ppr.getPrice()) {
				arr[2]++;
			}else if(stepPrice[3]<ppr.getPrice() && stepPrice[4]>=ppr.getPrice()) {
				arr[3]++;
			}else if(stepPrice[4]<ppr.getPrice() && stepPrice[5]>=ppr.getPrice()) {
				arr[4]++;
			}
		}
		PriceStepResponseDTO psr;
		psr = new PriceStepResponseDTO();
		psr.setMin(stepPrice[0]);
		psr.setMax(stepPrice[1]);
		psr.setCount(0);
		pricemap.put("1Step", psr);
		for(int i=1;i<5;i++) {
			psr = new PriceStepResponseDTO();
			psr.setMin(stepPrice[i]);
			psr.setMax(stepPrice[i+1]);
			psr.setCount(arr[i]);
			pricemap.put(i+1+"Step", psr);
		}
		return pricemap;
	}
	
	public long priceRound(double price) {
		String temp = Integer.toString((int)Math.round(price));
		int round =(int) Math.round(Integer.parseInt(temp.substring(0, temp.length()-3))*0.1);
		temp = Integer.toString(round)+"0000";
		return Long.parseLong(temp);
	}
	
	

}
