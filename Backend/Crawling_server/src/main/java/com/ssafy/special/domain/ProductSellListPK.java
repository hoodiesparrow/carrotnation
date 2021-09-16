package com.ssafy.special.domain;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSellListPK implements Serializable {
	private long id;
	private String market;
	@Override
	public int hashCode() {
		return Objects.hash(this.id,this.market);
	}
	@Override
	public boolean equals(Object obj) {
		if(this.id == ((ProductSellListPK)obj).getId() && this.market.equals(((ProductSellListPK)obj).getMarket()))
			return true;
		return false;
	}	
	
	
}
