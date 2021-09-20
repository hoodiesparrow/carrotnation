package com.ssafy.special.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.special.domain.ProductSellListPK;
import com.ssafy.special.domain.ProductSellListStack;

public interface ProductSellListStackRepository extends JpaRepository<ProductSellListStack, ProductSellListPK>,ProductSellListStackRepositoryCustom{

}
