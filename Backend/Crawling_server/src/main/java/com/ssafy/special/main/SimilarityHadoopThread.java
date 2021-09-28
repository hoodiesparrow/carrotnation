package com.ssafy.special.main;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ssafy.special.domain.Product;
import com.ssafy.special.service.SimilarityService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SimilarityHadoopThread implements Runnable{
	
	private final SimilarityService similarityService;
	private List<Product> product;
	
	public void run() {
		// TODO Auto-generated method stub
		similarityService.similarityProduct(product);
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

}
