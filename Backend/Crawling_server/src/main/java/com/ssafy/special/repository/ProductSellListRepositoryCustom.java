package com.ssafy.special.repository;

import java.util.List;
import java.util.Optional;

import com.ssafy.special.domain.ProductSellListStack;

public interface ProductSellListRepositoryCustom {
	Optional<List<ProductSellListStack>> getProductSellListByProductSellListStack();
}
