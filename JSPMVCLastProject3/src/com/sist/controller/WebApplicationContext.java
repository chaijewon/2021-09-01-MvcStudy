package com.sist.controller;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.sist.temp.FileRead;

import java.io.*;
import java.util.*;
public class WebApplicationContext {
   public List<String> list=new ArrayList<String>();
   public static void main(String[] args) {
	  String path="C:\\20210609-JspDev\\jspStudy\\JSPMVCLastProject3\\WebContent\\WEB-INF\\application.xml";
	  WebApplicationContext wc=
			  new WebApplicationContext(path);
   } 
   // 파싱 => 이미 만들져 있다 => XML => DOM을 주로 사용한다 
   // XML,Annotation => 사용하는 목적 
   // XML => 클래스등록 , 패키지 등록 => 메모리 할당 목적 => DispacherServlet를 고정할 목적
   // Annotation => 찾기 
   public WebApplicationContext(String path)
   {
	   try
	   {
		   SAXParserFactory spf=SAXParserFactory.newInstance();
		   // 파싱기 
		   SAXParser sp=spf.newSAXParser();
		   // 파싱 
		   HandlerMapping hm=new HandlerMapping();
		   sp.parse(new File(path), hm); // startElement
		   List<String> mlist=hm.list;
		   System.out.println("====================");
		   FileRead fr=new FileRead();
		   for(String s:mlist)
		   {
			   System.out.println(s);
			   List<String> cList=fr.fileReadData(s);
			   for(String ss:cList)
			   {
				   System.out.println(ss);
				   list.add(ss);
			   }
		   }
	   }catch(Exception ex) {}
   }
}
