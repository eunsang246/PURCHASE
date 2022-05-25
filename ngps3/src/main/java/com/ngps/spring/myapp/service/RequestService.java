package com.ngps.spring.myapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface RequestService {
	
	
	public int saveReq(Map<?, ?> paramMap) throws Exception;
	
	public int updateRequest(Map<?, ?> paramMap) throws Exception;

	public int deleteRequest(Map<?, ?> paramMap) throws Exception;
	
	public List<Map<String, Object>> SelectRequest(Map<?, ?> paramMap) throws Exception;
		
	public List<Map<String, Object>> ReqCodeSelect(String MainCategory) throws Exception;

	public Map<String, Object> getReqInfo(Map<?, ?> paramMap) throws Exception;
	
	public Map<String, Object> getNewReqNo(Map<?, ?> paramMap) throws Exception;
	
	public List<Map<String, Object>> getItemList(Map<?, ?> paramMap) throws Exception;
	
	public List<Map<String, Object>> getReqItemList(Map<?, ?> paramMap) throws Exception;	
	


}
