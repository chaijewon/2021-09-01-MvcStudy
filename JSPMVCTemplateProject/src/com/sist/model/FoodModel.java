package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
// main/main.do => food/category.do (main.jsp(X))
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.FoodCategoryVO;
import com.sist.vo.FoodLocationVO;
@Controller
public class FoodModel {
   @RequestMapping("food/category.do")
   public String food_category(HttpServletRequest request,HttpServletResponse response) {
	   // include할 파일을 전송 => request를 공유 
	   // request 공유 (include / forward) => 나머지는 request는 초기화  
	   FoodDAO dao=new FoodDAO();
	   List<FoodCategoryVO> cList=dao.foodCategoryData();
	   request.setAttribute("cList", cList);
	   request.setAttribute("main_jsp", "../food/category.jsp");
	   /*
	    *   공유 (request) : include 
	    *   데이터 출력 => main(X) => category.jsp
	    *   main은 출력된 category.jsp만 include한다 
	    */
	   return "../main/main.jsp";
   }
   @RequestMapping("food/find.do")
   public String food_find(HttpServletRequest request,HttpServletResponse response)
   {
	   // 지역받기 
		String[] gus = { "","강서구", "양천구", "구로구", "마포구", "영등포구", "금천구", "은평구", "서대문구",
		"동작구", "관악구", "종로구", "중구", "용산구", "서초구", "강북구", "성북구", "도봉구", "동대문구", "성동구",
		"강남구", "노원구", "중랑구", "광진구", "송파구", "강동구" };
	   try
	   {
		   request.setCharacterEncoding("UTF-8");
	   }catch(Exception ex) {}
	   
	   String loc=request.getParameter("loc");
	   if(loc==null)
		   loc="강서구";
	   System.out.println("loc="+loc);
	   int locno=0;
	   for(int i=0;i<gus.length;i++)
	   {
		   if(gus[i].equals(loc))
		   {
			   locno=i;
			   break;
		   }
	   }
	   
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   // DB연동 
	   FoodDAO dao=new FoodDAO();
	   List<FoodLocationVO> list=dao.foodFindData(curpage, locno);
	   int totolpage=dao.foodLoactionTotalPage(locno);
	   int count=dao.foodLocationCountData(locno);
	   
	   request.setAttribute("curpage", curpage);
	   request.setAttribute("totalpage", totolpage);
	   request.setAttribute("count", count);
	   request.setAttribute("list", list);
	   request.setAttribute("loc", loc);
	   request.setAttribute("main_jsp", "../food/find.jsp");
	   return "../main/main.jsp";
   }
}
