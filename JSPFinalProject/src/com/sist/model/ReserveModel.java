package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
  // JSP:../reserve/time.do  ==> @RequestMapping("reserve/time.do") <=>DAO
  // 출력 => return 
  @RequestMapping("reserve/time.do")
  public String food_time(HttpServletRequest request,HttpServletResponse response)
  {
	  // data:{"no":day} ==> time.do?no=16
	  String no=request.getParameter("no");
	  // 오라클에서 데이터를 얻어 온다 
	  FoodDAO dao=FoodDAO.newInstance(); // Ajax(javascript) <=> include(jsp)  
	  // 시간을 읽기 시작 
	  String rtimes=dao.dateGetTime(Integer.parseInt(no));
	  // 2,3,4,5,6,...
	  List<String> list=new ArrayList<String>();
	  StringTokenizer st=new StringTokenizer(rtimes,",");
	  while(st.hasMoreTokens())
	  {
		  String s=st.nextToken();
		  String time=dao.realGetTime(Integer.parseInt(s));
		  list.add(time);
	  }
	  // time.jsp로 값을 전송 
	  request.setAttribute("list", list);
	  return "../reserve/time.jsp";
  }
  
  @RequestMapping("reserve/inwon.do")
  public String food_inwon(HttpServletRequest request,HttpServletResponse response)
  {
	  return "../reserve/inwon.jsp";
  }
  
  @RequestMapping("reserve/reserve_ok.do")
  public String reserve_ok(HttpServletRequest request,HttpServletResponse response)
  {
	  // 데이터 받기 
	  /*
	   *        <input type=hidden value="" name="fno" id="fno">
                <input type=hidden value="" name="fdate" id="fdate">
                <input type=hidden value="" name="ftime" id="ftime">
                <input type=hidden value="" name="finwon" id="finwon">
	   */
	  try
	  {
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex) {}
	  String fno=request.getParameter("fno");
	  String fdate=request.getParameter("fdate");
	  String ftime=request.getParameter("ftime");
	  String finwon=request.getParameter("finwon");
	  
	  HttpSession session=request.getSession();
	  String id=(String)session.getAttribute("id");
	  
	  // 데이터 묶어서 전송 (오라클)
	  ReserveVO vo=new ReserveVO();
	  vo.setFno(Integer.parseInt(fno));
	  vo.setId(id);
	  vo.setRday(fdate);
	  vo.setRtime(ftime);
	  vo.setInwon(Integer.parseInt(finwon));
	  // 오라클 연결 
	  FoodDAO dao=FoodDAO.newInstance();
	  //메소드 호출 
	  dao.reserveOk(vo);// 예약 저장 종료 
	  return "redirect:../reserve/mypage.do";
  }
  @RequestMapping("reserve/mypage.do")
  public String reserve_mypage(HttpServletRequest request,HttpServletResponse response)
  {
	  HttpSession session=request.getSession();
	  String id=(String)session.getAttribute("id");
	  FoodDAO dao=FoodDAO.newInstance();
	  
	  List<ReserveVO> list=dao.mypageData(id);
	  
	  request.setAttribute("list", list);
	  request.setAttribute("main_jsp", "../reserve/mypage.jsp");
	  return "../main/main.jsp";
  }
  
  @RequestMapping("reserve/adminpage.do")
  public String reserve_adminpage(HttpServletRequest request,HttpServletResponse response)
  {
	  // 예약 등록된 전체데이터 출력 
	  FoodDAO dao=FoodDAO.newInstance();
	  List<ReserveVO> list=dao.adminpageData();
	  request.setAttribute("list", list);
	  request.setAttribute("main_jsp", "../reserve/adminpage.jsp");
	  return "../main/main.jsp";
  }
  
  @RequestMapping("reserve/ok.do")
  public String reserve_ok2(HttpServletRequest request,HttpServletResponse response)
  {
	  String no=request.getParameter("no");
	  FoodDAO dao=FoodDAO.newInstance();
	  dao.reserveOkData(Integer.parseInt(no));
	  return "redirect:../reserve/adminpage.do";
  }
  
  @RequestMapping("reserve/reserve_info.do")
  public String reserve_info(HttpServletRequest request,HttpServletResponse response)
  {
	  // data:{"no":no}
	  String no=request.getParameter("no");
	  FoodDAO dao=FoodDAO.newInstance();
	  // 맛집에 대한 정보 
	  // 예약정보 
	  ReserveVO rvo=dao.reserveInfoData(Integer.parseInt(no));
	  FoodVO vo=dao.foodDetailData(rvo.getFno());
	  String address=vo.getAddress();
	  String addr1=address.substring(0,address.lastIndexOf("지"));
	   // 서울특별시 송파구 백제고분로41길 43-21 SANDONG빌딩
	  String addr2=address.substring(address.lastIndexOf("지"));
	  vo.setAddr1(addr1);
	  vo.setAddr2(addr2);
	  request.setAttribute("vo", vo);
	  request.setAttribute("rvo", rvo);
	  return "../reserve/reserve_info.jsp";
  }
}








