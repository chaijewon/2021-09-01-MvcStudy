package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.MovieVO;

import java.util.*;
@Controller
public class MainModel {
   @RequestMapping("main/main.do")
   // @RequestMapping("main/main.do"),@GetMapping("main/main.do"),@PostMapping("main/main.do")
   public String main_main(HttpServletRequest request,HttpServletResponse response)
   {
	   MovieDAO dao=new MovieDAO(); // 한개의 메모리에서 사용 (싱글턴)
	   // 스프링 => 모든 클래스가 싱글턴을 사용한다 
	   List<MovieVO> rList=dao.movieRealData();
	   List<MovieVO> sList=dao.movieSchData();
	   request.setAttribute("rList", rList);
	   request.setAttribute("sList", sList);
	   
	   request.setAttribute("main_jsp", "../main/home.jsp");
	   return "../main/main.jsp";
   }
}





