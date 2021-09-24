package com.ssafy.special.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSellArticleSimilerPK implements Serializable {
	@Id
	private long idA;//게시글 pid
	@Id
	private String marketA;//마켓종류
	
	@Id
	private long idB;//게시글 pid
	@Id
	private String marketB;//마켓종류
	@Override
	public int hashCode() {
		return Objects.hash(this.idA,this.marketA,this.idB,this.marketB);
	}
	@Override
	public boolean equals(Object obj) {
		ProductSellArticleSimilerPK tmp =(ProductSellArticleSimilerPK)obj;
		if(this.idA == tmp.getIdA() && this.marketA.equals(tmp.getMarketA()) && this.idB == tmp.getIdB() && this.marketB.equals(tmp.getMarketB()))
			return true;
		return false;
	}	
}
