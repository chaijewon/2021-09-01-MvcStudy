package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.MemberDAO;
import java.util.*;
import com.sist.vo.*;

@Controller
public class MemberModel {
  @RequestMapping("member/join.do")
  public String member_join(HttpServletRequest request,HttpServletResponse response)
  {
	  request.setAttribute("main_jsp", "../member/join.jsp");
	  return "../main/main.jsp";
  }
  
  // 실행 => 실행결과만 받아온다 (Ajax) => Shadowbox를 그대로하면 결과값만 받아서 출력 (페이지 자체 처리)
  /*
   *    클라이언트가 요청 ==> 서버 ==> 서로운 페이지를 만들어 준다 (화면이 초기화)
   *    클라이언트가 요청 ==> 서버 ==> 현재 페이지에서 결과값을 읽어 온다 
   *                            ========= Web2.0 (실무 => 대부분이 AJax)
   *                                      Vue.js(Jquery보다 속도가 빠르다,Ajax)
   *                                      자바 (Vue,react), (코볼 , 포트란:jquery)
   *    => MVC 동작과정  (Model,Controller,View) => SpringMVC(AI:데이터분석,통계)
   *    => SQL문장 익히기  => MyBatis
   *    => Ajax(Javascript) => React,Vue
   */
  @RequestMapping("member/idcheck.do")
  public String member_idcheck(HttpServletRequest request,HttpServletResponse response)
  {
	  //사용자가 보내준 ID받기
	  String id=request.getParameter("id");// 한글 => 한글변환 
	  // DAO를 통해서 확인 (있는 아이디,없는 아이디)
	  MemberDAO dao=MemberDAO.newInstance();
	  int count=dao.memberidCheck(id);
	  request.setAttribute("count", count);
	  return "../member/idcheck_result.jsp";
  }
  @RequestMapping("member/postfind.do")
  public String member_postfind(HttpServletRequest request,HttpServletResponse response)
  {
	  // 동을 받는다 (한글)
	  // 한글변환(디코딩) => 인코딩 (바이트)->디코딩(Unicode=>UTF-8)
	  try
	  {
		  request.setCharacterEncoding("UTF-8");//디코딩 => post방식일때만 사용
	  }catch(Exception ex) {}
	  // 값을 받는다 
	  // <input type=text name=dong id=dong size=15 class="input-sm">
	  // getParameter(name명)
	  String dong=request.getParameter("dong");
	  // DAO를 연결해서 데이터 읽기
	  MemberDAO dao=MemberDAO.newInstance(); // 한개의 메로리 사용(메모리 누수방지) = 싱글턴
	  List<ZipcodeVO> list=dao.postfind(dong);
	  int count=dao.postfindCount(dong);
	  // postfind_result.jsp에 출력하기 위해서 데이터를 보내준다 (request,session)
	  /*
	   *  request : 해당 페이지에서만 출력할때
	   *  session : 기간,여러군데 jsp에서 사용  ==> 개인 정보를 서버에 저장 (로그인,장바구니)
	   */
	  request.setAttribute("list", list);
	  request.setAttribute("count", count);
	  return "../member/postfind_result.jsp";
  }
  // 로그인 , 검색 => Ajax
  // 회원가입 처리 
  @RequestMapping("member/join_ok.do")
  public String member_join_ok(HttpServletRequest request,HttpServletResponse response)
  {
	  System.out.println("회원 데이터 전송완료");
	  // 사용자가 보내준 데이터를 받는다 
	  try
	  {
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex) {}
	  String id=request.getParameter("id");//스프링 => 매개변수 (MemberVO vo)
	  String pwd=request.getParameter("pwd");
	  String name=request.getParameter("name");
	  String sex=request.getParameter("sex");
	  String birthday=request.getParameter("birthday");
	  String email=request.getParameter("email");// UNIQUE  => 아이디 찾기
	  String post1=request.getParameter("post1");
	  String post2=request.getParameter("post2");
	  String addr1=request.getParameter("addr1");
	  String addr2=request.getParameter("addr2");
	  String tel1=request.getParameter("tel1");// UNIQUE => 아이디 찾기 (후보키)
	  String tel2=request.getParameter("tel2");//
	  
	  MemberVO vo=new MemberVO();
	  vo.setId(id);
	  vo.setPwd(pwd);
	  vo.setName(name);
	  vo.setSex(sex);
	  vo.setBirthday(birthday);
	  vo.setPost(post1+"-"+post2);
	  vo.setEmail(email);
	  vo.setAddr1(addr1);
	  vo.setAddr2(addr2);
	  vo.setTel(tel1+"-"+tel2);
	  // DAO로 전송 
	  MemberDAO dao=MemberDAO.newInstance();
	  dao.memberJoinInsert(vo);
	  return "redirect:../main/main.do";// main에서 회원가입 데이터가 필요가 없다 (request초기화)
	  // sendRedirect() ==> DispatcherServlet => redirect:
  }
  // 회원 가입 , 로그인 , 예매/예약 , 검색  , 추천 (통계)  => 페이지기법 
  @RequestMapping("member/login.do")
  public String member_login(HttpServletRequest request,HttpServletResponse response)
  {
	  // 사용자 전송한 id,pwd
	  String id=request.getParameter("id");
	  String pwd=request.getParameter("pwd");
	  // DAO로 전송 => 결과값을 받아서 => login_result.jsp 전송 (출력=>main.jsp에 읽어간다)
	  MemberDAO dao=MemberDAO.newInstance();
	  // 메소드 호출 
	  String result=dao.isLogin(id, pwd);
	  if(!(result.equals("NOID")|| result.equals("NOPWD")))
	  {
		  HttpSession session=request.getSession(); // 세션 사용 
		  // 세션/쿠키 => request를 이용한다  (request.getCookie())
		  StringTokenizer st=new StringTokenizer(result,"|");
		  String name=st.nextToken();
		  String admin=st.nextToken();
		  
		  // 세션에 저장 
		  session.setAttribute("id", id);
		  session.setAttribute("admin", admin);
		  session.setAttribute("name", name);
		  result="OK";
	  }
	  request.setAttribute("result", result);
	  return "../member/login_result.jsp";
  }
  // 로그 아웃
  @RequestMapping("member/logout.do")
  public String member_logout(HttpServletRequest request,HttpServletResponse response)
  {
	  HttpSession session=request.getSession();
	  session.invalidate(); // 데이터 전체 삭제
	  return "redirect:../main/main.do";
  }
  // id찾기
  @RequestMapping("member/idfind.do")
  public String member_idfind(HttpServletRequest request,HttpServletResponse response)
  {
	  request.setAttribute("main_jsp", "../member/idfind.jsp");
	  return "../main/main.jsp";
  }
  
  @RequestMapping("member/join_update.do")
  public String member_join_update(HttpServletRequest request,HttpServletResponse response)
  {
	  HttpSession session=request.getSession();
	  String id=(String)session.getAttribute("id");
	  MemberDAO dao=MemberDAO.newInstance();
	  MemberVO vo=dao.memberJoinUpdateData(id);
	  String temp=vo.getPost();
	  String post1=temp.substring(0,3);
	  String post2=temp.substring(4,7);
	  temp=vo.getTel();
	  String tel1=temp.substring(0,3);
	  String tel2=temp.substring(4);
	  
	  request.setAttribute("vo", vo);
	  request.setAttribute("post1", post1);
	  request.setAttribute("post2", post2);
	  
	  request.setAttribute("tel1", tel1);
	  request.setAttribute("tel2", tel2);
	  request.setAttribute("main_jsp", "../member/join_update.jsp");
	  return "../main/main.jsp";
  }
  @RequestMapping("member/join_update_ok.do")
  public String member_join_update_ok(HttpServletRequest request,HttpServletResponse response)
  {
	  System.out.println("회원 수정 접근");
	  try
	  {
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex) {}
	  String id=request.getParameter("id");//스프링 => 매개변수 (MemberVO vo)
	  String pwd=request.getParameter("pwd");
	  String name=request.getParameter("name");
	  String sex=request.getParameter("sex");
	  String birthday=request.getParameter("birthday");
	  String email=request.getParameter("email");// UNIQUE  => 아이디 찾기
	  String post1=request.getParameter("post1");
	  String post2=request.getParameter("post2");
	  String addr1=request.getParameter("addr1");
	  String addr2=request.getParameter("addr2");
	  String tel1=request.getParameter("tel1");// UNIQUE => 아이디 찾기 (후보키)
	  String tel2=request.getParameter("tel2");//
	  
	  MemberVO vo=new MemberVO();
	  vo.setId(id);
	  vo.setPwd(pwd);
	  vo.setName(name);
	  vo.setSex(sex);
	  vo.setBirthday(birthday);
	  vo.setPost(post1+"-"+post2);
	  vo.setEmail(email);
	  vo.setAddr1(addr1);
	  vo.setAddr2(addr2);
	  vo.setTel(tel1+"-"+tel2);
	  // DAO로 전송 
	  MemberDAO dao=MemberDAO.newInstance();
	  boolean bCheck=dao.joinUpdate(vo);
	  if(bCheck==true)
	  {
		  HttpSession session=request.getSession();
		  session.setAttribute("name", vo.getName());
	  }
	  request.setAttribute("bCheck", bCheck);
	  return "../member/join_update_ok.jsp";
  }
  @RequestMapping("member/join_delete.do")
  public String member_join_delete(HttpServletRequest request,HttpServletResponse response)
  {
	  request.setAttribute("main_jsp", "../member/join_delete.jsp");
	  return "../main/main.jsp";
  }
  @RequestMapping("member/join_delete_ok.do")
  public String member_join_delete_ok(HttpServletRequest request,HttpServletResponse response)
  {
	  HttpSession session=request.getSession();
	  String id=(String)session.getAttribute("id");
	  String pwd=request.getParameter("pwd");
	  MemberDAO dao=MemberDAO.newInstance();
	  boolean bCheck=dao.memberJoinDelete(id, pwd);
	  if(bCheck==true)
	  {
		  session.invalidate();
	  }
	  request.setAttribute("bCheck", bCheck);
	  return "../member/join_delete_ok.jsp";
  }
  @RequestMapping("member/idfind_email.do")
  public String member_idfind_email(HttpServletRequest request,HttpServletResponse response)
  {
	  String email=request.getParameter("email");
	  MemberDAO dao=MemberDAO.newInstance();
	  String s=dao.member_idfind_email(email);
	  request.setAttribute("result", s);
	  return "../member/idfind_email.jsp";
  }
  @RequestMapping("member/idfind_tel.do")
  public String member_idfind_tel(HttpServletRequest request,HttpServletResponse response)
  {
	  String tel=request.getParameter("tel");
	  MemberDAO dao=MemberDAO.newInstance();
	  String s=dao.member_idfind_tel(tel);
	  request.setAttribute("result", s);
	  return "../member/idfind_tel.jsp";
  }
}


