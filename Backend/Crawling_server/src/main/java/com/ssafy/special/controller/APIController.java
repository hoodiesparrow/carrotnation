package com.ssafy.special.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.special.domain.ProductSellList;
import com.ssafy.special.service.ProductSellListInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class APIController {

	private final ProductSellListInfoService productSellListInfoService;

	//ProductSellList 뿌려줌(최신사이클만)
	@GetMapping("/productselllist")
	public ResponseEntity<List<ProductSellList>> getProductSellList(@RequestParam(defaultValue = "0") int page) {
			List<ProductSellList> productSellLists = productSellListInfoService.getProductSellLists(page);
			
			if(productSellLists==null) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}else if(productSellLists.size()==0) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(productSellLists);
	}
}