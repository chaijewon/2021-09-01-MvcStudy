package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
  // 화면(이동)제어 / 데이터 전송 ==> Model (자바코딩) ==> JSP/Model => Controller(DispatcherServlet) 
  /*
   *   M(Model) : 자바할 수 있는 모든 처리 
   *              요청값을 받거나 , 요청 처리 , 데이터베이스 처리 : 비지니스로직 
   *   V(View)  : JSP로 만들어진다 (HTML => Vue,React,Ajax) => 데이터를 받아서 화면 출력
   *              프리젠테이션로직  
   *   C(Controller) : 배달 (자바(Model)에서 결과값을 가지고 와서 JSP로 전송)
   *                   ===> 전달 수단(Model=>View) : request,session 
   *                   ===> request:JSP끼리 공유 (JSP마다 request를 가지고 있다)
   *                        ================== forward/include
   *   JSP(요청) => Spring를 모방 
   *     <a href="../notice/insert.do">
   *             |
   *       DispatcherServlet (service()) => .jar
   *             |  notice/insert.do를 찾기 시작 
   *                @RequestMapping("notice/insert.do")
   *                  1. GetMapping() , 2. PostMapping() => 4버전이상
   *                 => 밑에 있는 메소드를 호출 
   *                    => invoke() : 메소드 호출 (메소드명을 모르는 상태)
   *                       a.display()
   *                       => 결과값을 request에 담아준다 
   *                          request.setAttribute()
   *                    => jsp.forward(request) => request를 공유 
   *       Spring => 5버전 (보안) => 소스를 노출하지 않는다 
   *                 XML을 사용하면 않된다 (보안이 취약) => 순수하게 자바로만 만든다 
   *                 ==> 실무 (5버전이 아니라 => 4버전) 
   *                     = 스프링 기반 (공기업 : 전자정부프레임워크,대기업:애니프레임워크)
   *                     = 학교(.net) , 금융권(스프링)
   *       ****** 중요 
   *              = 중심(클래스보다 메소드에 중심) 
   */
  @RequestMapping("notice/insert.do")
  public String notice_insert(HttpServletRequest request,HttpServletResponse response)
  {
	  // include할 파일을 전송 => 라이브러리 (타일즈:tiles)
	  request.setAttribute("main_jsp", "../notice/insert.jsp");
	  return "../main/main.jsp";
  }
  
  @RequestMapping("notice/insert_ok.do")
  // redirect => _ok 
  // 기능수행후 보여주지 않는 부분 (insert,update,delete) => sendRedirect() 
  // sendRedirect()  => 서버에서 자체 처리 
  public String notice_insert_ok(HttpServletRequest request,HttpServletResponse response)
  {
	  try
	  {
		  //  한글 
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex){}
	  String subject=request.getParameter("subject");
	  String content=request.getParameter("content");
	  HttpSession session=request.getSession();
	  String name=(String)session.getAttribute("name");
	  // 데이터를 묶어서 DAO전송 (DAO는 INSERT를 한다)
	  NoticeVO vo=new NoticeVO();
	  vo.setName(name);
	  vo.setSubject(subject);
	  vo.setContent(content);
	  
	  // vo전송 
	  NoticeDAO dao=NoticeDAO.newInstance();
	  // 데이터 추가하는 메소드 호출 
	  dao.noticeInsert(vo);
	  return "redirect:../notice/list.do";//insert수행 종료하면 => list.do 다시 수행한다 
  }
  @RequestMapping("notice/notice_delete.do")//공지사항 삭제를 요청 했다면 
  public String notice_delete(HttpServletRequest request,HttpServletResponse response)
  {
	  // 1. 사용자 보내준 데이터를 받는다 
	  String no=request.getParameter("no");
	  // 삭제하려면 => 어떤 데이터가 필요한지 확인 (primary key=>no)
	  // 회원 탈퇴 => id를 보내준다 
	  // 대부분은 숫자로 만들어져있다 
	  // 211.238.142.34  => daum.net(도메인)
	  // 127.0.0.1 => localhost
	  // DAO연결 
	  NoticeDAO dao=NoticeDAO.newInstance();
	  // 삭제할 메소드 호출 
	  dao.noticeDelete(Integer.parseInt(no));
	  return "redirect:../notice/list.do";//delete수행 종료하면 => list.do 다시 수행한다 
  }
  @RequestMapping("notice/notice_update.do")//update를 요청했다면
  public String notice_update(HttpServletRequest request,HttpServletResponse response)
  {
	  // 기존에 입력된 데이터를 출력 
	  String no=request.getParameter("no");
	  // DAO로 전송 => no에 해당되는 공지사항을 가지고 온다
	  NoticeDAO dao=NoticeDAO.newInstance();
	  // 공지사항을 읽어오는 메소드 호출 
	  NoticeVO vo=dao.noticeUpdateData(Integer.parseInt(no));
	  request.setAttribute("vo", vo);
	  request.setAttribute("main_jsp", "../notice/update.jsp");
	  return "../main/main.jsp"; // 수정할 수 있는 창을 보여준다 
  }
  @RequestMapping("notice/update_ok.do")
  public String notice_update_ok(HttpServletRequest request,HttpServletResponse response)
  {
	  // subject,content,no
	  try
	  {
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex){}
	  String no=request.getParameter("no");
	  String subject=request.getParameter("subject");
	  String content=request.getParameter("content");
	  // 묶어서 => DAO로 전송 
	  NoticeVO vo=new NoticeVO();// 여러개의 데이터 한번 묶어서 처리(전송)
	  // DAO전송 , JSP전송 
	  vo.setNo(Integer.parseInt(no));
	  vo.setSubject(subject);
	  vo.setContent(content);
	  
	  NoticeDAO dao=NoticeDAO.newInstance();
	  // 수정하는 메소드 호출 => 오라클 수정 완료 
	  dao.noticeUpdate(vo);
	  return "redirect:../notice/detail.do?no="+no;// 상세보기로 이동 
	  /*
	   *   update => detail(list에는 내용이 없기때문에)
	   *   ================ 
	   *   reply
	   *   delete
	   *   insert
	   *   =============== list로 이동 
	   */
  }
}

 





