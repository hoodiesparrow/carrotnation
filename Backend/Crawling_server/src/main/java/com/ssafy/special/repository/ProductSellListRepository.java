package com.ssafy.special.repository;

import org.apache.logging.log4j.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.special.domain.ProductSellList;
import com.ssafy.special.domain.ProductSellListPK;

public interface ProductSellListRepository extends JpaRepository<ProductSellList, ProductSellListPK>, ProductSellListRepositoryCustom {
    @Modifying
    @Query(
            value = "truncate table product_sell_list",
            nativeQuery = true
    )
    void truncateProductSellList();
    
    @Query(value = "SELECT * INTO OUTFILE 'C:/SSAFY/aws/test/sellList.txt'" + 
    		"FIELDS TERMINATED BY ','" + 
    		"LINES TERMINATED BY '\\n'" + 
    		"FROM product;",
    		nativeQuery = true)
    Message txtProductSellList();
}
