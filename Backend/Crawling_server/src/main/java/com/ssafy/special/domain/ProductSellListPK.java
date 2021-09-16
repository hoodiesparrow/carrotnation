package com.ssafy.special.domain;

import java.io.Serializable;

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
		int h=0;
		if("daangn".equals(this.market))
			h=(int)this.id *10 +1;
		else if("thunder".equals(this.market))
			h=(int)this.id *10 +2;
		else 
			h=(int)this.id *10 +3;
		
		return h;
	}
	@Override
	public boolean equals(Object obj) {
		if(this.id == ((ProductSellListPK)obj).getId() && this.market.equals(((ProductSellListPK)obj).getMarket()))
			return true;
		return false;
	}	
	
	
}
