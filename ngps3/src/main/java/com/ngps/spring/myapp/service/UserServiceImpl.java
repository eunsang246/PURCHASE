package com.ngps.spring.myapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngps.spring.myapp.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	
	@Override
	public List<Map<String, Object>> SelectAllList() throws Exception {
		// TODO Auto-generated method stub
		return userMapper.SelectAllList();
	}

	@Override
	public List<Map<String, Object>> SelectAllList2() throws Exception {
		// TODO Auto-generated method stub
		return userMapper.SelectAllList2();
	}
}
