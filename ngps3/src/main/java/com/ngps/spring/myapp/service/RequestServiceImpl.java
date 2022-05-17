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
	public int InsertRequest(Map<?, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return requestMapper.InsertRequest(paramMap);
	}

	@Override
	public int UpdateRequest(Map<?, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return requestMapper.UpdateRequest(paramMap);
	}

	@Override
	public int DeleteRequest(Map<?, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return requestMapper.DeleteRequest(paramMap);
	}

	@Override
	public List<Map<String, Object>> ItemList(Map<?, ?> paramMap) throws Exception {
	
		// TODO Auto-generated method stub
		return requestMapper.ItemList(paramMap);
	}
	
	@Override
	public int saveReqItem(Map<?, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return requestMapper.saveReqItem(paramMap);
	}
	
}
