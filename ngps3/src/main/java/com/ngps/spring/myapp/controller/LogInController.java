package com.ngps.spring.myapp.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class LogInController {
	
	
	@RequestMapping(value="cmd=Login") // /변수가 없으면 Main 화면으로 이동한다. 		
	public ModelAndView Login(HttpSession session, 
			                  Map<String, Object> paramMap) throws Exception{
		
		ModelAndView mav = new ModelAndView();
	    
	    /*
		paramMap.put("StringID",paramMap.get("ID"));
		paramMap.put("StringPW",paramMap.get("PASSWORD"));

 		Map<String,String> loginUserMap = (Map<String, String>) loginService.loginUser(paramMap);
 		
 		if(로그인 정보가 일치할 때){ 		   
 		   //세션 데이터 세팅하고 메인 화면 출력 
 		   paramMap.put("ssnSabun", loginUserMap.get("ssnSabun"));
		   paramMap.put("ssnEnterCd", loginUserMap.get("ssnEnterCd"));
		   paramMap.put("ssnLocaleCd",	session.getAttribute("ssnLocaleCd"));
		   
		   mav.setViewName("myapp/dashBoard/index"); 
		   return mav;
 		}else if(비밀번호가 틀렸을 떄){
 		   //메세지 출력하고 로그인 화면으로 리턴 
 		  mav.setViewName("myapp/dashBoard/login"); 
		  return mav;
 		}
 		*/
 		   
 		  mav.setViewName("/////"); 
		  return mav;
	
    }
}
