package com.sist.temp;
import java.io.*;
import java.net.URL;
// package가 등록될때 처리
import java.util.*;
public class FileRead {
	// pack="com//sist//temp"
   public static void main(String[] args) {
	   FileRead fr=new FileRead();
	   fr.fileReadData("com.sist.temp");
	   // 메모리 할당을 자동 처리 하지 않는 클래스 => XML처리 클래스 , VO클래스 (프로그래머가 메모리 할당)
	                                       // VO는 데이터형 (int,double)=>지역변수로만 사용
   }
   public List<String> fileReadData(String pack)
   {
	   List<String> list=new ArrayList<String>();
	   try
	   {
		   // 맥 => 경로 (getReource())
		   URL url = this.getClass().getClassLoader().getResource(".");
		   System.out.println(url.toURI());
		   File file = new File(url.toURI());
		   String temp=file.getPath();
		   temp=temp.substring(0,temp.lastIndexOf("\\"));
		   temp=temp.substring(0,temp.lastIndexOf("\\"));
		   System.out.println(temp);
		   String path=temp+"\\src\\";
		   path=path+pack.replace(".", "\\");
		   File dir=new File(path);
		   //파일 목록을 읽는다 
		   File[] fList=dir.listFiles();
		   for(File f:fList)
		   {
			   //System.out.println(f.getName());
			   String name=f.getName();
			   String ext=name.substring(name.lastIndexOf(".")+1);
			   if(ext.equals("java"))
			   {
				   System.out.println(name);
				   String cls=name.substring(0,name.lastIndexOf("."));
				   String p=pack+"."+cls;
				   //System.out.println(p);
				   list.add(p);
			   }
			   
		   }
		   // C:\20210609-JspDev\jspStudy\JSPMVCLastProject3\src\com\sist\temp\A.java
	   }catch(Exception ex){}
	   return list;
   }
}
