package com.sist.vo;
/*
 *   NO      NOT NULL NUMBER         
CNO              NUMBER         
POSTER  NOT NULL VARCHAR2(2000) 
NAME    NOT NULL VARCHAR2(300)  
SCORE   NOT NULL NUMBER(2,1)    
ADDRESS NOT NULL VARCHAR2(1000) 
TEL     NOT NULL VARCHAR2(20)   
TYPE    NOT NULL VARCHAR2(100)  
PRICE            VARCHAR2(100)  
PARKING          VARCHAR2(100)  
TIME             VARCHAR2(100)  
MENU             VARCHAR2(4000) 
GOOD             NUMBER         
SOSO             NUMBER         
BAD              NUMBER         
 */
public class FoodVO {
   private int no,cno,good,soso,bad;
   private String poster,name,address,tel,type,price,parking,time,menu;
   private double score;
   private String addr1,addr2;//오라클과 상관이 없다 => JSP출력 
   
	public String getAddr1() {
	    return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getSoso() {
		return soso;
	}
	public void setSoso(int soso) {
		this.soso = soso;
	}
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	   
}
