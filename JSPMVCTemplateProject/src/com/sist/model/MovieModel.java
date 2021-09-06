package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
@Controller
public class MovieModel {
   @RequestMapping("movie/detail.do")
   public String movie_detail(HttpServletRequest request,HttpServletResponse response)
   {
	   // 사용자가 보내준 데이터를 받는다(영화번호)
	   String mno=request.getParameter("mno");
	   // DAO연결 
	   MovieDAO dao=new MovieDAO();
	   String key=dao.moviedDetailData(Integer.parseInt(mno));
	   request.setAttribute("key", key);
	   // include 
	   request.setAttribute("main_jsp", "../movie/detail.jsp");
	   return "../main/main.jsp";
   }
}
