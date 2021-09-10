package com.sist.xml;

import javax.xml.bind.annotation.XmlRootElement;
/*
 *   클래스 객체 <===> XML태그 매칭 (빅데이터=AI)  
 *            bind
 *   <rss>      태그와 태그사이에 태그 => 클래스  Rss
 *     <channel>                         Channel
 *       </item>
 *       <item>   ===> Item
 *         <title>aaa</title> String
 *         <author>bbb</author> String
 *       </item>
 *     </channel>
 *   </rss>
 *   
 *   XML태그 ==> 클래스  (언마샬)
 *   클래스 ==> XML파일  (마샬)
 *   ============================ OXM ==> jaxb.jar ==> 챗봇
 */
@XmlRootElement
public class Rss {
    private Channel channel=new Channel();

	public Channel getChannel() {
		return channel;
	}
	
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
  
}
