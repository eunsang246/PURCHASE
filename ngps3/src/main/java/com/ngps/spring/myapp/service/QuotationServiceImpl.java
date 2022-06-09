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
	public Map<String, Object> getQuoInfo(Map<?, ?> paramMap) throws Exception {
	
		// TODO Auto-generated method stub
		return quotationMapper.getQuoInfo(paramMap);
	}
	
	@Override
	public Map<String, Object> getNewQuoNo(Map<?, ?> paramMap) throws Exception {
	
		// TODO Auto-generated method stub
		return quotationMapper.getNewQuoNo(paramMap);
	}

	
	@Override
	public int saveQuo(Map<?, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return quotationMapper.saveQuo(paramMap);
	}
	

	@Override
	public int updateRequest(Map<?, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return quotationMapper.updateRequest(paramMap);
	}

	@Override
	public int deleteRequest(Map<?, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return quotationMapper.deleteRequest(paramMap);
	}

	
	
	@Override
	public List<Map<String, Object>> getReqItemList(Map<?, ?> paramMap) throws Exception {
	
		// TODO Auto-generated method stub
		return quotationMapper.getReqItemList(paramMap);
	}
		

	@Override
	public List<Map<String, Object>> getQuoCompList(Map<?, ?> paramMap) throws Exception {
	
		// TODO Auto-generated method stub
		return quotationMapper.getQuoCompList(paramMap);
	}

	@Override
	public List<Map<String, Object>> getCompList(Map<?, ?> paramMap) throws Exception {
	
		// TODO Auto-generated method stub
		return quotationMapper.getCompList(paramMap);
	}

}
