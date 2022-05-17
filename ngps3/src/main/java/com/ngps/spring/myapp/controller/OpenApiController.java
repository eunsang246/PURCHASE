package com.ngps.spring.myapp.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//XML 파싱에 사용되는 정보  
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//Service 구문 사용을 위한 Import
import com.ngps.spring.myapp.service.OpenApiService;



@RestController
public class OpenApiController {
	

	@Resource
	private OpenApiService openApiService;
	
	
    /* XML 파싱에 사용되는 변수.tag값의 정보를 가져오는 함수*/
	public static String getTagValue(String tag, Element eElement) {
       	//결과를 저장할 result 변수 선언
      	String result = "";
     	NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	   	result = nlList.item(0).getTextContent();
	   	return result;
	}
	
	 
	@RequestMapping(value="cmd=viewOilPrice")
	public ModelAndView OilPriceView(
		   HttpServletRequest req, HttpServletResponse res, 
		   @RequestParam Map<String, Object> paramMap) throws Exception{
	
		//paramMap.put("YMD", "20220413"); //파라미터 값 추가 
						 
		List<Map<String, Object>> OilPriceList = openApiService.OilPriceList(paramMap);   
		System.out.println("OilPrice");			
				
		ModelAndView mav = new ModelAndView();
		mav.addObject("OilPriceList", OilPriceList);
		mav.setViewName("myapp/openApi/oilApi"); //oilApi.html로 화면을 띄우겠다. 
		return mav;
	}
	

	
	 //Oil API는 Opinet에서 API 무료 키를 줘야 구현이 가능.일단 Holding 
	@RequestMapping(value="cmd=callapi_oil", method = {RequestMethod.GET,RequestMethod.POST})
	public String oilApi() {
	    //가변데이터를 다루는 경우 Stringbuffer를 선언하여 사용한다. 	
		StringBuffer result =  new StringBuffer();
		
		System.out.println("OilPrice Call Start");
		
		try {
			
			  System.out.println("OilPrice Call Try");
	          //유종별 전국주유소 평균가격 가져오기 
			   String urlstr = "http://www.opinet.co.kr/api/avgAllPrice.do";	
	              
			   /*----------------Open API Call 호출 Start----------------*/
	           StringBuilder urlBuilder = new StringBuilder(urlstr); /*URL*/
		       urlBuilder.append("?" + URLEncoder.encode("out","UTF-8") + "=xml"); /*Service Key*/
		       urlBuilder.append("&" + URLEncoder.encode("code","UTF-8") + "=F220411104"); /*한 페이지 결과 수*/
		   
		       URL url = new URL(urlBuilder.toString());
		       HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		       conn.setRequestMethod("GET");
		       conn.setRequestProperty("Content-type", "application/json");
		       System.out.println("Response code: " + conn.getResponseCode());
		     
		        BufferedReader rd;		     
		        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        } else {
		            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		        }
		      
		        StringBuilder sb = new StringBuilder();
		        String line;
		        while ((line = rd.readLine()) != null) {
		            sb.append(line);
		        }
		        rd.close();
		        conn.disconnect();
		        System.out.println(sb.toString()); 	    
		        
		        /*----------------Open API Call 호출 END----------------*/		        
		        
                /*---------------XML 파싱 Start---------------*/
		        DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(urlBuilder.toString());
				
				// 제일 첫번째 태그
				doc.getDocumentElement().normalize();				
				// 파싱할 tag
				NodeList nList = doc.getElementsByTagName("OIL");
				
				for(int temp = 0; temp < nList.getLength(); temp++){		        
					Node nNode = nList.item(temp);
					Element eElement = (Element) nNode;					
					System.out.println("일자 : " + getTagValue("TRADE_DT", eElement));
					System.out.println("Oil코드 : " + getTagValue("PRODCD", eElement));
					System.out.println("Oil명 : " + getTagValue("PRODNM", eElement));
					System.out.println("가격 : " + getTagValue("PRICE", eElement));
					System.out.println("변동율 : " + getTagValue("DIFF", eElement));			
					
					
					System.out.println("---------------DB Insert Start---------------");	
					
					HashMap<String, String> paramMap = new HashMap<String,String>();

					paramMap.put("OILIDX","T111");// 0
					paramMap.put("YMD",getTagValue("TRADE_DT", eElement));// 1
					paramMap.put("OILCODE",getTagValue("PRODCD", eElement));// 2
					paramMap.put("OILNAME",getTagValue("PRODNM", eElement));
					paramMap.put("PRICE",getTagValue("PRICE", eElement));
					paramMap.put("DIFF",getTagValue("DIFF", eElement));			   
					System.out.println("Insert Oil Api Strat");
					openApiService.InsertOilApi(paramMap);
			
					System.out.println("---------------DB Insert END---------------");
				}		
				/*---------------XML 파싱 END---------------*/
		}
		catch(Exception e){
			System.out.println("OpenApi Call Catch");
			
			e.printStackTrace();
		}
		return result.toString();
	}	
	
	


	//조달청 OpenAPI 나라정터 입찰공고 서비스
	@RequestMapping(value="cmd=callapi_bidprice", method = {RequestMethod.GET,RequestMethod.POST})
	public void bidPrice() {
		 
		System.out.println("bidPrice OpenApi Call Start");
			
		try {				
			 System.out.println("bidPrice OpenApi Try Start");
				
		     StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1230000/BidPublicInfoService02/getBidPblancListInfoCnstwk"); /*URL*/
		     urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=pDym7C3xmuXQxd1hNt3rVVABjzFoZmdEwcthtEt5FrXjqVH2aGSVvtQ6XpAEoCEGJTK5yolDfvAMKtn5cGXDLA%3D%3D"); /*Service Key*/
		     urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
		     urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
		     urlBuilder.append("&" + URLEncoder.encode("inqryDiv","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*검색하고자하는 조회구분 1.등록일시, 2.입찰공고번호 3.변경일시 (등록일시로 검색시 방위사업청 입찰공고데이터의 등록일시는 방위사업청에서 제공하는 오픈API로 연계된 입찰공고정보가 조*/
		     urlBuilder.append("&" + URLEncoder.encode("inqryBgnDt","UTF-8") + "=" + URLEncoder.encode("201605010000", "UTF-8")); /*검색하고자하는 등록일시 또는 변경일시 조회시작일시 */
		     urlBuilder.append("&" + URLEncoder.encode("inqryEndDt","UTF-8") + "=" + URLEncoder.encode("201605052359", "UTF-8")); /*검색하고자하는 등록일시 또는 변경일시 조회종료일시 */
		     urlBuilder.append("&" + URLEncoder.encode("bidNtceNo","UTF-8") + "=" + URLEncoder.encode("20160430911", "UTF-8")); /*검색하고자 하는 입찰공고번호 (조회구분 '2' 선택시 필수)*/
		     urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*오픈API 리턴 타입을 JSON으로 받고 싶을 경우 'json' 으로 지정*/
		    
		     URL url = new URL(urlBuilder.toString());
		     HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		     conn.setRequestMethod("GET");
		     conn.setRequestProperty("Content-type", "application/json");
		     System.out.println("Response code: " + conn.getResponseCode());
		     
		      BufferedReader rd;		     
		      if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		          rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		      } else {
		          rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		      }
		      
		      StringBuilder sb = new StringBuilder();
		        String line;
		        while ((line = rd.readLine()) != null) {
		            sb.append(line);
		        }
		        rd.close();
		        conn.disconnect();
		        System.out.println(sb.toString());
		        
			}
			catch(Exception e){
				System.out.println("BidPrice OpenAPI Call Catch");
				
				e.printStackTrace();
			}
			
	}
	

	//서부발전 실적/계획 정보 서비스
	@RequestMapping(value="cmd=callapi_west", method = {RequestMethod.GET,RequestMethod.POST})
	public void west() {
		
		 try {
		   StringBuilder urlBuilder = new StringBuilder("http://www.iwest.co.kr:8082/openapi-data/service/Develop/Development"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=pDym7C3xmuXQxd1hNt3rVVABjzFoZmdEwcthtEt5FrXjqVH2aGSVvtQ6XpAEoCEGJTK5yolDfvAMKtn5cGXDLA%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("strOrgCd","UTF-8") + "=" + URLEncoder.encode("TA", "UTF-8")); /*발전소코드번호*/
	        urlBuilder.append("&" + URLEncoder.encode("strHoki","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*발전소 위치번호*/
	        urlBuilder.append("&" + URLEncoder.encode("strDateS","UTF-8") + "=" + URLEncoder.encode("2020", "UTF-8")); /*조회년도*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        System.out.println(sb.toString());
		 }
		 catch(Exception e){
	        System.out.println("WEST OpenAPI Call Catch");
			e.printStackTrace();
		 }
		
	}
	
}
