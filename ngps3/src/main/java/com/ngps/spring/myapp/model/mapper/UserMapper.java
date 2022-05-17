/**
 * 
 */
package com.ngps.spring.myapp.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author CES
 *
 */
@Mapper
public interface UserMapper {
	
	//select * from User_Table
	public List<Map<String, Object>> SelectAllList() throws Exception;
	
	//select * from User2_Table
	public List<Map<String, Object>> SelectAllList2() throws Exception;

}
