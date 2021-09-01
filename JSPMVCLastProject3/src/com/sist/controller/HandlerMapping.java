package com.sist.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;
/*
 *   XML 파싱 
 *   =======
 *    jaxb => 클래스와 XML을 연결 => 데이터를 읽어온다   
 *    jaxp => 실제 파싱 
 *    ======
 *      dom => DOCUMENT OBJECT Model 
 *      sax => Read Only 읽기 전용 (Simple API for XML) => 스프링 , 마이바티스 
 *      
 *      <?xml version="1.0"?> => 자동 호출되는 메소드 => startDocument()
 *      <beans>               => startElement()
 *        <component-scan/>   => startElement() , endElement()
 *      </beans>              => endElement()
 *      ======================== endDocument()
 *      
 */
public class HandlerMapping extends DefaultHandler{
    List<String> list=new ArrayList<String>();
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		try
		{
			// qName => 태그명 
			if(qName.equals("component-scan"))
			{
				// 속성값 읽기 
				String pack=attributes.getValue("base-package");
				System.out.println(pack);
				list.add(pack);
			}
		}catch(Exception ex){}
	}
}






