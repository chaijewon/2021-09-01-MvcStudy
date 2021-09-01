package com.sist.temp;
import java.util.*;

import com.sist.controller.Controller;
/*
 *   package명 ==> 1.제외 (.java가 아닌 파일)
 *   => @Controller가 없는 것 제외 
 *   => 나머지 메모리 할당 => 자동 메모리 할당 (하지 않는 경우도 있다=프로그래머 호출 해서 사용)
 */
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try
        {
        	FileRead fr=new FileRead();
        	List<String> list=fr.fileReadData("com.sist.temp");
        	// 메모리 할당
        	for(String cls:list)
        	{
        		Class clsName=Class.forName(cls);
        		//클래스위에 Controller라는 어노테이션이 없다면(flase)
        		if(clsName.isAnnotationPresent(Controller.class)==false)
        		     continue;
        		Object obj=clsName.getDeclaredConstructor().newInstance();
        		System.out.println(obj);
        	}
        }catch(Exception ex){}
	}

}
