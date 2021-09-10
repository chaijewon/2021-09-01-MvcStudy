package com.sist.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SeoulMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        SeoulMain sm=new SeoulMain();
        //sm.seoulAttractions();
        //sm.seoulNature();
        sm.seoulHotel();
	}
	/*
	 *  <ul class="article-list">
					<li class="item">
							<!-- 2020 웹접근성 -->
							<a href="/attractions/양화진외국인선교사묘원_/1153" target="_self"  title="페이지 이동">
							
								<div class="thumb" style="background-image:url(/comm/getImage?srvcId=POST&amp;parentSn=1153&amp;fileTy=POSTTHUMB&amp;fileNo=2&amp;thumbTy=M)">
									</div>
								<div class="infor-element">
									<div class="infor-element-inner">
										<span class="title">양화진외국인선교사묘원</span>
										<span class="small-text text-dot-d">
											양화진외국인선교사묘원은 서울 마포구 합정동에 위치한 외국인 선교사들의 공동묘지이다.</span>
										<span class="trip-ico">
											<img src="https://www.tripadvisor.co.kr/img/cdsi/img2/ratings/traveler/5.0-20215-5.svg" alt="평점:5.0">
											<span class="trip-text">14 reviews</span>
										</span>
										<span class="view"></span>
									</div>
								</div>
							</a>
						</li>
	 */
	/*
	 *   <div class="detial-cont-element active"><!-- 09-26 detial-cont-element 추가 -->
					<div class="detail-map-infor first border"><!-- 09-26 border 추가 -->


				<dl>
						<dt>전화번호</dt>
						<dd>02-541-3525</dd>
					</dl>
				<dl>
						<dt>팩스번호</dt>
						<dd>02-541-3526</dd>
					</dl>
				<dl>
						<dt>웹사이트</dt>
						<dd>
							<a href="http://horimmuseum.org/sinsa/" target="_blank" title="새창열림">웹사이트 보기<span class="ion-ios-arrow-thin-right"></span>
							</a>
						</dd>
					</dl>
				<dl>
						<dt>이용시간</dt>
						<dd>AM 10:30 ~ PM 06:00 (입장 마감 PM 5:00)</dd>
					</dl>
				<dl>
						<dt>휴무일</dt>
						<dd>-일
<br>-1월 1일
<br>-설날, 추석 연휴</dd>
					</dl>
				<dl>
						<dt>운영 요일</dt>
						<dd>월~토</dd>
					</dl>
				<!--
				
		 -->
				<dl>
						<dt>이용요금</dt>
						<dd>○ 개인
				<br>-일반 : 8,000원
				<br>-청소년·장애인: 5,000원
				<br>-경로우대: 5,000원
				<br>○ 단체(20인 이상)
				<br>-일반 : 5,000원
				<br>-청소년·장애인: 3,000원
				<br>* 단체는 20인 이상이며, 전화로 문의 및 예약해주시기를 바랍니다.</dd>
									</dl>
								<dl>
										<dt>이용시설안내</dt>
										<dd>뮤지엄숍, 커피숍, 휴식공간 등</dd>
									</dl>
								</div>
							</div><!--// detail-map-infor -->
							div class="detail-map-infor">
			<dl>
				<dt>주소</dt>
				<dd>
					06021&nbsp;&nbsp;서울 강남구 도산대로 317 (신사동, 호림아트센터 1빌딩)&nbsp;&nbsp;</dd>
			</dl>
			<dl>
	 */
	public void seoulAttractions()
	{
		try
		{
			int k=1;
			for(int i=1;i<=35;i++)
			{
			   Document doc=Jsoup.connect("https://korean.visitseoul.net/attractions?curPage="+i).get();
			   Elements poster=doc.select("ul.article-list li.item div.thumb");
			   Elements comment=doc.select("ul.article-list li.item div.infor-element span.text-dot-d");
			   Elements link=doc.select("ul.article-list li.item a");
			   Elements title=doc.select("ul.article-list li.item div.infor-element span.title");
			   for(int j=0;j<poster.size();j++)
			   {
				   System.out.println("번호:"+k);
				   System.out.println(title.get(j).text());
				   System.out.println(poster.get(j).attr("style"));
				   System.out.println(comment.get(j).text());
				   System.out.println(link.get(j).attr("href"));
				   Document doc2=Jsoup.connect("https://korean.visitseoul.net"+link.get(j).attr("href")).get();
				   Element address=doc2.select("div.detail-map-infor:eq(1) dl dd").get(0);
				   System.out.println("주소:"+address.text());
				   System.out.println("=========================================================");
			       k++;
			   }
			}
		}catch(Exception ex){}
	}
	/*
	 * <div class="wide-slide owl-carousel">
							<div class="item" style="background-image:url(http://media-cdn.tripadvisor.com/media/photo-s/10/f5/55/88/entrance--v17513728.jpg)"></div>
							<div class="item" style="background-image:url(http://media-cdn.tripadvisor.com/media/photo-s/10/f5/55/0f/cheonsan-chinese-restaurant--v175.jpg)"></div>
							<div class="item" style="background-image:url(http://media-cdn.tripadvisor.com/media/photo-s/10/f5/54/d8/maestro-bar--v17513957.jpg)"></div>
							<div class="item" style="background-image:url(http://media-cdn.tripadvisor.com/media/photo-s/10/f5/54/84/imperial-suite--v17513921.jpg)"></div>
							<div class="item" style="background-image:url(http://media-cdn.tripadvisor.com/media/photo-s/10/f5/55/65/lobby--v17514021.jpg)"></div>
							</div>
				</div>
	 */
	public void seoulHotel()
	{
		try
		{
			int k=1;
			for(int i=1;i<=33;i++)
			{
			   Document doc=Jsoup.connect("https://korean.visitseoul.net/hotels?curPage="+i).get();
			   Elements poster=doc.select("ul.article-list li.item div.thumb img");
			   Elements link=doc.select("ul.article-list li.item a");
			   Elements title=doc.select("ul.article-list li.item div.infor-element span.title");
			   Elements score=doc.select("div.infor-element-inner span.trip-ico img");//
			   
			   for(int j=0;j<poster.size();j++)
			   {
				   System.out.println("번호:"+k);
				   System.out.println(title.get(j).text());
				   System.out.println(poster.get(j).attr("src"));
				   System.out.println(link.get(j).attr("href"));
				   System.out.println(score.get(j).attr("alt"));
				   Document doc2=Jsoup.connect("https://korean.visitseoul.net"+link.get(j).attr("href")).get();
				   Element address=doc2.select("div.detail-map-infor dl dd").get(0);
				   Elements images=doc2.select("div.wide-slide div.item");
				   for(int m=0;m<images.size();m++)
				   {
					   System.out.println(images.get(m).attr("style"));
				   }
				   System.out.println("주소:"+address.text());
				   
				   System.out.println("=========================================================");
			       k++;
			   }
			}
		}catch(Exception ex){}
	}
	public void seoulNature()
	{
		try
		{
			int k=1;
			for(int i=1;i<=14;i++)
			{
			   Document doc=Jsoup.connect("https://korean.visitseoul.net/nature?curPage="+i).get();
			   
			   Elements poster=doc.select("ul.article-list li.item div.thumb");
			   Elements comment=doc.select("ul.article-list li.item div.infor-element span.text-dot-d");
			   Elements link=doc.select("ul.article-list li.item a");
			   Elements title=doc.select("ul.article-list li.item div.infor-element span.title");
			   for(int j=0;j<poster.size();j++)
			   {
				   System.out.println("번호:"+k);
				   System.out.println(title.get(j).text());
				   System.out.println(poster.get(j).attr("style"));
				   System.out.println(comment.get(j).text());
				   System.out.println(link.get(j).attr("href"));
				   Document doc2=Jsoup.connect("https://korean.visitseoul.net"+link.get(j).attr("href")).get();
				   Element address=doc2.select("div.detail-map-infor:eq(1) dl dd").get(0);
				   System.out.println("주소:"+address.text());
				   System.out.println("=========================================================");
			       k++;
			   }
			}
		}catch(Exception ex){}
	}

}
