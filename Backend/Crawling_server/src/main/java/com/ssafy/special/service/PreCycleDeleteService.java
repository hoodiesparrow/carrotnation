package com.ssafy.special.service;

public interface PreCycleDeleteService {
	//현재시간보다 1시간전 미만인 사이클 삭제
	void deleteProductSellArticleSimiler();
	
	//현재시간보다 1시간전 미만인 사이클 삭제
	void deleteProductSellList();
}
