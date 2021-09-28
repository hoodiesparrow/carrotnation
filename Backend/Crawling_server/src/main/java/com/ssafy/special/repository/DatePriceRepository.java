package com.ssafy.special.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.special.domain.DatePrice;
import com.ssafy.special.domain.Product;

public interface DatePriceRepository extends JpaRepository<DatePrice, Long>, DatePriceRepositoryCustom {
	Optional<DatePrice> findByPdateAndProductId(LocalDate pdate, Product product);
	
}
