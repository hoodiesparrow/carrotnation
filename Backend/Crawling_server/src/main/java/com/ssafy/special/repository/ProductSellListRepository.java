package com.ssafy.special.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.special.domain.ProductSellList;
import com.ssafy.special.domain.ProductSellListPK;

public interface ProductSellListRepository extends JpaRepository<ProductSellList, ProductSellListPK> {

}
