package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.vo.*;
import com.sist.dao.*;
@Controller
public class NoticeModel {
  @RequestMapping("notice/list.do")
  public String notice_list(HttpServletRequest request,HttpServletResponse response)
  {
	  // 사용자가 보내준 데이터를 받는다 (page)
	  String page=request.getParameter("page");
	  if(page==null)// 첫페이지 
	  {
		  page="1";//default
	  }
	  // 현재 페이지 
	  int curpage=Integer.parseInt(page);
	  // 해당 페이지의 데이터를 받는다 
	  NoticeDAO dao=NoticeDAO.newInstance();
	  List<NoticeVO> list=dao.noticeListData(curpage);
	  // 총페이지 
	  int totalpage=dao.noticeTotalPage();
	  
	  // JSP에 출력하기 위해 데이터를 보내준다 
	  request.setAttribute("curpage", curpage);
	  request.setAttribute("totalpage", totalpage);
	  request.setAttribute("list", list);
	  request.setAttribute("main_jsp", "../notice/list.jsp");
	  /*
	   *   ========================
	   *     header.jsp (Menu)
	   *   ========================
	   *      상시 변경  request.setAttribute("main_jsp", "../notice/list.jsp");
	   *   ========================
	   *      footer.jsp
	   *   ========================
	   *   
	   *    
			<%@ include file="../main/header.jsp" %>
			
			<jsp:include page="../notice/list.jsp"></jsp:include>
			                  
			<%@ include file="../main/footer.jsp" %>
	   */
	  return "../main/main.jsp";
  }
  // 공지상세보기 
  @RequestMapping("notice/detail.do")
  public String notice_detail(HttpServletRequest request,HttpServletResponse response)
  {
	  // 사용자 요청 데이터 받기
	  String no=request.getParameter("no");
	  // DAO로 전송 => 처리결과를 받아 온다 
	  NoticeDAO dao=NoticeDAO.newInstance();
	  // 처리결과 받아 온다
	  NoticeVO vo=dao.noticeDetailData(Integer.parseInt(no));
	  // request를 이용해서 detail.jsp로 전송 
	  request.setAttribute("vo", vo);
	  request.setAttribute("main_jsp", "../notice/detail.jsp");
	  return "../main/main.jsp";
  }
}






