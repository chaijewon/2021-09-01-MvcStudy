package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FreeBoardDAO;

import java.net.HttpRetryException;
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
  @RequestMapping("freeboard/insert_ok.do")
  public String freeboard_insert_ok(HttpServletRequest request,HttpServletResponse response)
  {
	  // 처리 
	  try
	  {
		  // 한글 변환 (디코딩)
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex) {}
	  // 데이터를 모아서 => DAO전송 (오라클에 추가한다)
	  String name=request.getParameter("name");
	  String subject=request.getParameter("subject");
	  String content=request.getParameter("content");
	  String pwd=request.getParameter("pwd");
	  
	  
	  FreeBoardVO vo=new FreeBoardVO();
	  vo.setName(name);
	  vo.setSubject(subject);
	  vo.setContent(content);
	  vo.setPwd(pwd);
	  
	  // DAO로 전송 
	  FreeBoardDAO dao=FreeBoardDAO.newInstance();
	  // 추가하는 메소드 호출 
	  dao.freeboardInsert(vo);
	  return "redirect:../freeboard/list.do";// 처리후에 => 목록을 보여준다 
  }
  // 게시판 상세보기 
  @RequestMapping("freeboard/detail.do")
  public String freeboardDetail(HttpServletRequest request,HttpServletResponse response)
  {
	  // 요청한 데이터 받기 (no)
	  String no=request.getParameter("no");
	  // FreeBoardDAO연결 => 데이터 얻기를 한다 
	  FreeBoardDAO dao=FreeBoardDAO.newInstance();
	  // 메소드 호출 
	  FreeBoardVO vo=dao.freeboardDetailData(Integer.parseInt(no));
	  // 댓글 받기
	  List<ReplyVO> list=dao.replyListData(Integer.parseInt(no), 1);
	  request.setAttribute("list", list);
	  request.setAttribute("vo", vo);
	  request.setAttribute("main_jsp", "../freeboard/detail.jsp");
	  /*
	   *   ======================
	   *     고정 (메뉴) header
	   *   ======================
	   *   
	   *       사용자 요청에 따라 변경되는 영역 ==> 출력할 JSP를 넘겨준다 
	   *       request.setAttribute("main_jsp", "../freeboard/detail.jsp");
	   *   
	   *   ======================
	   *     고정 (footer)
	   *   ======================
	   */
	  return "../main/main.jsp";
  }
  @RequestMapping("freeboard/update.do")
  public String freeboard_update(HttpServletRequest request,HttpServletResponse response)
  {
	  // Model => Model(요청 => 응답:비지니스로직) => DAO,VO,Manager(포함) 
	  // 자바 코딩은 모두 Model이다 
	  // 데이터 읽기
	  String no=request.getParameter("no");
	  // DAO=>연결 
	  FreeBoardDAO dao=FreeBoardDAO.newInstance();
	  // FreeBoardVO값을 얻어온다 
	  FreeBoardVO vo=dao.freeboardUpdateData(Integer.parseInt(no));
	  // update.jsp전송 
	  request.setAttribute("vo", vo);
	  request.setAttribute("main_jsp", "../freeboard/update.jsp");
	  return "../main/main.jsp";
  }
  @RequestMapping("freeboard/update_ok.do")
  public String freeboard_update_ok(HttpServletRequest request,HttpServletResponse response)
  {
	  // 스프링에서는 @RestController => 자바스크립트 전송이 가능 (자바스크립트 전송 불가능)
	  // 수정하는 데이터를 받는다 
	  try
	  {
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex) {}
	  
	  String name=request.getParameter("name");
	  String subject=request.getParameter("subject");
	  String content=request.getParameter("content");
	  String pwd=request.getParameter("pwd");
	  String no=request.getParameter("no");
	  
	  FreeBoardVO vo=new FreeBoardVO();
	  vo.setNo(Integer.parseInt(no));
	  vo.setName(name);
	  vo.setSubject(subject);
	  vo.setContent(content);
	  vo.setPwd(pwd);
	  
	  FreeBoardDAO dao=FreeBoardDAO.newInstance();
	  // 메소드 호출 
	  boolean bCheck=dao.freeboardUpdate(vo);
	  // 답변 , 수정 , 추가 => 오라클에 있는 데이터 조절 
	  request.setAttribute("bCheck", bCheck);
	  request.setAttribute("no", no);
	  return "../freeboard/update_ok.jsp";//이동 2개 (비밀번호 틀릴경우/비밀번호 맞는 경우) 
  }
  // 사용자 요청을 구분하는 역할 => DispatcherServlet => @RequestMapping를 찾는다 
  // 밑에 있는 메소드 자동 호출 
  // request를 retrun에 등록된 jsp를 전송 
  /*
   *   JSP(브라우저) => DispatcherServlet ==> Model을 찾는다 ====> 메소드 호출해서 요청처리 
   *                                                          =================
   *       요청     ==> *.do                                        DAO연결 
   *                                                               |
   *                                                            결과값을 request에 담는다
   *                                                               |
   *                                                            DispatcherServlet
   *                                                               |
   *                                                            해당 JSP로 연결 
   *       JSP(링크,form)  ==> @RequestMapping(".do") => DAO => Model => JSP
   *          .do
   *       동작하는 순서 
   *       흐름 파악 (동작하는 순서)
   */
  @RequestMapping("freeboard/delete.do")
  public String freeboard_delete(HttpServletRequest request,HttpServletResponse response)
  {
	  // request => 사용자 요청정보를 가지고 있다 => 톰캣이 설정을 한다 
	  // response => 서버의 응답 정보                => 톰캣이 아이피를 찾아서 =.> 해당 브라우저로 전송
	  // 요청 : 어떤 데이터를 보낼 것인지?   delete.do?no=${}
	  String no=request.getParameter("no");
	  request.setAttribute("no", no);
	  request.setAttribute("main_jsp", "../freeboard/delete.jsp");
	  // include => delete.jsp,main.jsp가 request를 공유할 수 있다 (필요한 JSP에서 request를 사용한다)
	  return "../main/main.jsp"; // 메뉴/footer가 없어진다 
  }
  @RequestMapping("freeboard/delete_ok.do")
  public String freeboard_delete_ok(HttpServletRequest request,HttpServletResponse response)
  {
	  // 보여주는 화면 => list
	  
	  // 리다이렉트는 이미 화면이 만들어져있다면 
	  // 새로운 화면 => forward
	  // Ajax => 해당 jsp만 실행 
	  // _ok (처리) => 처리만 한다 (화면에 출력하지 않는다) => redirect
	  
	  // 요청값을 받는다 
	  String pwd=request.getParameter("pwd");
	  String no=request.getParameter("no");
	  
	  // pwd,no => 삭제 요청( DAO )
	  FreeBoardDAO dao=FreeBoardDAO.newInstance();
	  // 메소드 호출 
	  boolean bCheck=dao.freeboardDelete(Integer.parseInt(no), pwd);
	  // 결과값을 delete_ok.jsp 
	  request.setAttribute("bCheck", bCheck);
	  return "../freeboard/delete_ok.jsp";// list.jsp , history.back()
  }
  /*
   *   DAO => 1.DBCP , 2. Transcation , 3. 싱글턴 
   */
  @RequestMapping("freeboard/reply_insert.do") // if => annotation 
  // 어노테이션을 if문을 추가하는 것이다 (조건 => 조건에 맞는 메소드를 호출할 목적) = 찾기(index)
  public String reply_insert(HttpServletRequest request,HttpServletResponse response)
  {
	  // msg , bno , type
	  // 한글 처리 
	  try
	  {
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex) {}
	  
	  // 사용자가 보내준 값을 받는다 
	  String bno=request.getParameter("bno");
	  String type=request.getParameter("type");
	  String msg=request.getParameter("msg");
	  
	  HttpSession session=request.getSession();
	  String id=(String)session.getAttribute("id");
	  String name=(String)session.getAttribute("name");
	  
	  // 묶어서 DAO로 전송 
	  ReplyVO vo=new ReplyVO();
	  vo.setBno(Integer.parseInt(bno));
	  vo.setId(id);
	  vo.setName(name);
	  vo.setType(Integer.parseInt(type));
	  vo.setMsg(msg);
	  // 이동 댓글이 올라간다 
	  FreeBoardDAO dao=FreeBoardDAO.newInstance();
	  // 댓글 추가 메소드를 호출 
	  dao.replyInsert(vo);
	  return "redirect:../freeboard/detail.do?no="+bno;
  }
  // 조립 => Model (요청값 받기 , DAO연결 , 페이지 이동)
  /*
   *   @RequestMapping("freeboard/reply_delete.do") => 
   *                    ========================== 사용자가 보내준 URI주소
   *       사용자가 요청시에 해당 메소드를 찾아 주는 역할 
   *   메소드 
   *    1. 사용자가 보내준 요청값을 받는다 (getParameter())
   *    2. DAO연결 => DB처리 
   *    3. 결과값이 있는 경우 => 결과값을 전송 ==> request.setAttribute()
   *       include => request.setAttribute("main_jsp","jsp파일명")
   *    4. 해당 JSP를 찾아서 request를 전송 ==> return "../main/main.jsp"
   *    
   *    ================================== JSP에서 전송받은 결과값을 출력 
   *                                       JSTL/EL (제어문이 없는 경우)
   *                                       ==== 제어문이 필요
   */
  @RequestMapping("freeboard/reply_delete.do")
  public String reply_delete(HttpServletRequest request,HttpServletResponse response)
  {
	  // 요청 데이터를 받는다 
	  String no=request.getParameter("no");// 댓글번호 (삭제 목적)
	  String bno=request.getParameter("bno");// 게시물 번호(해당 페이지로 이동)
	  
	  // DAO
	  FreeBoardDAO dao=FreeBoardDAO.newInstance();
	  // 삭제 메소드 호출 
	  dao.replyDelete(Integer.parseInt(no));
	  return "redirect:../freeboard/detail.do?no="+bno;// 게시판 상세보기를 보여 달라 
  }
  // 어노테이션은 (찾기) => 적용된 메소드 , 클래스 => 항상 밑에 있다 
  // 웹사이트 => 해당페이지 이동 (어떤 파일을 보여줄것인지) => 흐름  => main_jsp
  @RequestMapping("freeboard/reply_update.do")
  public String reply_update(HttpServletRequest request,HttpServletResponse response)
  {
	  // 요청 데이터를 받는다  (no,bno,msg)
	  // 요청 데이터가 한글일 경우 => 한글 변환 코드설정 
	  try
	  {
		  request.setCharacterEncoding("UTF-8");//디코딩
	  }catch(Exception ex) {}
	  
	  String no=request.getParameter("no");
	  String bno=request.getParameter("bno");//이동할 목적 (댓글이 있는 상세보기로)
	  String msg=request.getParameter("msg");
	  
	  // DAO연결 
	  FreeBoardDAO dao=FreeBoardDAO.newInstance();
	  // 수정 할 메소드 호출 
	  dao.replyUpdate(Integer.parseInt(no), msg);
	  return "redirect:../freeboard/detail.do?no="+bno;// Ajax => Spring
  }
}






