package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class MainModel {
  @RequestMapping("main/main.do")
  public String main_main(HttpServletRequest request,HttpServletResponse response)
  {
	  // include파일 전송 
	  request.setAttribute("main_jsp", "../food/food_main.jsp");
	  return "../main/main.jsp";
  }
}
