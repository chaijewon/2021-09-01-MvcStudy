package com.sist.controller;
import java.net.*;
import java.io.*;
public class FileConfig {
   public void display()
   {
	   try
		  {
			  URL url = this.getClass().getClassLoader().getResource(".");
			  File file = new File(url.toURI());
			  System.out.println(file.getPath());
		  }catch(Exception ex) {ex.printStackTrace();}
   }
   public static void main(String[] args) {
	   FileConfig f=new FileConfig();
	   f.display();
   }
}
