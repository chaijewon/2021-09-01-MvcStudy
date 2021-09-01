package com.sist.temp;

import com.sist.controller.Controller;
// class에서 사용되는 어노테이션은 => 메모리할당여부 
@Controller
public class A {
   public void display()
   {
	   System.out.println("A:display Call()...");
   }
}
