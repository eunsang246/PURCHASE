package com.ngps.spring.myapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface QuotationService {
	
	
	public int saveQuo(Map<?, ?> paramMap) throws Exception;
	
	public int updateRequest(Map<?, ?> paramMap) throws Exception;

	public int deleteRequest(Map<?, ?> paramMap) throws Exception;
	
	public List<Map<String, Object>> SelectQuotation(Map<?, ?> paramMap) throws Exception;
		
	public Map<String, Object> getQuoInfo(Map<?, ?> paramMap) throws Exception;
	
	public Map<String, Object> getNewQuoNo(Map<?, ?> paramMap) throws Exception;
	
	public List<Map<String, Object>> getCompList(Map<?, ?> paramMap) throws Exception;
	
	public List<Map<String, Object>> getQuoCompList(Map<?, ?> paramMap) throws Exception;
	
	public List<Map<String, Object>> getReqItemList(Map<?, ?> paramMap) throws Exception;	
	


}
