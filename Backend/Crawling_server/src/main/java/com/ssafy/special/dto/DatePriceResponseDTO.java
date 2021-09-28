package com.ssafy.special.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DatePriceResponseDTO {	

	private long productId;//제품 id
	
	private String productName;//제품명
		
	private LocalDate pdate;//시간없이 날짜만
	
	private long price;	
}
