package com.sist.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SeoulMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void seoulAttractions()
	{
		try
		{
			for(int i=1;i<=35;i++)
			{
			   Document doc=Jsoup.connect("https://korean.visitseoul.net/attractions?curPage="+i).get();
			   
			}
		}catch(Exception ex){}
	}
	public void seoulHotel()
	{
		try
		{
			for(int i=1;i<=33;i++)
			{
			   Document doc=Jsoup.connect("https://korean.visitseoul.net/hotels?curPage="+i).get();
			   
			}
		}catch(Exception ex){}
	}
	public void seoulNature()
	{
		try
		{
			for(int i=1;i<=14;i++)
			{
			   Document doc=Jsoup.connect("https://korean.visitseoul.net/nature?curPage="+i).get();
			   
			}
		}catch(Exception ex){}
	}

}
