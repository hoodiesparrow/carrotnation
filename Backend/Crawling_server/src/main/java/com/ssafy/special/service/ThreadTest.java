package com.ssafy.special.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service("asyncTask")
public class ThreadTest {
	
	@Scheduled(fixedDelay = 1000 * 60 * 30)
	@Async("executor1")
	public void test1() {
		for(int i=0;i<100;i++) {
			System.out.println(i);
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}

}
