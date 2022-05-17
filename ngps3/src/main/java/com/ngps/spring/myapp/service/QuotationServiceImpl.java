package com.ngps.spring.myapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngps.spring.myapp.model.mapper.QuotationMapper;

@Service
public class QuotationServiceImpl implements QuotationService{


	@Autowired
	QuotationMapper quotationMapper;	

	@Override
	public List<Map<String, Object>> SelectQuotation(Map<?, ?> paramMap) throws Exception {
	
		// TODO Auto-generated method stub
		return quotationMapper.SelectQuotation(paramMap);
	}

	
	@Override
	public List<Map<String, Object>> ReqInfo(String REQ_NO) throws Exception {
	
		// TODO Auto-generated method stub
		return quotationMapper.ReqInfo(REQ_NO);
	}
	
	@Override
	public List<Map<String, Object>> CompanyList(Map<?, ?> paramMap) throws Exception {
	
		// TODO Auto-generated method stub
		return quotationMapper.CompanyList(paramMap);
	}
	
	@Override
	public List<Map<String, Object>> QuoComList(String REQ_NO) throws Exception {
	
		// TODO Auto-generated method stub
		return quotationMapper.QuoComList(REQ_NO);
	}
	
	@Override
	public int InsertRequest(Map<?, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return quotationMapper.InsertRequest(paramMap);
	}

	@Override
	public int UpdateRequest(Map<?, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return quotationMapper.UpdateRequest(paramMap);
	}

	@Override
	public int DeleteRequest(Map<?, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return quotationMapper.DeleteRequest(paramMap);
	}
	
	@Override
	public int saveReqCompany(Map<?, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return quotationMapper.saveReqCompany(paramMap);
	}

	
}
