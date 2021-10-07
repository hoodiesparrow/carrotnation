package com.ssafy.special.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.special.domain.DatePrice;
import com.ssafy.special.domain.Product;
import com.ssafy.special.dto.DatePriceResponseDTO;
import com.ssafy.special.repository.DatePriceRepository;
import com.ssafy.special.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DatePriceServiceImpl implements DatePriceService {

	private final ProductRepository productRepository;
	private final DatePriceRepository datePriceRepository;
	
	
	@Override
	public void setDatePrice() {
		List<Product> products = productRepository.findAll();
		
		for(Product p: products) {
			LocalDate now =LocalDate.now();
			int nowtime = Integer.parseInt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH")));
			DatePrice datePrice = datePriceRepository.findByPdateAndProductId(now, p).orElse(new DatePrice(now));
			long price = datePrice.getPrice();
			if(nowtime>0)
				price = price * nowtime;
			price = (price + p.getAvgPrice())/(nowtime+1);
			datePrice.setPrice(price);
			datePrice.setProductId(p);
			datePriceRepository.save(datePrice);
		}		
	}
	
	@Override
	public List<DatePriceResponseDTO> getDatePrice(long pid, int size) {
		Product product = productRepository.findById(pid).orElse(null);
		
		if(product == null)
			return null;
		
		List<DatePriceResponseDTO> ret = datePriceRepository.getDatePriceResponseDTO(pid, size).orElse(new ArrayList<DatePriceResponseDTO>());
		
		return ret;		
	}
}
