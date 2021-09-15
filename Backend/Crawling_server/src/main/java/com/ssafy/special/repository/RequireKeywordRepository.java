package com.ssafy.special.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.special.domain.Product;
import com.ssafy.special.domain.RequireKeyword;

public interface RequireKeywordRepository extends JpaRepository<RequireKeyword, Long>{
	Optional<List<RequireKeyword>> findByProductIdAndMarket(Product productId, String market);
}
