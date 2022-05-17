package com.ngps.spring.myapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngps.spring.myapp.model.mapper.OpenApiMapper;

@Service
public class OpenApiServiceImpl implements OpenApiService{


	@Autowired
	OpenApiMapper openApiMapper;	

	@Override
	public List<Map<String, Object>> OilPriceList(Map<?, ?> paramMap) throws Exception {
	
		// TODO Auto-generated method stub
		return openApiMapper.OilPriceList(paramMap);
	}

	@Override
	public int InsertOilApi(Map<?, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Insert Oil Service Impl");
		return openApiMapper.InsertOilApi(paramMap);
	}

	@Override
	public int UpdateOilApi(Map<?, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return openApiMapper.UpdateOilApi(paramMap);
	}

	@Override
	public int DeleteOilApi(Map<?, ?> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return openApiMapper.DeleteOilApi(paramMap);
	}

	
}
