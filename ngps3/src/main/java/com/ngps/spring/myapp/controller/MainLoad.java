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
public class MainLoad {
	
	
	@RequestMapping(value="/") // /변수가 없으면 Main 화면으로 이동한다. 		
	public ModelAndView MainView(HttpSession session, Map<String, Object> map) throws Exception{
	    
		
		
		session.setAttribute("ssnEnterCd","BACH" ); //차후 DB 에서
		session.setAttribute("ssnUserId","MASTER" ); //차후 DB 에서
		session.setAttribute("ssnAuth","ALL" ); //차후 DB 에서				
		
		ModelAndView mav = new ModelAndView();

		mav.setViewName("myapp/dashBoard/index"); //index.html로 화면을 띄우겠다. 
		return mav;
	}
	
}
