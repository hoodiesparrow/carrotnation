package com.ssafy.special;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CrawlingServerApplication {	
	public static void main(String[] args) {
		SpringApplication.run(CrawlingServerApplication.class, args);
	}
}
