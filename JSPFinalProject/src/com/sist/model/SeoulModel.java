package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.xml.SeoulWeatherManager;
import com.sist.dao.*;
import com.sist.vo.*;
import java.util.*;
// NewsModel , MemberModel , ReserveModel , RecommandModel , PageModel(mypage,adminpage)
// BoardModel , ReplyModel , NoticeModel
// 자바 => 재사용 
@Controller
public class SeoulModel {
	   @RequestMapping("seoul/seoul_main.do")
	   public String seoul_main(HttpServletRequest request,HttpServletResponse response)
	   {
		   SeoulDAO dao=SeoulDAO.newInstance();
		   SeoulHotelVO hvo=dao.seoulHotelMainData();
		   SeoulLocationVO lvo=dao.seoulLoactionMainData();
		   SeoulNatureVO nvo=dao.seoulNatureMainData();
		   request.setAttribute("hvo", hvo);
		   request.setAttribute("lvo", lvo);
		   request.setAttribute("nvo", nvo);
		   request.setAttribute("main_jsp", "../seoul/seoul_main.jsp");
		   return "../main/main.jsp";
	   }
	   @RequestMapping("seoul/seoul_weather.do")
	   public String seoul_weather(HttpServletRequest request,HttpServletResponse response)
	   {
		   SeoulWeatherManager sm=new SeoulWeatherManager();
		   String data=sm.seoulWeather();
		   request.setAttribute("data", data);
		   request.setAttribute("main_jsp", "../seoul/seoul_weather.jsp");
		   return "../main/main.jsp";
	   }
	   // 명소 출력 
	   @RequestMapping("seoul/seoul_1.do")
	   public String seoul_1(HttpServletRequest request,HttpServletResponse response)
	   {
		   /*
		    *        startPage   endPage        
		    *         |           |
		    *   << < [1][2][3][4][5] > >>  curpage=1,2,3,4,5 => startPage=1,endPage=5
		    *                              curpage=6,7,8,9,10 => startPage=6,endPage=10
		    *                                  <c:forEach var="i" begin="${startPage}"
		    *                                                     end="${endPage}">
		    *                              
		    *   ==                     ==
		    *   1page                  총페이지
		    *   
		    *      startPage 1 , 6 , 11 , 16
		    *      endPage   5  , 10  15  , 20  
		    */
		   // 페이지를 받아서 => startPage,endPage => 블럭설정
		   String page=request.getParameter("page");
		   // 첫번째 페이지는 default로 설정 => 1
		   if(page==null)
			   page="1";
		   int curpage=Integer.parseInt(page);
		   SeoulDAO dao=SeoulDAO.newInstance();
		   // 해당 페이지의 출력할 데이터를 읽어 온다 (오라클)
		   List<SeoulLocationVO> list=dao.seoulLocationData(curpage);
		   // 총페이지 
		   int totalpage=dao.seoulLocationTotalPage();
		   // BLOCK나누기 
		   final int BLOCK=5;// [1][2][3][4][5] => [6][7][8][9][10]
		   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		   
		   // endPage ==> totalpage보다 클 수도 있다    5,10,15,20... 
		   //             ========= 13
		   if(endPage>totalpage)
			   endPage=totalpage;
		   
		   // 데이터를 출력할 수 있게 전송 
		   request.setAttribute("curpage", curpage);
		   request.setAttribute("totalpage", totalpage);
		   request.setAttribute("BLOCK", BLOCK);
		   request.setAttribute("startPage", startPage);
		   request.setAttribute("endPage", endPage);
		   request.setAttribute("list", list);
		   /*
		    *     1,2,3,4,5/BLOCK => 0
		    *     1page => 0/BLOCK =0 
		    *     2page => 1/5     =0
		    *     3
		    *     4
		    *     5     => (5-1)/5 => 0
		    *     
		    *     6(5/5),7(6/5),8(7/5),9(8/5),10(9/5) / 5 => 1*5= 5 + 1 => 6
		    *     11(10/5),12,13,14,15(14/5) ==> 2*5+1==> 11
		    */
		   request.setAttribute("main_jsp", "../seoul/seoul_1.jsp");
		   return "../main/main.jsp";
	   }
	   // 자연 출력 
	   @RequestMapping("seoul/seoul_2.do")
	   public String seoul_2(HttpServletRequest request,HttpServletResponse response)
	   {
		// 페이지를 받아서 => startPage,endPage => 블럭설정
		   String page=request.getParameter("page");
		   // 첫번째 페이지는 default로 설정 => 1
		   if(page==null)
			   page="1";
		   int curpage=Integer.parseInt(page);
		   SeoulDAO dao=SeoulDAO.newInstance();
		   // 해당 페이지의 출력할 데이터를 읽어 온다 (오라클)
		   List<SeoulNatureVO> list=dao.seoulNatureData(curpage);
		   // 총페이지 
		   int totalpage=dao.seoulNatureTotalPage();
		   // BLOCK나누기 
		   final int BLOCK=5;// [1][2][3][4][5] => [6][7][8][9][10]
		   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		   
		   // endPage ==> totalpage보다 클 수도 있다    5,10,15,20... 
		   //             ========= 13
		   if(endPage>totalpage)
			   endPage=totalpage;
		   
		   // 데이터를 출력할 수 있게 전송 
		   request.setAttribute("curpage", curpage);
		   request.setAttribute("totalpage", totalpage);
		   request.setAttribute("BLOCK", BLOCK);
		   request.setAttribute("startPage", startPage);
		   request.setAttribute("endPage", endPage);
		   request.setAttribute("list", list);
		   request.setAttribute("main_jsp", "../seoul/seoul_2.jsp");
		   return "../main/main.jsp";
	   }
	   // 호텔 
	   @RequestMapping("seoul/seoul_3.do")
	   public String seoul_3(HttpServletRequest request,HttpServletResponse response)
	   {
		// 페이지를 받아서 => startPage,endPage => 블럭설정
		   String page=request.getParameter("page");
		   // 첫번째 페이지는 default로 설정 => 1
		   if(page==null)
			   page="1";
		   int curpage=Integer.parseInt(page);
		   SeoulDAO dao=SeoulDAO.newInstance();
		   // 해당 페이지의 출력할 데이터를 읽어 온다 (오라클)
		   List<SeoulHotelVO> list=dao.seoulHotelData(curpage);
		   // 총페이지 
		   int totalpage=dao.seoulHotelTotalPage();
		   // BLOCK나누기 
		   final int BLOCK=5;// [1][2][3][4][5] => [6][7][8][9][10]
		   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		   
		   // endPage ==> totalpage보다 클 수도 있다    5,10,15,20... 
		   //             ========= 13
		   if(endPage>totalpage)
			   endPage=totalpage;
		   
		   // 데이터를 출력할 수 있게 전송 
		   request.setAttribute("curpage", curpage);
		   request.setAttribute("totalpage", totalpage);
		   request.setAttribute("BLOCK", BLOCK);
		   request.setAttribute("startPage", startPage);
		   request.setAttribute("endPage", endPage);
		   request.setAttribute("list", list);
		   request.setAttribute("main_jsp", "../seoul/seoul_3.jsp");
		   return "../main/main.jsp";
	   }
	   
	   // 명소 상세보기 
	   /*
	    *   <a href="../seoul/seoul_loc.do?no=${vo.no }"> JSP(사용자 요청)
	    *               |
	    *     seoul/seoul_loc.do 를 찾는다 
	    *     @RequestMapping("seoul/seoul_loc.do") => if를 사용하면 (한개의 파일)
	    *     =====================================
	    *       여러명이 동시에 다른 파일을 이용할 수 있다 (분산)  
	    *              |
	    *          XML / Annotation의 차이점 (Spring의 주요 면접)
	    *          ===   ==========
	    *          = 여러명의 개발자 공통으로 사용되는 부분 
	    *          = 개별적으로 개발시에 어노테이션 사용         
	    *          
	    *          = 기회 (이력서) => 업체 지원 (의뢰처) => 자격조건
	    *          
	    */
	   @RequestMapping("seoul/seoul_loc.do")
	   public String seoul_loc(HttpServletRequest request,HttpServletResponse response)
	   {
		   // 출력할 데이터 (한개만 실행 => 고유번호(Primary key) => no
		   String no=request.getParameter("no");
		   // 오라클에 저장된 데이터를 읽어 온다 
		   SeoulDAO dao=SeoulDAO.newInstance();
		   // 해당 명소데이터를 읽어 온다 
		   SeoulLocationVO vo=dao.seoulLocDetailData(Integer.parseInt(no));
		   // seoul_loc.jsp에서 출력 => 상세보기 출력 
		   // main.jsp => include (화면 브라우저 가운데 출력) => 메뉴/footer유지 
		   String addr=vo.getAddress();
		   //서울특별시 종로구 삼청로 50
		   addr=addr.substring(addr.indexOf(" "),addr.lastIndexOf("("));
		   addr=addr.trim();
		   
		   String gu=addr.substring(addr.indexOf(" ")+1);
		   // gu=종로구 삼청로 50
		   gu=gu.substring(0,gu.indexOf(" "));
		   // gu=종로구
		   // 맛집 
		   // 호텔 
		   // 자연
		   request.setAttribute("addr", addr);
		   request.setAttribute("vo", vo);
		   request.setAttribute("main_jsp", "../seoul/seoul_loc.jsp");
		   return "../main/main.jsp";
	   }
}





