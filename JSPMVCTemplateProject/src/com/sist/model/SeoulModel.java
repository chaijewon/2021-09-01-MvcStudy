package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class SeoulModel {
  @RequestMapping("seoul_info/weather.do")
  public String seoul_info(HttpServletRequest request,HttpServletResponse response)
  {
	  try
	  {
		  Document doc=Jsoup.connect("https://korean.visitseoul.net/weather").get();
		  Element section=doc.selectFirst("section#content");
		  String w=section.html();
		  w=w.replace("img src=\"", "img src=\"https://korean.visitseoul.net");
		  w=w.replace("제공 : 케이웨더(Kweather)", "");
		  request.setAttribute("weather", w);
		  
	  }catch(Exception ex){}
	  request.setAttribute("main_jsp", "../seoul_info/weather.jsp");
	  return "../main/main.jsp";
  }
}
