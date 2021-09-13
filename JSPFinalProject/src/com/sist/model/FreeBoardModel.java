package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FreeBoardDAO;
import java.util.*;
import com.sist.vo.*;
@Controller 
// Model 클래스를 알려준다 (DispatcherServlet)
// 프로젝트 => 스프링 => DAO,일반 자바 , 모델 
//              DAO : @Repository , @Component @Controller
public class FreeBoardModel {
  @RequestMapping("freeboard/list.do")
  public String freeboard_list(HttpServletRequest request,HttpServletResponse response)
  {
	  // main으로 이동하지 않는 경우 (해당 JSP에서 결과값을 읽어 온다 : Ajax,다운로드) 
	  // 사용자가 보내주는 요청값 (페이지 번호)
	  String page=request.getParameter("page");
	  // 첫화면에서는 페이지를 선택할 수 없다 (디폴트)
	  /*
	   *    list.do?page= ==> page.equals("")
	   *    list.do       ==> page==null
	   *    list.do?page= 1 ==> page.eqauls("1")
	   */
	  if(page==null) // 첫페이지일 경우 
	  {
		  page="1";
	  }
	  // 해당페이지의 데이터를 가지고 온다(DAO)
	  FreeBoardDAO dao=FreeBoardDAO.newInstance();
	  int curpage=Integer.parseInt(page);
	  List<FreeBoardVO> list=dao.freeboardListData(curpage);
	  int totalpage=dao.freeboardTotalPage();
	  // list.jsp로 전송 
	  request.setAttribute("curpage", curpage);// 현재 페이지
	  request.setAttribute("totalpage", totalpage);
	  request.setAttribute("list", list);
	  // 화면 출력 => include 대상 
	  request.setAttribute("main_jsp", "../freeboard/list.jsp");
	  return "../main/main.jsp";//메뉴/footer유지 
  }
  @RequestMapping("freeboard/insert.do")
  public String freeboard_insert(HttpServletRequest request,HttpServletResponse response)
  {
	  // 입력창만 보여준다 
	  request.setAttribute("main_jsp", "../freeboard/insert.jsp");
	  return "../main/main.jsp";
  }
}






