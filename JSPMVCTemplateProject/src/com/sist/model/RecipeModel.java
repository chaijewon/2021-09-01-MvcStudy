package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@Controller
public class RecipeModel {
  @RequestMapping("recipe/list.do")
  public String recipe_list(HttpServletRequest request,HttpServletResponse response)
  {
	  String page=request.getParameter("page");
	  if(page==null)
		  page="1";
	  // 현재페이지 
	  int curpage=Integer.parseInt(page);
	  // 페이지에 해당되는 데이터 읽기 
	  FoodDAO dao=new FoodDAO();
	  List<FoodVO> list=dao.foodListData(curpage);
	  // 총페이지 읽기 
	  int totalpage=dao.foodTotalPage();
	  
	  final int BLOCK=5;
	  /*
	   *     startPage   endPage
	   *      |           |
	   *   < [1][2][3][4][5] >
	   *                        startPage   endPage
	   *                     |  |           |
	   *                     < [6][7][8][9][10] >
	   */
	  int startPage=(((curpage-1)/BLOCK)*BLOCK)+1;
	  //                 1-1/BLOCK => 0*5 => 0  ==>  1
	  //                 6-1/5 => 1*5 => 5 +1 6
	  //                 10/5 ==> 2*5 => 10+1 ===> 11
	  int endPage=(((curpage-1)/BLOCK)*BLOCK)+BLOCK; // ==> 5 ==> 
	  //             6-1/5 => 1*BLOCK => 5+5 => 10
	  //   totalpage=13 ==> endPage => 15
	  
	  if(endPage>totalpage)
		  endPage=totalpage;
	  
	  // list.jsp전송
	  request.setAttribute("curpage", curpage);
	  request.setAttribute("totalpage", totalpage);
	  request.setAttribute("list", list);
	  request.setAttribute("BLOCK", BLOCK);
	  request.setAttribute("startPage", startPage);
	  request.setAttribute("endPage", endPage);
	  
	  request.setAttribute("main_jsp", "../recipe/list.jsp");
	  return "../main/main.jsp";
  }
}
