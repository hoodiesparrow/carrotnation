package com.ssafy.special.main;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ssafy.special.service.SimilarityService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SimilarityThread implements Runnable{
	private final SimilarityService similarityService;
	private List<Long> pidList;
	
	public void setPidList(List<Long> pidList) {
		this.pidList = pidList;
	}
	
	@Override
	public void run() {
		//크롤링 끝난뒤 크롤링한 데이터를 Komoran으로 형태소 분석후 NNP, NNG만 클러스터 서버에 FTP로 파일전송
		//전송후 해당 파일을 HDFS에 집어넣고 하둡을 돌려서 유사도 뽑아낸뒤 그걸 DB에 저장
		similarityService.similarityProduct(pidList);
		//여기에 pidList 매개변수로 넘겨서 동작하는 메서드 실행시켜줘야함
	}




	

}
