/**
 * 
 */
package com.ngps.spring.myapp.model.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author CES
 *
 */
@Mapper
public interface RequestMapper {
	
    int saveReq(Map<?, ?> paramMap) throws Exception;
    
    int updateRequest(Map<?, ?> paramMap) throws Exception;
    
    int deleteRequest(Map<?, ?> paramMap) throws Exception;

    List<Map<String, Object>> SelectRequest(Map<?, ?> paramMap);
    
    List<Map<String, Object>> ReqCodeSelect(String MainCategory);
    
    Map<String, Object> getReqInfo(Map<?, ?> paramMap);
    
    Map<String, Object> getNewReqNo(Map<?, ?> paramMap);
  
    List<Map<String, Object>> getItemList(Map<?, ?> paramMap);
    
    List<Map<String, Object>> getReqItemList(Map<?, ?> paramMap);
    
    
   
}
