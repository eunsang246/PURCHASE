package com.ngps.spring.myapp.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ngps.spring.myapp.service.UserService;
import com.ngps.spring.myapp.security.HashEncrypt;


@RestController
public class UserController {
	
	
	@Resource
	private UserService userService;
	

	
			
	@RequestMapping(value="cmd=viewbutton") // /home으로 변수가 넘어오면 아래 로직을 수행한다. 
	public ModelAndView HomeView(Map<String, Object> map) throws Exception{
		ModelAndView mav = new ModelAndView();
		
		List<Map<String, Object>> AllList = userService.SelectAllList();   
		
		System.out.println(AllList);			
		
		mav.addObject("Alllist", AllList);
		mav.setViewName("myapp/sample/button"); //myapp 폴더의 user.html로 화면을 띄우겠다. 
		return mav;
	}
	
	@RequestMapping(value="cmd=viewhouse") // /home으로 변수가 넘어오면 아래 로직을 수행한다. 
	public ModelAndView HouseView(Map<String, Object> map) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		List<Map<String, Object>> AllList = userService.SelectAllList2();
   
		System.out.println("Execute Result ↓↓↓↓↓");
		System.out.println(AllList);	
		
		
		mav.addObject("Alllist", AllList);
		mav.setViewName("myapp/adminUser/user"); //user.html로 화면을 띄우겠다. 
		return mav;
	}
	
	
	//비밀번호 검증로직 
	@RequestMapping(value="cmd=chkPwd")
	public boolean PwdCheck() throws Exception{
		
		HashEncrypt sha256 = new HashEncrypt();
        
        //비밀번호
        String password = "hi12345678";
        //SHA256으로 암호화된 비밀번호
        String cryptogram = sha256.encrypt(password);
        
        System.out.println(cryptogram);
        
        //비밀번호 일치 여부. 일치하면 true값 리턴 
        System.out.println(cryptogram.equals(sha256.encrypt(password)));
        
		return cryptogram.equals(sha256.encrypt(password));
	}

	
	
}
