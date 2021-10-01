package com.ssafy.special.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.special.domain.Product;
import com.ssafy.special.domain.ProductSellList;
import com.ssafy.special.domain.ProductSellListPK;
import com.ssafy.special.dto.ProductSellListByDistanceResponseDTO;

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
  	
  	@Query(value="SELECT *,ST_Distance_Sphere(POINT(127.091961310776, 37.5379818127006), POINT(x, y)) AS distance\r\n" +  
  			"FROM product_sell_list\r\n" + 
  			"WHERE ST_Distance_Sphere(POINT(:lon, :lat), POINT(x, y)) <= 5000 and product_id=:pid\r\n" + 
  			"ORDER BY distance")
  	Optional<List<ProductSellListByDistanceResponseDTO>> nearProduct(@Param("lon")double x, @Param("lat")double y,@Param("pid")long id);
  	
}
