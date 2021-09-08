package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class MovieModel {
	   @RequestMapping("movie/movie_main.do")
	   public String movie_main(HttpServletRequest request,HttpServletResponse response)
	   {
		   request.setAttribute("main_jsp", "../movie/movie_main.jsp");
		   return "../main/main.jsp";
	   }
}
