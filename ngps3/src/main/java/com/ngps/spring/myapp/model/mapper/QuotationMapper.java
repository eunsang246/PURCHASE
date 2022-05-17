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
	
    int InsertRequest(Map<?, ?> paramMap) throws Exception;
    
    int UpdateRequest(Map<?, ?> paramMap) throws Exception;
    
    int DeleteRequest(Map<?, ?> paramMap) throws Exception;
    
    int saveReqCompany(Map<?, ?> paramMap) throws Exception;

    List<Map<String, Object>> SelectQuotation(Map<?, ?> paramMap);
    
    List<Map<String, Object>> ReqCodeSelect(Map<?, ?> paramMap);
    
    List<Map<String, Object>> ReqInfo(String REQ_NO);
    
    List<Map<String, Object>> QuoComList(String REQ_NO);

    List<Map<String, Object>> CompanyList(Map<?, ?> paramMap);
    
   
}
