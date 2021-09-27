package com.ssafy.special.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.special.domain.ProductQuery;

public interface ProductQueryRepository extends JpaRepository<ProductQuery, String>{

}
