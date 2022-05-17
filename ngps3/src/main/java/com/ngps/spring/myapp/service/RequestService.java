package com.ngps.spring.myapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface RequestService {
	

	public int InsertRequest(Map<?, ?> paramMap) throws Exception;
	
	public int UpdateRequest(Map<?, ?> paramMap) throws Exception;

	public int DeleteRequest(Map<?, ?> paramMap) throws Exception;
	
	public List<Map<String, Object>> SelectRequest(Map<?, ?> paramMap) throws Exception;
		
	public List<Map<String, Object>> ReqCodeSelect(String MainCategory) throws Exception;

	public Map<String, Object> getReqInfo(Map<?, ?> paramMap) throws Exception;
	
	public Map<String, Object> getNewReqNo(Map<?, ?> paramMap) throws Exception;
	
	public List<Map<String, Object>> ItemList(Map<?, ?> paramMap) throws Exception;	
	
	public int saveReqItem(Map<?, ?> paramMap) throws Exception;

}
