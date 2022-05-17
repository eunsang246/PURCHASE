package com.ngps.spring.myapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface OpenApiService {
	

	public int InsertOilApi(Map<?, ?> paramMap) throws Exception;

	public int UpdateOilApi(Map<?, ?> paramMap) throws Exception;

	public int DeleteOilApi(Map<?, ?> paramMap) throws Exception;
	
	public List<Map<String, Object>> OilPriceList(Map<?, ?> paramMap) throws Exception;

	
}
