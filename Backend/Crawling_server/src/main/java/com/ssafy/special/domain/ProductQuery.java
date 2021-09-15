package com.ssafy.special.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ProductQuery {
	@Id
	private String query;//검색어
}
