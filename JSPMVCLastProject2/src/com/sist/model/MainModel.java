package com.sist.model;
import javax.servlet.http.HttpServletRequest;

import com.sist.controller.*;
//POJO => 일반 클래스 => 클래스명 자유롭다 (메소드도 마음되로 사용이 가능)
// 프로젝트 => 클래스명을 전한다 , 메소드명을 정한다 (X) => 
public class MainModel {
   @RequestMapping("main/main.do")
   public String main_page(HttpServletRequest request)
   {
	   return "../main/main.jsp";
   }
   @RequestMapping("main/ko.do")
   public String main_ko(HttpServletRequest request)
   {
	   return "../main/ko.jsp";
   }
   @RequestMapping("main/ch.do")
   public String main_ch(HttpServletRequest request)
   {
	   return "../main/ch.jsp";
   }
   @RequestMapping("main/ja.do")
   public String main_ja(HttpServletRequest request)
   {
	   return "../main/ja.jsp";
   }
}
