package com.ssafy.special.service;

import java.util.List;

import com.ssafy.special.dto.DatePriceResponseDTO;

public interface DatePriceService {
	//오늘 날짜의 각 제품별 평균가격을 DatePrice에 넣어줌
	void setDatePrice();
	//해당 pid를 가지는 제품의 가격추이를 리턴해줌
	 List<DatePriceResponseDTO> getDatePrice(long pid);
}
