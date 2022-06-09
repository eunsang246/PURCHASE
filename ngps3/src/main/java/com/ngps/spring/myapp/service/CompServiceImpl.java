package com.ngps.spring.myapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngps.spring.myapp.model.mapper.CompMapper;

@Service
public class CompServiceImpl implements CompService{

	@Autowired
	CompMapper compMapper;
	
	@Override
	public List<Map<String, Object>> SelectCompList() throws Exception {
		
		return compMapper.SelectCompList();
	}

}
