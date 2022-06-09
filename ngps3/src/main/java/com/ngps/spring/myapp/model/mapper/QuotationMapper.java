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
public interface QuotationMapper {
	
    int saveQuo(Map<?, ?> paramMap) throws Exception;
    
    int updateRequest(Map<?, ?> paramMap) throws Exception;
    
    int deleteRequest(Map<?, ?> paramMap) throws Exception;

    List<Map<String, Object>> SelectQuotation(Map<?, ?> paramMap);
    
    Map<String, Object> getQuoInfo(Map<?, ?> paramMap);
    
    Map<String, Object> getNewQuoNo(Map<?, ?> paramMap);
  
    List<Map<String, Object>> getReqItemList(Map<?, ?> paramMap);
    
    List<Map<String, Object>> getCompList(Map<?, ?> paramMap);
    
    List<Map<String, Object>> getQuoCompList(Map<?, ?> paramMap);
    
    
   
}
