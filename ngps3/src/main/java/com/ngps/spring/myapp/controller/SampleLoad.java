package com.ngps.spring.myapp.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class SampleLoad {
	
		
	@RequestMapping(value="cmd=viewcolor")
	public ModelAndView ColorView(Map<String, Object> map) throws Exception{
	
		ModelAndView mav = new ModelAndView();

		mav.setViewName("myapp/sample/utilities-color"); 
		return mav;
	}	
	
	@RequestMapping(value="cmd=viewborder")
	public ModelAndView BorderView(Map<String, Object> map) throws Exception{
	
		ModelAndView mav = new ModelAndView();

		mav.setViewName("myapp/sample/utilities-border"); 
		return mav;
	}
	
	@RequestMapping(value="cmd=viewother")
	public ModelAndView OtherView(Map<String, Object> map) throws Exception{
	
		ModelAndView mav = new ModelAndView();

		mav.setViewName("myapp/sample/utilities-other"); 
		return mav;
	}
	
	@RequestMapping(value="cmd=viewanimation")
	public ModelAndView AnimationView(Map<String, Object> map) throws Exception{
	
		ModelAndView mav = new ModelAndView();

		mav.setViewName("myapp/sample/utilities-animation"); 
		return mav;
	}
	
	
	@RequestMapping(value="cmd=viewcharts")
	public ModelAndView ChartsView(Map<String, Object> map) throws Exception{
	
		ModelAndView mav = new ModelAndView();

		mav.setViewName("myapp/sample/charts"); 
		return mav;
	}
	
	
	@RequestMapping(value="cmd=viewcards")
	public ModelAndView CardsView(Map<String, Object> map) throws Exception{
	
		ModelAndView mav = new ModelAndView();

		mav.setViewName("myapp/sample/cards"); 
		return mav;
	}
	
	
	@RequestMapping(value="cmd=viewtable")
	public ModelAndView TableView(Map<String, Object> map) throws Exception{
	
		ModelAndView mav = new ModelAndView();

		mav.setViewName("myapp/sample/tables"); 
		return mav;
	}

	
	
	
	


	
	
}
