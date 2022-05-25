package com.ngps.spring.myapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngps.spring.myapp.model.mapper.RequestMapper;

@Service
public class RequestServiceImpl implements RequestService{


	@Autowired
	RequestMapper requestMapper;	

	@Override
	public List<Map<String, Object>> SelectRequest(Map<?, ?> paramMap) throws Exception {
	
		// TODO Auto-generated method stub
		return requestMapper.SelectRequest(paramMap);
	}

	@Override
	public List<Map<String, Object>> ReqCodeSelect(String MainCategory) throws Exception {
	
		// TODO Auto-generated method stub
		return requestMapper.ReqCodeSelect(MainCategory);
	}
	
	@Override
	public Map<String, Object> getReqInfo(Map<?, ?> paramMap) throws Exception {
	
		// TODO Auto-generated method stub
		return requestMapper.getReqInfo(paramMap);
	}
	
	@Override
	public Map<String, Object> getNewReqNo(Map<?, ?> paramMap) throws Exception {
	
		// TODO Auto-generated method stub
		return requestMapper.getNewReqNo(paramMap);
	}


	
	@Override
	public int saveReq(Map<?, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return requestMapper.saveReq(paramMap);
	}
	

	@Override
	public int updateRequest(Map<?, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return requestMapper.updateRequest(paramMap);
	}

	@Override
	public int deleteRequest(Map<?, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return requestMapper.deleteRequest(paramMap);
	}

	@Override
	public List<Map<String, Object>> getItemList(Map<?, ?> paramMap) throws Exception {
	
		// TODO Auto-generated method stub
		return requestMapper.getItemList(paramMap);
	}
	
	@Override
	public List<Map<String, Object>> getReqItemList(Map<?, ?> paramMap) throws Exception {
	
		// TODO Auto-generated method stub
		return requestMapper.getReqItemList(paramMap);
	}
	

}
