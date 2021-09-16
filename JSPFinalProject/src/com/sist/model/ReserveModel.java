package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.vo.*;

/*
 *     요청   /  응답(오라클 포함)
 *     ===
 *     1. 데이터 전송 (요청) ==> ?변수=값  ===> 값을 받아서 처리후에 응답 
 *                                       ==================
 *                                        request.setAttribute()
 *     2. list.do  ===> @RequestMapping("list.do")
 *                      메소드 ==> 처리 == DAO
 *                      결과값을 JSP 전송  ===> 어떤 JSP (return ".jsp")
 */
import java.util.*; // 날짜 설정 
import java.text.*; // 날짜 변경 (SimpleDateFormat)
// 방식 ==> 스프링 웹 프로그램 방식 => 동일 (스프링이 기능이 많다) = 흐름보다 라이브러리
/*
 *   web.xml에 등록 (SpringMVC) => SpringMVC project 
 *                              ================== maven (라이브러리 관리,프로젝트 관리,
 *                               배포관리)  ant ==> XML
 *                               == 전자정부 프레임워크 
 */
@Controller
public class ReserveModel {
  // 달력 연습
  @RequestMapping("reserve/date.do")
  public String reserve_date(HttpServletRequest request,HttpServletResponse response)
  {
	  // 사용자 요청한 데이터 => 년도/월  ==> year/month
	  String strYear=request.getParameter("year");
	  String strMonth=request.getParameter("month");
	  // 1. 첫번째는 사용자가 선택할 내용이 없다 ==> default page 
	  Date date=new Date();// 시스템 날짜 / 시간 
	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-d");
	  // MM , dd (X)   => MM=> 09 , 10 , 11, 12  M=9,10,11,12
	  // 09 ==> 자바에서 인식 => 8진법
	  String today=sdf.format(date);
	  
	  StringTokenizer st=new StringTokenizer(today,"-");
	  String sy=st.nextToken();
	  String sm=st.nextToken();
	  String sd=st.nextToken();
	  
	  // default설정 
	  if(strYear==null)
		  strYear=sy;
	  if(strMonth==null)
		  strMonth=sm;
	  
	  // 정수형 변환 
	  int year=Integer.parseInt(strYear);
	  int month=Integer.parseInt(strMonth);
	  int day=Integer.parseInt(sd);
	  // 게시판 페이지 ==> if(page==null)
	  // 전송 ==> date.jsp 
	  // 요일 전송  ==> 
	  // 1. 1월 1일부터 ~ 전년도까지의 총합 
	  int total=(year-1)*365
			   +(year-1)/4    // 366
			   -(year-1)/100  // 365
			   +(year-1)/400; // 366  => 로마시대 (윤년)
	  // 2. 1월 1일 - 전달까지의 합 
	  int[] lastday={31,28,31,30,31,30,
			         31,31,30,31,30,31};
	  if((year%4==0 && year%100!=0)||(year%400==0))
	  {
		  lastday[1]=29; // 윤년이면 
	  }
	  else
	  {
		  lastday[1]=28; // 윤년이 아니면 
	  }
	  
	  for(int i=0;i<month-1;i++)// 전달까지
	  {
		  total+=lastday[i];
	  }
	  // 3. +1 => 각달의 1일
	  total++;
	  // 4. (1+2+3)%7 ==> 요일 계산 => 윤년 공식
	  int week=total%7;
	  // date.jsp에서 필요한(출력할) 모든 데이터를 request에 담아서 전송 
	  
	  String[] strWeek={"일","월","화","수","목","금","토"};
	  request.setAttribute("year", year);
	  request.setAttribute("month", month);
	  request.setAttribute("day", day);
	  request.setAttribute("week", week);
	  request.setAttribute("lastday", lastday[month-1]);
	  request.setAttribute("strWeek", strWeek);
	  return "../reserve/date.jsp";
  }
  
  @RequestMapping("reserve/reserve.do")
  public String reserve_page(HttpServletRequest request,HttpServletResponse response)
  {
	  // main.jsp에 보여주는 파일 
	  request.setAttribute("main_jsp", "../reserve/reserve.jsp");
	  return "../main/main.jsp";
  }
  
  // 목록 처리
  @RequestMapping("reserve/food_list.do")
  public String food_list(HttpServletRequest request,HttpServletResponse response)
  {
	  // DB 연동 
	  FoodDAO dao=FoodDAO.newInstance();
	  // 연동 
	  List<FoodVO> list=dao.reserveFoodListData();
	  request.setAttribute("list", list);
	  return "../reserve/food_list.jsp";
  }
}








