package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;

import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;
import com.sist.vo.ReserveVO;
import com.sist.vo.SeoulHotelVO;
import com.sist.vo.SeoulLocationVO;
import com.sist.vo.SeoulNatureVO;

import javax.naming.*;
public class FoodDAO {
	  private Connection conn;
	  private PreparedStatement ps;
	  private static FoodDAO dao; // 싱글턴 패턴 (static:메모리 공간을 한개만 사용이 가능) => 재사용 
	  // Connection 주소 읽기 => Connection객체를 미리생성 => 생성시간을 줄여서 속도가 빠르게 만든다 
	  // 갯수 지정을 했기에 Connection객체 관리가 편하다 => 모든 웹사이트 개발는 95%가 DBCP를 사용한다 
	  // Connection 객체 얻기 
	  public void getConnection()
	  {
		  try
		  {
			  Context init=new InitialContext(); // 저장된 위치에 접근 
			  // JNDI (java naming directory interface)
			  Context c=(Context)init.lookup("java://comp//env");
			  DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			  conn=ds.getConnection();
		  }catch(Exception ex) 
		  {
			  ex.printStackTrace();
		  }
	  }
	  // 사용후 반환 -> 다른사람이 재사용이 가능하게 만든다 
	  public void disConnection()
	  {
		  try
		  {
			  if(ps!=null) ps.close();
			  if(conn!=null) conn.close();
		  }catch(Exception ex) {}
	  }
	  // 싱글턴 패턴 
	  public static FoodDAO newInstance()
	  {
		  if(dao==null) // 미생성시에는 
			  dao=new FoodDAO();
		  return dao; // 이미 만들어진 dao객체를 사용한다 
			  
	  }
	  // 기능 => 테이블단위 
	  public List<CategoryVO> foodCategoryData()
	  {
		  System.out.println("DAO연결");
		  List<CategoryVO> list=new ArrayList<CategoryVO>();
		  try
		  {
			  getConnection();
			  String sql="SELECT cno,title,subject,poster "
					    +"FROM project_food_category";
			  ps=conn.prepareStatement(sql);
			  ResultSet rs=ps.executeQuery();
			  while(rs.next())
			  {
				  CategoryVO vo=new CategoryVO();
				  vo.setCno(rs.getInt(1));
				  vo.setTitle(rs.getString(2));
				  vo.setSubject(rs.getString(3));
				  vo.setPoster(rs.getString(4));
				  list.add(vo);
			  }
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return list;
	  }
	  // 카테고리별 목록 읽기
	  public List<FoodVO> foodCategoryListData(int cno)
	  {
		  List<FoodVO> list=new ArrayList<FoodVO>();
		  try
		  {
			  getConnection();
			  String sql="SELECT no,poster,name,tel,address,type,score "
					    +"FROM project_food_house "
					    +"WHERE cno=?";
			  ps=conn.prepareStatement(sql);
			  ps.setInt(1, cno);
			  ResultSet rs=ps.executeQuery();
			  while(rs.next())
			  {
				  FoodVO vo=new FoodVO();
				  vo.setNo(rs.getInt(1));
				  String poster=rs.getString(2);// 이미지 5개를 묶어서 저장 (1개만 가지고 온다)
				  poster=poster.substring(0,poster.indexOf("^"));
				  poster=poster.replace("#","&");
				  vo.setPoster(poster);
				  vo.setName(rs.getString(3));
				  vo.setTel(rs.getString(4));
				  String addr=rs.getString(5);
				  addr=addr.substring(0,addr.lastIndexOf("지"));// 길 /지번
				  vo.setAddress(addr);
				  vo.setType(rs.getString(6));
				  vo.setScore(rs.getDouble(7));
				  list.add(vo);
			  }
			  
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return list;
	  }
	  // 카테고리제목 읽기 
	  public CategoryVO foodCategoryInfoData(int cno)
	  {
		  CategoryVO vo=new CategoryVO();
		  try
		  {
			  getConnection();
			  String sql="SELECT title,subject FROM project_food_category "
					    +"WHERE cno=?";
			  ps=conn.prepareStatement(sql);
			  ps.setInt(1, cno);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  vo.setTitle(rs.getString(1));
			  vo.setSubject(rs.getString(2));
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return vo;
	  }
	  
	  public FoodVO foodCookieInfoData(int no)
	  {
		  FoodVO vo=new FoodVO();
		  try
		  {
			  getConnection();
			  String sql="SELECT no,name,poster "
					    +"FROM project_food_house "
					    +"WHERE no=?";
			  ps=conn.prepareStatement(sql);
			  ps.setInt(1, no);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  vo.setNo(rs.getInt(1));
			  vo.setName(rs.getString(2));
			  String poster=rs.getString(3);
			  poster=poster.substring(0,poster.indexOf("^"));
			  poster=poster.replace("#", "&");
			  vo.setPoster(poster);
			  rs.close();
		  }catch(Exception ex) 
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return vo;
	  }
	  // 맛집 상세보기 
	  /*
	   *    NO      NOT NULL NUMBER         
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
	  public FoodVO foodDetailData(int no)
	  {
		  FoodVO vo=new FoodVO();
		  try
		  {
			  getConnection();
			  String sql="SELECT no,poster,name,score,address,tel,type,"
					    +"price,parking,time,menu,good,soso,bad,cno "
					    +"FROM project_food_house "
					    +"WHERE no=?";
			  ps=conn.prepareStatement(sql);
			  ps.setInt(1, no);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  vo.setNo(rs.getInt(1));
			  vo.setPoster(rs.getString(2));
			  vo.setName(rs.getString(3));
			  vo.setScore(rs.getDouble(4));
			  vo.setAddress(rs.getString(5));
			  vo.setTel(rs.getString(6));
			  vo.setType(rs.getString(7));
			  vo.setPrice(rs.getString(8));
			  vo.setParking(rs.getString(9));
			  vo.setTime(rs.getString(10));
			  vo.setMenu(rs.getString(11));
			  vo.setGood(rs.getInt(12));
			  vo.setSoso(rs.getInt(13));
			  vo.setBad(rs.getInt(14));
			  vo.setCno(rs.getInt(15));
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return vo;
	  }
	  public List<SeoulLocationVO> foodSeoulLocationData(String addr)
	  {
		  List<SeoulLocationVO> list=new ArrayList<SeoulLocationVO>();
		  try
		  {
			  getConnection();
			  String sql="SELECT no,title,poster,rownum "
					    +"FROM (SELECT no,title,poster "
					    +"FROM seoul_location WHERE address LIKE '%'||?||'%' ORDER BY no ASC) "
					    +"WHERE rownum<=5";
			  ps=conn.prepareStatement(sql);
			  ps.setString(1, addr);
			  ResultSet rs=ps.executeQuery();
			  while(rs.next())
			  {
				  SeoulLocationVO vo=new SeoulLocationVO();
				  vo.setNo(rs.getInt(1));
				  vo.setTitle(rs.getString(2));
				  vo.setPoster(rs.getString(3));
				  list.add(vo);
			  }
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return list;
	  }
	  public List<SeoulHotelVO> foodSeoulHotelData(String addr)
	  {
		  List<SeoulHotelVO> list=new ArrayList<SeoulHotelVO>();
		  
		  try
		  {
			  getConnection();
			  String sql="SELECT no,name,poster,rownum "
					    +"FROM (SELECT no,name,poster "
					    +"FROM seoul_hotel WHERE address LIKE '%'||?||'%' ORDER BY no ASC) "
					    +"WHERE rownum<=5";
			  ps=conn.prepareStatement(sql);
			  ps.setString(1, addr);
			  ResultSet rs=ps.executeQuery();
			  while(rs.next())
			  {
				  SeoulHotelVO vo=new SeoulHotelVO();
				  vo.setNo(rs.getInt(1));
				  vo.setName(rs.getString(2));
				  vo.setPoster(rs.getString(3));
				  list.add(vo);
			  }
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return list;
	  }
	  public List<SeoulNatureVO> foodSeoulNatureData(String addr)
	  {
		  List<SeoulNatureVO> list=new ArrayList<SeoulNatureVO>();
		  try
		  {
			  getConnection();
			  String sql="SELECT no,title,poster,rownum "
					    +"FROM (SELECT no,title,poster "
					    +"FROM seoul_nature WHERE address LIKE '%'||?||'%' ORDER BY no ASC) "
					    +"WHERE rownum<=5";
			  ps=conn.prepareStatement(sql);
			  ps.setString(1, addr);
			  ResultSet rs=ps.executeQuery();
			  while(rs.next())
			  {
				  SeoulNatureVO vo=new SeoulNatureVO();
				  vo.setNo(rs.getInt(1));
				  vo.setTitle(rs.getString(2));
				  vo.setPoster(rs.getString(3));
				  list.add(vo);
			  }
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return list;
	  }
	 // 맛집 예약 ===============================================
	  public List<FoodVO> reserveFoodListData()
	  {
		  List<FoodVO> list=new ArrayList<FoodVO>();
		  try
		  {
			  getConnection();
			  String sql="SELECT no,poster,name "
					    +"FROM project_food_house";
			  ps=conn.prepareStatement(sql);
			  ResultSet rs=ps.executeQuery();
			  while(rs.next())
			  {
				  FoodVO vo=new FoodVO();
				  vo.setNo(rs.getInt(1));
				  String image=rs.getString(2);
				  image=image.substring(0,image.indexOf("^"));
				  vo.setPoster(image);
				  vo.setName(rs.getString(3));
				  list.add(vo);
			  }
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return list;
	  }
	  // 날자에 저장된 시간을 읽어 온다 
	  public String dateGetTime(int no)
	  {
		  String rtime="";
		  try
		  {
			  getConnection();
			  String sql="SELECT tno FROM food_date WHERE no=?";
			  ps=conn.prepareStatement(sql);
			  ps.setInt(1, no);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  rtime=rs.getString(1);
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return rtime;
	  }
	  // 실제 시간 
	  public String realGetTime(int no)
	  {
		  String time="";
		  try
		  {
			  getConnection();
			  String sql="SELECT time FROM food_time WHERE no=?";
			  ps=conn.prepareStatement(sql);
			  ps.setInt(1, no);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  time=rs.getString(1);
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return time;
	  }
	  //  예매
	  /*
	   *    NO      NOT NULL NUMBER       
			ID               VARCHAR2(20) 
			FNO              NUMBER       
			RDAY    NOT NULL VARCHAR2(50) 
			RTIME   NOT NULL VARCHAR2(20) 
			INWON   NOT NULL NUMBER       
			ISCHECK          NUMBER
	   */
	  public void reserveOk(ReserveVO vo)
	  {
		  try
		  {
			  getConnection();
			  String sql="INSERT INTO food_reserve VALUES("
					    +"(SELECT NVL(MAX(no)+1,1) FROM food_reserve),"
					    +"?,?,?,?,?,0)";
			  ps=conn.prepareStatement(sql);
			  ps.setString(1, vo.getId());
			  ps.setInt(2, vo.getFno());
			  ps.setString(3, vo.getRday());
			  ps.setString(4, vo.getRtime());
			  ps.setInt(5, vo.getInwon());
			  
			  ps.executeUpdate();
			  
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
	  }
	  // 마이페이지
	  public List<ReserveVO> mypageData(String id)
	  {
		  List<ReserveVO> list=new ArrayList<ReserveVO>();
		  try
		  {
			  getConnection();
			  String sql="SELECT no,fno,rday,rtime,inwon,ischeck, "
					    +"(SELECT name FROM project_food_house WHERE project_food_house.no=food_reserve.no) as name,"
					    +"(SELECT address FROM project_food_house WHERE project_food_house.no=food_reserve.no) as address,"
					    +"(SELECT tel FROM project_food_house WHERE project_food_house.no=food_reserve.no) as tel "
					    +"FROM food_reserve "
					    +"WHERE id=?";
			  ps=conn.prepareStatement(sql);
			  ps.setString(1, id);
			  ResultSet rs=ps.executeQuery();
			  while(rs.next())
			  {
				  ReserveVO vo=new ReserveVO();
				  vo.setNo(rs.getInt(1));
				  vo.setFno(rs.getInt(2));
				  vo.setRday(rs.getString(3));
				  vo.setRtime(rs.getString(4));
				  vo.setInwon(rs.getInt(5));
				  vo.setIsCheck(rs.getInt(6));
				  vo.setName(rs.getString(7));
				  vo.setAddress(rs.getString(8));
				  vo.setTel(rs.getString(9));
				  list.add(vo);
			  }
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return list;
	  }
	  // 어드민페이지 
	  public List<ReserveVO> adminpageData()
	  {
		  List<ReserveVO> list=new ArrayList<ReserveVO>();
		  try
		  {
			  getConnection();
			  String sql="SELECT no,fno,rday,rtime,inwon,ischeck,id "
					    +"FROM food_reserve";
			  ps=conn.prepareStatement(sql);
			  ResultSet rs=ps.executeQuery();
			  while(rs.next())
			  {
				  ReserveVO vo=new ReserveVO();
				  vo.setNo(rs.getInt(1));
				  vo.setFno(rs.getInt(2));
				  vo.setRday(rs.getString(3));
				  vo.setRtime(rs.getString(4));
				  vo.setInwon(rs.getInt(5));
				  vo.setIsCheck(rs.getInt(6));
				  vo.setId(rs.getString(7));
				  list.add(vo);
			  }
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return list;
	  }
}






