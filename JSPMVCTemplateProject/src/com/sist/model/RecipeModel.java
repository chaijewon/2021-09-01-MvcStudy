package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class RecipeModel {
  @RequestMapping("recipe/list.do")
  public String recipe_list(HttpServletRequest request,HttpServletResponse response)
  {
	  request.setAttribute("main_jsp", "../recipe/list.jsp");
	  return "../main/main.jsp";
  }
}
