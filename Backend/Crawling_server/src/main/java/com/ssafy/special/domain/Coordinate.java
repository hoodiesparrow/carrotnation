package com.ssafy.special.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Coordinate {
	
	@Id
	private long code;
	
	private int count;
	
	private String sido;
	
	private String gugun;
	
	private String dong;
	
	private String lat;
	
	private String lon;
	
	private String address;

}
