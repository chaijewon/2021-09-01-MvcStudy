package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
// 메소드 유형 => 매개변수 (request,response)
@Controller
public class MainModel {
   @RequestMapping("main/main.do")
   public String main_main(HttpServletRequest request,HttpServletResponse response)
   {
	   // main_jsp에 채우는 jsp파일명을 지정 
	   request.setAttribute("main_jsp", "../main/default.jsp");
	   return "../main/main.jsp";
   }
}
