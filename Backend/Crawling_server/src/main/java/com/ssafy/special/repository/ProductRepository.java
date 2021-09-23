package com.ssafy.special.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.special.domain.Product;
import com.ssafy.special.domain.ProductQuery;

public interface ProductRepository extends JpaRepository<Product, Long>{
	Optional<List<Product>> findByQuery(ProductQuery productQuery);
}
