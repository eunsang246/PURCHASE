package com.ngps.spring.myapp.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ngps.spring.myapp.service.CompService;
import com.ngps.spring.myapp.security.HashEncrypt;


@RestController
public class CompController {
	
	
	@Resource
	private CompService compService;
	
			
	@RequestMapping(value="cmd=viewCompList") // /home으로 변수가 넘어오면 아래 로직을 수행한다. 
	public ModelAndView CompListView(Map<String, Object> map) throws Exception{
		ModelAndView mav = new ModelAndView();
		
		List<Map<String, Object>> CompList = compService.SelectCompList();   	
	
		
		mav.addObject("CompList", CompList);
		mav.setViewName("myapp/adminComp/compList"); //myapp 폴더의 user.html로 화면을 띄우겠다. 
		return mav;
	}
	

	

	
	
}
