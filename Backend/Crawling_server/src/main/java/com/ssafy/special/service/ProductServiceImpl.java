package com.ssafy.special.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.querydsl.core.Tuple;
import com.ssafy.special.domain.Product;
import com.ssafy.special.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	
	@Override
	public void setProductsMinMaxAvgPrice() {
		//물품 목록 받아옴
		List<Product> products= productRepository.findAll();
		
		for(Product p : products) {
			Tuple result = productRepository.getProductsMinMaxAvgPrice(p);
			
			if(result == null)
				continue;
			
			long min = result.get(0,Long.class);
			long max = result.get(1,Long.class);
			double avg = result.get(2,Double.class);
			long avgl = (long)avg;
			avgl= avgl/100 * 100;
			
			p.setMinPrice(min);
			p.setMaxPrice(max);
			p.setAvgPrice(avgl);			
			
			productRepository.save(p);			
		}
		
	}
	
	@Override
	public Product getProduct(long pid) {
		//물품 목록 받아옴
		Product product= productRepository.findById(pid).orElse(null);
		
		return product;		
	}
	@Override
	public List<Long> getProductidByQuery(String query) {
		//query에 해당하는 pid 받아옴
		List<Long> pidList= productRepository.getProductIdByQuery(query).orElse(new ArrayList<Long>());
		
		return pidList;		
	}
	
}
