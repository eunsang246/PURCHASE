package com.ngps.spring.myapp.service;

import java.util.List;
import java.util.Map;

public interface UserService {
	 //CES
	//select * from Test_Table
	public List<Map<String, Object>> SelectAllList() throws Exception;
	
	//select * from Test_Table
	public List<Map<String, Object>> SelectAllList2() throws Exception;
}
