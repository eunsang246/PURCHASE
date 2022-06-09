package com.ngps.spring.myapp.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ngps.spring.myapp.service.RequestService;
import com.ngps.spring.myapp.service.QuotationService;

import net.sf.json.*;

@RestController
public class QuotationController<paramMap> {	
	
	@Resource
	private RequestService requestService;
	@Resource
	private QuotationService quotationService;
	
	/*
	 Quotation List 화면 출력 
	 */
	@RequestMapping(value="cmd=viewQuotationList") 
	public ModelAndView QuotationListView(HttpSession session,
		   HttpServletRequest req, HttpServletResponse res, 
		   @RequestParam Map<String, Object> paramMap) throws Exception{
		
		paramMap.put("ssnEnterCd", session.getAttribute("ssnEnterCd"));
						 
		List<Map<String, Object>> SelectQuotation = quotationService.SelectQuotation(paramMap);   
			
		ModelAndView mav = new ModelAndView();
		mav.addObject("SelectQuotation", SelectQuotation);
		mav.setViewName("myapp/quotation/quotationList"); 
		return mav;
	}

	/*
	 Quotation 입력 화면 출력 
	 */
	@RequestMapping(value="cmd=viewQuoInsert") 
	public ModelAndView QuotationInsertView(HttpSession session,
		    HttpServletRequest req, HttpServletResponse res, 
			@RequestParam Map<String, Object> paramMap) throws Exception{	

		ModelAndView mav = new ModelAndView();	
		Map<String, Object> QuoInfo =null;
		String Message = "";
		String QUO_NO= (String)paramMap.get("QUO_NO");	
		
		List<Map<String, Object>> ReqItemList = requestService.getReqItemList(paramMap); //ItemList 가져오기 
		 mav.addObject("ReqItemList", ReqItemList);
		
        System.out.println("viewQuoInsert parameter : " + paramMap);
        
	    /*기존 REQ NO를 조회하는 경우 파라미터 내에 REQ_NO 값을 체크한다.*/
		if(!"Quotation Proceed".equals(QUO_NO)) { 
	           QuoInfo = quotationService.getQuoInfo(paramMap);//기존 Quotataion 정보 가져오기 	          
	           List<Map<String, Object>> QuoCompList = quotationService.getQuoCompList(paramMap); //ItemList 가져오기
	    	   mav.addObject("QuoCompList", QuoCompList);
	    	   mav.addObject("QuoInfo", QuoInfo);
		}else {
			   System.out.println("New Quo Insert");	  
			   Message ="NEW";		
		       QuoInfo = quotationService.getNewQuoNo(paramMap);   //새로운 Quotation No 생성 
		       mav.addObject("QuoInfo", QuoInfo);	     		
	    }
	  	
		mav.addObject("Message", Message);	
	    mav.setViewName("myapp/quotation/quotationInsert");        
		return mav;
	}
	
	/*
	 견적업체 선택을 위한 팝업
	 */
	@RequestMapping(value="cmd=popSelectComp") 
	public ModelAndView popSelectItem( HttpSession session ,
			 HttpServletRequest req, HttpServletResponse res, 
			@RequestParam Map<String, Object> paramMap) throws Exception{
		
		ModelAndView mav = new ModelAndView();	

		List<Map<String, Object>> CompList = quotationService.getCompList(paramMap); 
		mav.addObject("CompList", CompList);
        mav.setViewName("myapp/quotation/popSelectComp"); 
        
		return mav;
	}
	
	
	
	@RequestMapping(value="cmd=viewQuoResult") 
	public ModelAndView QuotationResultView(HttpSession session,
		    HttpServletRequest req, HttpServletResponse res, 
			@RequestParam Map<String, Object> paramMap) throws Exception{	

		ModelAndView mav = new ModelAndView();	
		Map<String, Object> QuoInfo =null;
		String Message = "";
		
		System.out.println(paramMap);
  	         try{
	           QuoInfo = quotationService.getQuoInfo(paramMap);   //REQ_NO로 기존 신청정보 가져오기. 
	           List<Map<String, Object>> ReqItemList = requestService.getReqItemList(paramMap); //ItemList 가져오기 
	           List<Map<String, Object>> QuoCompList = quotationService.getQuoCompList(paramMap); //ItemList 가져오기 		 	  
	    	   mav.addObject("ReqItemList", ReqItemList);
	    	   mav.addObject("QuoCompList", QuoCompList);
	    	   mav.addObject("QuoInfo", QuoInfo);
	         }catch(Exception e){
	 	    	 System.out.println(e);
		     };	
			  	
		mav.addObject("Message", Message);	
	    mav.setViewName("myapp/quotation/quotationResult");        
		return mav;
	}

	
	

	@RequestMapping(value="cmd=saveQuo")
	public void saveReqItem(HttpSession session, 
			 HttpServletRequest req, HttpServletResponse res, 
			 @RequestParam Map<String, Object> paramMap) { 
           try {			   
 		     	System.out.println("-- Save Quotation Start --");		
			    System.out.println(paramMap);	  			    
			    		    
			    String paramStr = paramMap.get("jsonData").toString();
			    JSONArray arr = JSONArray.fromObject(paramStr);
			    System.out.println(arr.size());
			    
			    List<Map<String, Object>> resendList = new ArrayList<Map<String, Object>>();
			    for(int i=0; i<arr.size(); i++){
			    	
			        JSONObject obj = (JSONObject)arr.get(i);
			    	Map<String, Object> resendMap = new HashMap<String, Object>();
			        resendMap.put("ENTER_CD", obj.get("ENTER_CD"));
			        resendMap.put("REQ_NO", obj.get("REQ_NO"));	
			        resendMap.put("ITEM_CD", obj.get("ITEM_CD"));
			        resendMap.put("ITEM_NM", obj.get("ITEM_NM"));		
			        resendMap.put("UNIT", obj.get("UNIT"));
			        resendMap.put("QTY", obj.get("QTY"));		
			        resendList.add(resendMap);
			    	
			        if(i==0) {
			    		//첫번째 배열에서 Master 성 데이터를 추출한
			    		paramMap.put("ENTER_CD", obj.get("ENTER_CD"));
			    		paramMap.put("REQ_NO", obj.get("REQ_NO"));
			    		paramMap.put("MAIN_CATEGORY", obj.get("MAIN_CATEGORY"));
			    		paramMap.put("SUB_CATEGORY", obj.get("SUB_CATEGORY"));
			    		paramMap.put("REQ_SDATE", obj.get("REQ_SDATE"));
			    		paramMap.put("REQ_EDATE", obj.get("REQ_EDATE"));
			    		paramMap.put("BIGO", obj.get("BIGO"));	    		
			    		
			    	}		
			    }
			    paramMap.put("i_arrStr", resendList);
			   
	          	quotationService.saveQuo(paramMap);
			    
			    System.out.println("-- Save Request End --");
		   } catch (Exception e) {
		        System.out.println(e);
		   }
		}
	
	
	
}
