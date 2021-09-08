package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class RecipeModel {
	   @RequestMapping("recipe/recipe_main.do")
	   public String recipe_main(HttpServletRequest request,HttpServletResponse response)
	   {
		   request.setAttribute("main_jsp", "../recipe/recipe_main.jsp");
		   return "../main/main.jsp";
	   }
}
