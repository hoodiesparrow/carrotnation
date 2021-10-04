package com.ssafy.special.service;

import java.util.List;

import com.ssafy.special.dto.ByDistance;

public interface NearProductService {
	public List<ByDistance> nearProduct(double lon, double lat, long pid, int page, int sort, int market);
}
