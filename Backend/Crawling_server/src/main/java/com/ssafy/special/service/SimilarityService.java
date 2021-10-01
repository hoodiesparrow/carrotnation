package com.ssafy.special.service;

import java.util.List;

import com.ssafy.special.domain.Product; 
import com.ssafy.special.dto.ProductSellArticleSimilerResponseDTO;

public interface SimilarityService {
	public List<ProductSellArticleSimilerResponseDTO> returnSimilarity(long id);
	public void similarityProduct(List<Long> pidList);
}