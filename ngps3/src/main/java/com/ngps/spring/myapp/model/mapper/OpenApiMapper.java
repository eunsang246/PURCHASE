/**
 * 
 */
package com.ngps.spring.myapp.model.mapper;

import java.util.List;
import java.util.Map;
import java.util.HashMap;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OpenApiMapper {
	
	
    int InsertOilApi(Map<?, ?> paramMap) throws Exception;
    
    int UpdateOilApi(Map<?, ?> paramMap) throws Exception;
    
    int DeleteOilApi(Map<?, ?> paramMap) throws Exception;

    List<Map<String, Object>> OilPriceList(Map<?, ?> paramMap);
    
    
}