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

import net.sf.json.*;

@RestController
public class RequestController<paramMap> {	
	
	@Resource
	private RequestService requestService;
	
	/*
	 Request List 화면 출력  
	*/	
	@RequestMapping(value="cmd=viewRequestList") 
	public ModelAndView RequestListView(HttpSession session,
		   HttpServletRequest req, HttpServletResponse res, 
		   @RequestParam Map<String, Object> paramMap) throws Exception{
		
		
		paramMap.put("ssnEnterCd", session.getAttribute("ssnEnterCd"));
				
		//paramMap.put("YMD", "20220413"); //파라미터 값 추가 
					 
		List<Map<String, Object>> SelectRequest = requestService.SelectRequest(paramMap);   
			
				
		ModelAndView mav = new ModelAndView();
		mav.addObject("SelectRequest", SelectRequest);
		mav.setViewName("myapp/request/requestList"); 
		return mav;
	}

	/*
	 Request 입력 팝업 화면 출력 
	*/	
	@RequestMapping(value="cmd=viewReqInsert") 
	public ModelAndView RequestInsertView(HttpSession session,
		    HttpServletRequest req, HttpServletResponse res, 
			@RequestParam Map<String, Object> paramMap) throws Exception{	

		ModelAndView mav = new ModelAndView();	
		Map<String, Object> ReqInfo =null;
		String Message = "";
		
	    /*기존 REQ NO를 조회하는 경우 파라미터 내에 REQ_NO 값을 체크한다.*/
		if((String)paramMap.get("REQ_NO")!= null) { 
	         System.out.println(paramMap);
  	         try{
	    	     ReqInfo = requestService.getReqInfo(paramMap);   //REQ_NO로 기존 신청정보 가져오기. 
	    	     List<Map<String, Object>> ReqItemList = requestService.getReqItemList(paramMap); //ItemList 가져오기 
	    	     mav.addObject("ReqItemList", ReqItemList);
	    	     mav.addObject("ReqInfo", ReqInfo);
	     	   }catch(Exception e){
	 	    	 System.out.println(e);
		       };
		 }else {
			     Message ="NEW";			
			     ReqInfo = requestService.getNewReqNo(paramMap);   //새로운 Requisition No 생성 
			     mav.addObject("ReqInfo", ReqInfo);
			     System.out.println("New Req Insert");			
		 }
		
		mav.addObject("Message", Message);	
	    mav.setViewName("myapp/request/requestInsert");        
		return mav;
	}

	/*
	  Category ComboBox 출력 
	*/	
	@RequestMapping(value="cmd=SubSelect")
	public void ReqCodeSelect(HttpServletRequest req, HttpServletResponse res,  String param){
          try {			                   
			    //데이터 단건일 경우 List 형태로 가져온다. 
			    List<Map<String, Object>> CodeList = requestService.ReqCodeSelect(param);           
		        System.out.println(CodeList.get(0));
		        
		        JSONArray jsonArray = new JSONArray();
		   		for (int i = 0; i < CodeList.size(); i++) {
		   			jsonArray.add(CodeList.get(i));
		   		}		 		   		
		   		// jsonArray 넘김
		   		PrintWriter pw = res.getWriter();
		   		pw.print(jsonArray.toString());
		   		pw.flush();
		   		pw.close();
		 
		   } catch (Exception e) {
		       System.out.println(e);
		   }     
		}
	
	
	/*
	 Item 선택을 위한 팝업창 출력  
	*/	
	@RequestMapping(value="cmd=popSelectItem") 
	public ModelAndView popSelectItem( HttpSession session ,
			 HttpServletRequest req, HttpServletResponse res, 
			@RequestParam Map<String, Object> paramMap) throws Exception{
		
		ModelAndView mav = new ModelAndView();	

		List<Map<String, Object>> ItemList = requestService.getItemList(paramMap); 
		//mav.addObject("REQ_NO",paramMap.get("REQ_NO") );
		mav.addObject("ItemList", ItemList);
        mav.setViewName("myapp/request/popSelectItem"); 
        
		return mav;
	}
	
	/*
	 Request Save
	*/	
	@RequestMapping(value="cmd=saveReq")
	public void saveReqItem(HttpSession session, 
			 HttpServletRequest req, HttpServletResponse res, 
			 @RequestParam Map<String, Object> paramMap) { 
           try {			   
 		     	System.out.println("-- Save Request Start --");		
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
			   
	          	requestService.saveReq(paramMap);
			    
			    System.out.println("-- Save Request End --");
		   } catch (Exception e) {
		        System.out.println(e);
		   }
		}
	
	
	
}
