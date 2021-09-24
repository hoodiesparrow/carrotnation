package com.ssafy.special.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.special.dto.ProductSellListResponseDTO;
import com.ssafy.special.service.ProductSellListInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class APIController {

	private final ProductSellListInfoService productSellListInfoService;

	//ProductSellList 뿌려줌(최신사이클만)
	@GetMapping("/productselllist")
	public ResponseEntity<Map<String, Object>> getProductSellList(@RequestParam(defaultValue = "0") int page, @RequestParam long pid) {
			List<ProductSellListResponseDTO> productSellLists = productSellListInfoService.getProductSellLists(page, pid);
			Long count = productSellListInfoService.getProductSellListCount(pid);
			
			Map<String, Object> ret = new HashMap<String, Object>();
			
			if(productSellLists==null) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}else if(productSellLists.size()==0) {
				ret.put("msg", "페이지, 제품번호를 다시 확인해 주세요");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ret);
			}
			long totalpage= count/20;
			if(count%20>0)
				totalpage+=1;
			ret.put("totalpage", totalpage);
			ret.put("list", productSellLists);			
			
			return ResponseEntity.status(HttpStatus.OK).body(ret);
	}
}