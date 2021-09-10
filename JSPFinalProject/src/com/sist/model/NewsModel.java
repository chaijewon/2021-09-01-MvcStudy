package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.xml.*;
@Controller
public class NewsModel {
  @RequestMapping("news/news.do")
  public String news_news(HttpServletRequest request,HttpServletResponse response)
  {
	  // 검색어를 받는다 
	  try
	  {
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex){}
	  String fd=request.getParameter("fd");
	  if(fd==null)
		  fd="맛집";
	  // 실시간 뉴스를 읽는다 
	  NewsManager nm=new NewsManager();
	  List<Item> list=nm.newsFindData(fd);
	  request.setAttribute("list", list);
	  request.setAttribute("main_jsp", "../news/news.jsp");
	  return "../main/main.jsp";
  }
}
