package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.manager.*;
// 실시간 뉴스 
@Controller
public class NewsModel {
  @RequestMapping("news/search.do")
  public String news_search(HttpServletRequest request,HttpServletResponse response)
  {
	  // 한글 변환 코드 (tomcat 10버전)  => 8.5
	  try
	  {
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex) {}
	  //사용자 보내준 검색어를 받는다 
	  String fd=request.getParameter("fd");
	  // default 
	  if(fd==null)
		  fd="맛집";
	  NewsManager mgr=new NewsManager();
	  List<Item> list=mgr.newsSearch(fd);
	  request.setAttribute("list", list);//jsp에 출력할 내용 전송 (여러개가 있을 수 있다)
	  // model => jsp (X) 
	  // model =====> request ====> DispatcherServlet ======= request =====> JSP
	  // dispatcher => 배달부 (request를 JSP로 배달) => Controller(서빙)
	  request.setAttribute("main_jsp", "../news/search.jsp"); // 데이터 출력 
	  // 예외 ==> Ajax => 단독 (해당 JSP출력)
	  return "../main/main.jsp";// include
  }
}
