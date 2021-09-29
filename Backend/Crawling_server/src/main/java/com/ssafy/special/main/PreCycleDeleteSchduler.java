package com.ssafy.special.main;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ssafy.special.service.PreCycleDeleteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class PreCycleDeleteSchduler {
	private final PreCycleDeleteService preCycleDeleteService;
	
	@Scheduled(fixedRate = 1000 * 60 * 60 * 24)//24시간마다 동작
	private void preCycleDelete() {
		log.info("이전 사이클 데이터 삭제를 시작합니다");
		//ProductSellArticleSimiler의 이전 사이클 삭제
		preCycleDeleteService.deleteProductSellArticleSimiler();		
		//ProductSellList의 이전 사이클 삭제
		preCycleDeleteService.deleteProductSellList();	
		log.info("이전 사이클 데이터들이 삭제되었습니다");
	}	
}
