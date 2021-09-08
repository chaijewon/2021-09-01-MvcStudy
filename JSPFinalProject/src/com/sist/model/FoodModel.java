package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class FoodModel {
   @RequestMapping("food/food_main.do")
   public String food_main(HttpServletRequest request,HttpServletResponse response)
   {
	   request.setAttribute("main_jsp", "../food/food_main.jsp");
	   return "../main/main.jsp";
   }
}
