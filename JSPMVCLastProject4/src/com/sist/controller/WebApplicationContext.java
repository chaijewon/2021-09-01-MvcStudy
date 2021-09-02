package com.sist.controller;
import java.io.File;
// 스프링의 원래 목적 => 클래스를 관리하는 것이다 (웹 전용(X), 클래스관계도가 복잡한 경우에 사용)
import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
public class WebApplicationContext {
    private List<String> list=new ArrayList<String>();

	public List<String> getList() {
		return list;
	}
   
	// 파싱
	public WebApplicationContext(String path)
	{
		System.out.println("WebApplication:"+path);
		try
		{
			SAXParserFactory spf=SAXParserFactory.newInstance();
			// 파서기를 가지고 온다 
			SAXParser sp=spf.newSAXParser();
			// JSON/XML/HTML => 등록된 데이터 읽기 => 파서 (태그:HTML)
			HandlerMapping hm=new HandlerMapping();
			sp.parse(new File(path), hm);//startElement() => 여는 태그마다 호출 
			List<String> pList=hm.list;
			XMLFileRead r=new XMLFileRead();
			for(String s:pList)
			{
				List<String> sList=r.fileGetJava(s);
				for(String ss:sList)
				{
					list.add(ss);
				}
			}
		}catch(Exception ex) {}
	}
}





