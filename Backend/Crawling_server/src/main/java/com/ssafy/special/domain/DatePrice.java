package com.ssafy.special.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DatePrice {
	@Id
	@GeneratedValue
	private long id;//기본키
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	private Product productId;//품명
	
	private LocalDate pdate;//시간없이 날짜만
	
	private long price;	
	
	public DatePrice(LocalDate pdate) {
		this.pdate=pdate;
	}
}
