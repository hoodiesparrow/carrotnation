package com.ssafy.special.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.special.domain.Product;
import com.ssafy.special.domain.ProductQuery;

public interface ProductQueryRepository extends JpaRepository<ProductQuery, Long>{
	Optional<ProductQuery> findByProductIdAndMarket(Product productId, String market);
}
