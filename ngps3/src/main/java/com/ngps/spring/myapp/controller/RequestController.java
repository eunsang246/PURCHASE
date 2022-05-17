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

//해시함수 인코딩을 위해 Import
import com.ngps.spring.myapp.security.HashEncrypt;
import com.ngps.spring.myapp.service.RequestService;

import net.sf.json.*;

@RestController
public class RequestController<paramMap> {	
	
	@Resource
	private RequestService requestService;
	
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
	    	 //List<Map<String, Object>> ReqItemList = requestService.ItemList(REQ_NO); //REQ_NO 가져오기 
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
	
	

	@RequestMapping(value="cmd=popSelectItem") 
	public ModelAndView popSelectItem( HttpSession session ,
			 HttpServletRequest req, HttpServletResponse res, 
			@RequestParam Map<String, Object> paramMap) throws Exception{
		
		
		ModelAndView mav = new ModelAndView();	
		System.out.println(paramMap);			
		System.out.println(paramMap.get("REQ_NO"));
					
		List<Map<String, Object>> ItemList = requestService.ItemList(paramMap); 
		mav.addObject("REQ_NO",paramMap.get("REQ_NO") );
		mav.addObject("ItemList", ItemList);
        mav.setViewName("myapp/request/popSelectItem"); 
        
		return mav;
	}
	
	
	@RequestMapping(value="cmd=saveReqItem")
	public void saveReqItem(HttpSession session, 
			 HttpServletRequest req, HttpServletResponse res, 
			 @RequestParam Map<String, Object> paramMap) { 
           try {			   
 		     	System.out.println("********************************************111111");		
			    System.out.println(paramMap);	  			    
			    		    
			    String paramStr = paramMap.get("jsonData").toString();
			    JSONArray arr = JSONArray.fromObject(paramStr);
			    System.out.println(arr.size());
			    
			    List<Map<String, Object>> resendList = new ArrayList<Map<String, Object>>();
			    for(int i=0; i<arr.size(); i++){
			           
			        JSONObject obj = (JSONObject)arr.get(i);
			        Map<String, Object> resendMap = new HashMap<String, Object>();
			        resendMap.put("ENTER_CD", obj.get("ENTER_CD"));
			        resendMap.put("ITEM_CD", obj.get("ITEM_CD"));
			        resendMap.put("REQ_NO", obj.get("REQ_NO"));			            
			        resendList.add(resendMap);
			  }
			    paramMap.put("i_arrStr", resendList);
	          	requestService.saveReqItem(paramMap);
			    
			    System.out.println("********************************************222222");
		   } catch (Exception e) {
		        System.out.println(e);
		   }
		}
	
	


	@RequestMapping(value="cmd=saveRequest")
	public void saveRequest(HttpSession session, 
			 HttpServletRequest req, HttpServletResponse res, 
			 @RequestParam Map<String, Object> paramMap) { 
     try {			   
 			System.out.println(session.getAttribute("ssnEnterCd"));			
 			paramMap.put("ssnEnterCd", session.getAttribute("ssnEnterCd"));
 			
    	       
    	       System.out.println("---------  saveRequest Start!  -----------");	
			   System.out.println(paramMap);	    
			  
               int InsertReq = requestService.InsertRequest(paramMap);      
	           System.out.println(InsertReq);		     
		    	
				/*---------------- 해시함수 Encoding Start ----------------*/
				HashEncrypt hashEncrypt =  new HashEncrypt();
				String EncryptResult = hashEncrypt.encrypt("20220413");	
				
				System.out.println(EncryptResult);	

			    System.out.println(EncryptResult.equals(hashEncrypt.encrypt("20220413")));

				/*---------------- 해시함수 Encoding End ----------------*/		
		   } catch (Exception e) {
		        System.out.println("Controller error");
		   }
     
                System.out.println("---------  saveRequest End!  -----------");	
		}
	
	
	
	
	
}
