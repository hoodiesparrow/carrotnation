package com.ssafy.special.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.special.domain.ProductSellList;
import com.ssafy.special.domain.ProductSellListPK;


public interface ProductSellListRepository extends JpaRepository<ProductSellList, ProductSellListPK>, ProductSellListRepositoryCustom {
//    @Modifying
//    @Query(
//            value = "truncate table product_sell_list",
//            nativeQuery = true
//    )
//    void truncateProductSellList();

    @Query(value = "SELECT * INTO OUTFILE '/home/ubuntu/mysqltablefile/sellList.txt' FIELDS TERMINATED BY '|' " + 
    		"LINES TERMINATED BY '\\n' " + 
    		"FROM product_sell_list "+
    		"where cycle >= :date" ,
    		nativeQuery = true)
    void txtProductSellList(@Param("date")Long date);
    
}
