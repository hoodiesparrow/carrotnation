package com.ssafy.special.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.special.domain.ExceptionKeyword;
import com.ssafy.special.domain.Product;

public interface ExceptionKeywordRepository extends JpaRepository<ExceptionKeyword, Long>{
	Optional<List<ExceptionKeyword>> findByProductIdAndMarket(Product productId, String market);
}
