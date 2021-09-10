package com.sist.model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
@Controller
public class FoodModel {
   @RequestMapping("food/food_main.do")
   public String food_main(HttpServletRequest request,HttpServletResponse response)
   {
	   return "redirect:../main/main.do";
   }
   // jsp(link,button-click) => Model(데이터받기) => DAO (데이터 수집) 
   // => Model(DAO에 출력된 결과값 읽기) => request로 묶어서 jsp로 전송
   /*
    *   참조 : 9장 
    *   쿠키 (클라이언트 브라우저에 저장) => 보안에 취약 (최근 방문,자동 로그인) => 트라이얼버전(쿠키)(WebStorm)
    *                                             => 배포 (webpack) 
    *                                             => Vue,React,Node    
    *   ===
    *   1. 쿠키 생성 
    *      Cookie cookie=new Cookie(키,값)  => Cookie는 저장이 문자열만 저장이 가능
    *   2. 쿠키 저장 기간 설정 
    *      setMaxAge(0) => 삭제 
    *               === 초단위  (60*60) => 1시간  (60*60*5)
    *   2-2. 저장위치 지정  ==> / (루트에 저장)
    *      setPath("/")
    *   3. 클라이언트로 전송 
    *      response.addCookie(cookie) 
    *   4. 쿠키 읽기 ( 키 , 값 ) => 화면에 출력 
    *      Cookie[] cookies=request.getCookies()
    *      4-1. 키 => cookies[i].getName()
    *      4-2. 값 => cookies[i].getValue()
    *   
    *   참조 : 10장 
    *   세션 : 서버에 저장 (보안,여러개의 데이터 한번에 저장 (Object)
    *        = 사용자 정보 , 장바구니 , 예매내용 
    *   1. 생성 
    *      HttpSession session=request.getSession();
    *      =================== 추상클래스
    *   2. 저장 
    *      session.setAttribute("키",값(Object))
    *   3. 값읽기
    *      session.setAttribute("키")
    *   4. 삭제(일부)
    *      session.removeAttribute("키") => 회원수정 
    *   5. 전체삭제 
    *      session.invalidate() => 로그아웃 , 브라우저 종료 
    *   6. 저장기간 (기본=1800초(30분))
    *      session.setMaxinactiveInterval(초단위 지정)
    *      
    *   JSP 
    *   ===
    *     기술면접 : 11월 (면접) => 추천 (이력서:포트폴리어:JSP(MVC),Spring) 
    *     ======
    *      GET / POST 
    *      Cookie/Session
    *      MVC
    *   
    */
   @RequestMapping("food/food_list.do")
   public String food_list(HttpServletRequest request,HttpServletResponse response)
   {
	   // 카테고리 번호 받기
	   String cno=request.getParameter("cno");
	   // FoodDAO를 통해서 해당 카테고리의 맛집을 가지고 온다 
	   FoodDAO dao=FoodDAO.newInstance();
	   CategoryVO vo=dao.foodCategoryInfoData(Integer.parseInt(cno));
	   List<FoodVO> list=dao.foodCategoryListData(Integer.parseInt(cno));
	   // food_list.jsp로 데이터를 전송 => 출력 => main.jsp에 출력된 내용을 읽어 간다 
	   request.setAttribute("vo", vo);
	   request.setAttribute("list", list);
	   request.setAttribute("main_jsp", "../food/food_list.jsp");
	   return "../main/main.jsp";
   }
   
   @RequestMapping("food/food_detail_before.do")
   public String food_detail_before(HttpServletRequest request,HttpServletResponse response)
   {
	   // 쿠키 생성 => 쿠기 전송 => 상세보기로 넘어간다 (response는 한개의 jsp 한번만 전송이 가능)
	   // 전송 (1.쿠키 , 2.HTML) => 두개를 동시에 전송이 불가능하다 
	   String no=request.getParameter("no");
	   HttpSession session=request.getSession();
	   String id=(String)session.getAttribute("id");
	   Cookie cookie=new Cookie(id+"f"+no, no);
	   // 키,값 = Map = JSON , request,response ,session
	   cookie.setMaxAge(60*60*24); // 초단위 계산 (24시간)
	   cookie.setPath("/");
	   // 쿠키 전송 
	   response.addCookie(cookie);
	   /*
	    *  영화 / 레시피 (id+"r")/ 여행(id+"t") ==> 구분 쿠키 
	    *  id+"m" 
	    *  
	    *  => shim => 
	    *  => hong => 
	    */
	   // 클라이언트로 전송 
	   
	   return "redirect:../food/food_detail.do?no="+no;//RedirectAttribute()
   }
   
   @RequestMapping("food/food_detail.do")
   public String food_detail(HttpServletRequest request,HttpServletResponse response)
   {
	   // 요청 : 번호에 해당되는 맛집을 보여달라 (번호가 중복이 없기때문에) => 테이블 (Primary Key)
	   String no=request.getParameter("no");
	   // 
	   // DAO
	   FoodDAO dao=FoodDAO.newInstance();
	   FoodVO vo=dao.foodDetailData(Integer.parseInt(no));
	   String address=vo.getAddress();
	   String addr1=address.substring(0,address.lastIndexOf("지"));
	   // 서울특별시 송파구 백제고분로41길 43-21 SANDONG빌딩
	   String addr2=address.substring(address.lastIndexOf("지"));
	   // 서울특별시 송파구 백제고분로41길 43-21 SANDONG빌딩 지번 서울시 송파구 송파동 8 SANDONG빌딩
	   String temp=address.substring(address.indexOf(" ")+1);
	   // 송파구 백제고분로41길 43-21 SANDONG빌딩
	   temp=temp.substring(0,address.indexOf(" "));
	   // 송파구
	   /*
	    *   송파구 백제고분로41길 43-21 SANDONG빌딩
                    지번 서울시 송파구 송파동 8 SANDONG빌딩
	    */
	   // 결과값 보내기
	   vo.setAddr1(addr1);
	   vo.setAddr2(addr2);
	   request.setAttribute("vo", vo);
	   // 1. 여행(명소,호텔,자연) , 2. 댓글 
	   List<SeoulLocationVO> aList=dao.foodSeoulLocationData(temp);
	   request.setAttribute("aList", aList);
	   List<SeoulHotelVO> bList=dao.foodSeoulHotelData(temp);
	   request.setAttribute("bList", bList);
	   List<SeoulNatureVO> cList=dao.foodSeoulNatureData(temp);
	   request.setAttribute("cList", cList);
	   request.setAttribute("main_jsp", "../food/food_detail.jsp");
	   return "../main/main.jsp";
   }
}






