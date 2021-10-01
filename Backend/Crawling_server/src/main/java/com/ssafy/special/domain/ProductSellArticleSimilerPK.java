package com.ssafy.special.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSellArticleSimilerPK implements Serializable {
	private long articleA;//게시글
	
	private long articleB;//게시글
}
