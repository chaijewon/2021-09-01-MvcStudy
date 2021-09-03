package com.sist.manager;

import javax.xml.bind.annotation.XmlRootElement;

// jaxb => 1.8에서만 사용이 가능 (이상버전에서는 지원하지 않는다) => 호환성이 가장 좋은 버전 
// 1.9이상 = 오라클 자체에서 제작 => 오라클 (형식:C/C++) => 유료
// 15,14... => 
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
