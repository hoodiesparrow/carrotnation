package com.ssafy.special.service;

import java.util.List;

import com.ssafy.special.domain.ProductSellList;

public interface SimilarityService {
	public ProductSellList findProduct(long id, String market);
	public void similarityProduct();
}