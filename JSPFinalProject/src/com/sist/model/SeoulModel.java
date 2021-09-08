package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

// NewsModel , MemberModel , ReserveModel , RecommandModel , PageModel(mypage,adminpage)
// BoardModel , ReplyModel , NoticeModel
// 자바 => 재사용 
@Controller
public class SeoulModel {
	   @RequestMapping("seoul/seoul_main.do")
	   public String seoul_main(HttpServletRequest request,HttpServletResponse response)
	   {
		   request.setAttribute("main_jsp", "../seoul/seoul_main.jsp");
		   return "../main/main.jsp";
	   }
}





