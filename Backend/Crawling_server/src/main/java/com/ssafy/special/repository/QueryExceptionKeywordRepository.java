package com.ssafy.special.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.special.domain.ProductQuery;
import com.ssafy.special.domain.QueryExceptionKeyword;

public interface QueryExceptionKeywordRepository extends JpaRepository<QueryExceptionKeyword, Long> {
	Optional<List<QueryExceptionKeyword>> findByQuery(ProductQuery productQuery);
}
