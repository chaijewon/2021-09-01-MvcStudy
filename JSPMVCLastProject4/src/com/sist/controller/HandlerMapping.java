package com.sist.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;
// XML 파싱 (SAX) => MyBatis/Spring => XML에서 에러가 발생 => sax
// XML에서 에러가 발생하면 => 100% ==> 읽기 전용 
/*
 *   <a> startElement => qName = a
 *    <b> startElement => qName = b
 *     <c> startElement => qName = c
 *      aaa characters()
 *     </c> endElement
 *    </b> endElement
 *   </a> endElement
 *   
 *   <beans>
 *     <context:component-scan base-package="com.sist.dao"/>
 *     <context:component-scan base-package="com.sist.model"/>
 *   </beans>
 */
public class HandlerMapping extends DefaultHandler{
     public List<String> list=new ArrayList<String>();

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// qName(태그명) , attributes(속성읽기)
		try
		{
			if(qName.equals("component-scan"))
			{
				String pack=attributes.getValue("base-package");
				System.out.println(pack);
				list.add(pack);
			}
		}catch(Exception ex) {}
	}
     
}
