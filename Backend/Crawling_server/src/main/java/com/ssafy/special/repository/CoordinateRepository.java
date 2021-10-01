package com.ssafy.special.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.special.domain.Coordinate;
import com.ssafy.special.domain.DatePrice;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long>{
	Optional<Coordinate> findByaddress(String address);
}
