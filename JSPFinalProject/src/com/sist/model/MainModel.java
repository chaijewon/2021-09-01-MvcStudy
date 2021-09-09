package com.sist.model;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.vo.*;

@Controller
public class MainModel {
  @RequestMapping("main/main.do")
  public String main_main(HttpServletRequest request,HttpServletResponse response)
  {
	   FoodDAO dao=FoodDAO.newInstance();
	   List<CategoryVO> list=dao.foodCategoryData();
	   request.setAttribute("list", list);
	   // 쿠키 데이터 보내기 
	   HttpSession session=request.getSession();
	   String id=(String)session.getAttribute("id");
	   List<FoodVO> fList=new ArrayList<FoodVO>();
	   if(id!=null)//로그인시에만 쿠키 전송 
	   {
		   // 쿠키 읽기
		   Cookie[] cookies=request.getCookies();
		   if(cookies!=null && cookies.length>1) // 쿠키가 존재 
		   {
			   for(int i=cookies.length-1;i>=0;i--)
			   {
				   if(cookies[i].getName().startsWith(id+"f"))
				   {
					   cookies[i].setPath("/");
					   String no=cookies[i].getValue();
					   FoodVO vo=dao.foodCookieInfoData(Integer.parseInt(no));
					   fList.add(vo);
				   }
			   }
		   }
		   
		   request.setAttribute("fList", fList);
	   }
	   // <c:forEach> ==> 뒤에서 출력 (++)
	   request.setAttribute("main_jsp", "../food/food_main.jsp");
	   return "../main/main.jsp";
  }
}






