package com.sist.manager;
// data.go.kr (XML) , 카페/블로그(XML) => XML/JSON/CSV => XML/JSON => JSON(JavaScript)
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

// http://newssearch.naver.com/search.naver?where=rss&query=%EB%A0%88%EC%8B%9C%ED%94%BC
import java.net.*;
public class NewsManager {
   public static void main(String[] args) {
	  NewsManager n=new NewsManager();
	  List<Item> list=n.newsSearch("코로나");
	  for(Item i:list)
	  {
		  System.out.println(i.getTitle());
	  }
   }
   public List<Item> newsSearch(String fd)
   {
	   List<Item> list=new ArrayList<Item>();
	   try
	   {
	      // fd : 사용자가 보내준 검색어 
	      String strUrl="http://newssearch.naver.com/search.naver?where=rss&query="
			            + URLEncoder.encode(fd, "UTF-8");
	      URL url=new URL(strUrl);
	      // XML 파싱 ( XML태그 <==> 클래스 연결 ) => 언마샬
	      // 클래스객체 값 ==> XML태그 변환 => 마샬
	      JAXBContext jb=JAXBContext.newInstance(Rss.class);
	      Unmarshaller un=jb.createUnmarshaller();
	      Rss rss=(Rss)un.unmarshal(url);
	      list=rss.getChannel().getItem();
	      // 카페 / 블로그 (다음/네이버) => 추천 (XML)
	   }catch(Exception ex) {}
	   
	   return list;
	   
   }
}
