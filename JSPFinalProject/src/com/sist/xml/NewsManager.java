package com.sist.xml;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import java.net.*;
//http://newssearch.naver.com/search.naver?where=rss&query=%EB%A0%88%EC%8B%9C%ED%94%BC
public class NewsManager {
    public List<Item> newsFindData(String fd)
    {
    	List<Item> list=new ArrayList<Item>();
    	try
    	{
    		JAXBContext jb=JAXBContext.newInstance(Rss.class);
    		String strUrl="http://newssearch.naver.com/search.naver?where=rss&query="
    				     +URLEncoder.encode(fd, "UTF-8");
    		URL url=new URL(strUrl);
    		Unmarshaller un=jb.createUnmarshaller();
    		Rss rss=(Rss)un.unmarshal(url);
    		list=rss.getChannel().getItem();
    		// 스프링 => XML , MyBatis => XML
    	}catch(Exception ex){}
    	return list;
    }
}
