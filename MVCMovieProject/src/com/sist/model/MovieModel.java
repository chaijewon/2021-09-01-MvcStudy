package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import java.util.*;
@Controller
public class MovieModel {
  /*
   *    list.jsp 
   *    <%
   *        String page=request.getParameter("page");
	        if(page==null)
		      page="1";
   *    %>
   */
  @RequestMapping("movie/list.do")
  public String movie_list(HttpServletRequest request,HttpServletResponse response)
  {
	  // 사용자가 보내준 데이터를 받는다 
	  String page=request.getParameter("page");
	  if(page==null)
		  page="1";
	  int curpage=Integer.parseInt(page);
	  MovieDAO dao=new MovieDAO();
	  List<MovieVO> list=dao.movieListData(curpage);
	  int totalpage=dao.movieTotalPage();
	  
	  request.setAttribute("curpage", curpage);//현재페이지
	  request.setAttribute("totalpage", totalpage);//총페이지
	  request.setAttribute("list", list);// 영화데이터 
	  return "../movie/list.jsp";// request를 받을 대상 => request를 받아서 출력만 하는 JSP(View)
  }
  // 처리=> 모델 (오라클 연결) ==> 처리후에 JSP로 출력할 내용을 보내준다 (여러개를 전송)
  // ======================DispatcherServlet===============================
  // 요청(a,button,menu) => DispatcherServlet ==> Model(요청 처리) ===> DAO 데이터 읽기 
  // DAO => Model => DispatcherServlet ==> JSP
  // DispatcherServlet은 고정 (라이브러리)
  // Model ,DAO,VO , JSP ==> Spring
  @RequestMapping("movie/detail.do")
  public String movie_detail(HttpServletRequest request,HttpServletResponse response)
  {
	  // 영화번호 ==> DAO에 전송 ==> 처리후에 MovieVO를 넘겨준다 => JSP전송 (request.setAttribute())
	  String mno=request.getParameter("mno");
	  MovieDAO dao=new MovieDAO();
	  // MovieVO 를 받는다 
	  MovieVO vo=dao.movieDetailData(Integer.parseInt(mno));
	  // request에 값을 담아준다 
	  request.setAttribute("vo", vo);
	  return "../movie/detail.jsp";
  }
}




