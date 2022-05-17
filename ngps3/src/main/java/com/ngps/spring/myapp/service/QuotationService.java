package com.ngps.spring.myapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface QuotationService {
	

	public int InsertRequest(Map<?, ?> paramMap) throws Exception;
	
	public int saveReqCompany(Map<?, ?> paramMap) throws Exception;

	public int UpdateRequest(Map<?, ?> paramMap) throws Exception;

	public int DeleteRequest(Map<?, ?> paramMap) throws Exception;
	
	public List<Map<String, Object>> SelectQuotation(Map<?, ?> paramMap) throws Exception;

	public List<Map<String, Object>> ReqInfo(String REQ_NO) throws Exception;
	
	public List<Map<String, Object>> QuoComList(String REQ_NO) throws Exception;
	
	public List<Map<String, Object>> CompanyList(Map<?, ?> paramMap) throws Exception;	
	
}
