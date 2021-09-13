package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.xml.SeoulWeatherManager;
import com.sist.dao.*;
import com.sist.vo.*;
// NewsModel , MemberModel , ReserveModel , RecommandModel , PageModel(mypage,adminpage)
// BoardModel , ReplyModel , NoticeModel
// 자바 => 재사용 
@Controller
public class SeoulModel {
	   @RequestMapping("seoul/seoul_main.do")
	   public String seoul_main(HttpServletRequest request,HttpServletResponse response)
	   {
		   SeoulDAO dao=SeoulDAO.newInstance();
		   SeoulHotelVO hvo=dao.seoulHotelMainData();
		   SeoulLocationVO lvo=dao.seoulLoactionMainData();
		   SeoulNatureVO nvo=dao.seoulNatureMainData();
		   request.setAttribute("hvo", hvo);
		   request.setAttribute("lvo", lvo);
		   request.setAttribute("nvo", nvo);
		   request.setAttribute("main_jsp", "../seoul/seoul_main.jsp");
		   return "../main/main.jsp";
	   }
	   @RequestMapping("seoul/seoul_weather.do")
	   public String seoul_weather(HttpServletRequest request,HttpServletResponse response)
	   {
		   SeoulWeatherManager sm=new SeoulWeatherManager();
		   String data=sm.seoulWeather();
		   request.setAttribute("data", data);
		   request.setAttribute("main_jsp", "../seoul/seoul_weather.jsp");
		   return "../main/main.jsp";
	   }
}





