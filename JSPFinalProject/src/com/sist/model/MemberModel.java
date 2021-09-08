package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.MemberDAO;

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
}


