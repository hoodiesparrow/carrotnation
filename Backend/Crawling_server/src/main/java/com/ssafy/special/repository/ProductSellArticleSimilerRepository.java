package com.ssafy.special.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.special.domain.ProductSellArticleSimiler;
import com.ssafy.special.domain.ProductSellArticleSimilerPK;

public interface ProductSellArticleSimilerRepository extends JpaRepository<ProductSellArticleSimiler, ProductSellArticleSimilerPK>,ProductSellArticleSimilerRepositoryCustom {

}
