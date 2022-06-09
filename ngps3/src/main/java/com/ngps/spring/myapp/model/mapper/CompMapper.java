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
public interface CompMapper {
	

	public List<Map<String, Object>> SelectCompList() throws Exception;
	
}
