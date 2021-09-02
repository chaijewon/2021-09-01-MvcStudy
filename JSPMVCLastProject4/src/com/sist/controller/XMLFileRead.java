package com.sist.controller;
import java.util.*;
import java.io.*;
import java.net.*;
// XML => pack명만 전송 
public class XMLFileRead {
   public static void main(String[] args) {
	   XMLFileRead r=new XMLFileRead();
	   r.fileGetJava("com.sist.model");
	  //fileGetJava("com\\sist\\controller");// 폴더명 => \\ (.)
	  // C:\20210609-JspDev\jspStudy\JSPMVCLastProject4\src\com\sist\controller
   }
   // 패키지명을 받아서 => 메모리할당을 할 수 있게 변경 역할 
   public  List<String> fileGetJava(String pack)
   {
	   List<String> list=new ArrayList<String>();
	   try
	   {
		   //String path="C:\\20210609-JspDev\\jspStudy\\JSPMVCLastProject4\\src\\";
		   URL url=this.getClass().getClassLoader().getResource(".");
		   File file=new File(url.toURI());
		   String path=file.getPath();
		   //path=path.substring(0,path.lastIndexOf("\\"));
		   //path=path.substring(0,path.lastIndexOf("\\"));
		   path=path+"\\"+pack.replace(".", "\\");
		   System.out.println(path);
		   File dir=new File(path);
		   File[] files=dir.listFiles(); // 폴더안에 있는 모든 파일 읽기
		   for(File f:files)
		   {
			   //System.out.println(f.getName());
			   // 클래스의 메모리 할당 => 확장자 => .java
			   String name=f.getName();
			   String ext=name.substring(name.lastIndexOf(".")+1);
			   // 파일의 확장자만 자른다 
			   
			   if(ext.equals("class"))
			   {
				   System.out.println(name);
				   String temp=pack+"."+name.substring(0,name.lastIndexOf("."));
				   list.add(temp);
				   //System.out.println(temp);
				   /*Class clsName=Class.forName(temp);
				   Object obj=clsName.getDeclaredConstructor().newInstance();
				   System.out.println(obj);*/
			   }
		   }
		   
	   }catch(Exception ex){}
	   return list;
   }
}
