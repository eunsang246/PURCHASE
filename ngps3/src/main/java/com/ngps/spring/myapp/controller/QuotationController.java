package com.ngps.spring.myapp.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

//해시함수 인코딩을 위해 Import
import com.ngps.spring.myapp.security.HashEncrypt;
import com.ngps.spring.myapp.service.QuotationService;

//
import org.json.simple.*;
import org.json.simple.parser.JSONParser;


@RestController
public class QuotationController {
	
	
	@Resource
	private QuotationService quotationService;
	
	@RequestMapping(value="cmd=viewQuotationList") 
	public ModelAndView RequestListView(
		   HttpServletRequest req, HttpServletResponse res, 
		   @RequestParam Map<String, Object> paramMap) throws Exception{
	
		//paramMap.put("YMD", "20220413"); //파라미터 값 추가 
					 
		List<Map<String, Object>> SelectQuotation = quotationService.SelectQuotation(paramMap);   
		System.out.println("Quotation List");			
				
		ModelAndView mav = new ModelAndView();
		mav.addObject("SelectQuotation", SelectQuotation);
		mav.setViewName("myapp/quotation/quotationList"); 
		return mav;
	}

	
	@RequestMapping(value="cmd=viewQuoInsert") 
	public ModelAndView RequestInsertView(HttpServletRequest request,Map<String, Object> paramMap) throws Exception{
		

		System.out.println("--------- Req Insert Controll Start!  -----------");	
		System.out.println(request.getParameter("REQ_NO"));	
		System.out.println(paramMap.get("data"));	   
		
		String REQ_NO ="";
		ModelAndView mav = new ModelAndView();	
		//List<Map<String, Object>> ReqInfo = requestService.ReqInfo(REQ_NO); 	
		List<Map<String, Object>> QuoComList = quotationService.QuoComList(REQ_NO); 	
		//mav.addObject("ReqInfo", ReqInfo);
		mav.addObject("QuoComList", QuoComList);
        mav.setViewName("myapp/request/requestInsert");        
		return mav;
	}
	
	
	
	@RequestMapping(value="cmd=popSelectCom") 
	public ModelAndView PoppopSelectCom(Map<String, Object> paramMap) throws Exception{
		
		ModelAndView mav = new ModelAndView();		
			
		List<Map<String, Object>> CompanyList = quotationService.CompanyList(paramMap); 
		mav.addObject("CompanyList", CompanyList);
        mav.setViewName("myapp/request/popSelectCom"); //user.html로 화면을 띄우겠다. 
        
		return mav;
	}


	@RequestMapping(value="cmd=saveQuotation")
	public void saveRequest(HttpServletRequest req, HttpServletResponse res, 
			 @RequestParam Map<String, Object> paramMap) { 
     try {			   
			   System.out.println("---------  saveRequest Start!  -----------");	
			   System.out.println(paramMap);	    
			  
               int InsertReq = quotationService.InsertRequest(paramMap);      
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
	
	
	
	
	@RequestMapping(value="cmd=saveQuoCompany")
	public void saveReqCompany(HttpServletRequest req, HttpServletResponse res, 
			@RequestParam Map<String, Object> paramMap) { 
		     System.out.println("---------  saveReqCompany Start!  -----------");	
          try {	
        	   System.out.println(paramMap);	
        	   System.out.println(paramMap.get("CC_CD"));
        	  //requestService.saveReqCompany(QUO_CC_CD);
        	
          
          }catch (Exception e) {
		       System.out.println("saveReqCompany Controller error");
		       System.out.println(e);
		  }
     
               System.out.println("---------  saveReqCompany End!  -----------");	
		}
	
}
