package com.ssafy.special.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.special.domain.Product;
import com.ssafy.special.domain.ProductSellList;
import com.ssafy.special.domain.ProductSellListPK;

public interface ProductSellListRepository extends JpaRepository<ProductSellList, ProductSellListPK>, ProductSellListRepositoryCustom {

//    @Query(value = "SELECT * INTO OUTFILE '/home/ubuntu/mysqltablefile/sellList.txt' FIELDS TERMINATED BY '|' " + 
//    		"LINES TERMINATED BY '\\n' " + 
//    		"FROM product_sell_list "+
//    		"where cycle >= :date" ,
//    		nativeQuery = true)
  @Query(value = "SELECT *  FROM product_sell_list where cycle >= :date",
	nativeQuery = true)
    List<ProductSellList> txtProductSellList(@Param("date")Long date);
  
  	Optional<ProductSellList> findByIdAndMarket(Long id, String market);
  	
  	Optional<List<ProductSellList>> findByProductId(Product product);
  	
  	Optional<List<ProductSellList>> findByCycleLessThan(Long cycle);
}
