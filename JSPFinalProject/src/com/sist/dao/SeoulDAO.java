package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;

import com.sist.vo.FoodVO;
import com.sist.vo.SeoulHotelVO;
import com.sist.vo.SeoulLocationVO;
import com.sist.vo.SeoulNatureVO;

import javax.naming.*;
public class SeoulDAO {
	  private Connection conn;
	  private PreparedStatement ps;
	  private static SeoulDAO dao;
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
	  public static SeoulDAO newInstance()
	  {
		  if(dao==null) // 미생성시에는 
			  dao=new SeoulDAO();
		  return dao; // 이미 만들어진 dao객체를 사용한다 
			  
	  }
	  
	  public SeoulLocationVO seoulLoactionMainData()
	  {
		  SeoulLocationVO vo=new SeoulLocationVO();
		  try
		  {
			  getConnection();
			  String sql="SELECT no,title,poster "
					    +"FROM seoul_location "
					    +"WHERE no=1";
			  ps=conn.prepareStatement(sql);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  vo.setNo(rs.getInt(1));
			  vo.setTitle(rs.getString(2));
			  vo.setPoster(rs.getString(3));
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
	  public SeoulHotelVO seoulHotelMainData()
	  {
		  SeoulHotelVO vo=new SeoulHotelVO();
		  try
		  {
			  getConnection();
			  String sql="SELECT no,name,poster "
					    +"FROM seoul_hotel "
					    +"WHERE no=1";
			  ps=conn.prepareStatement(sql);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  vo.setNo(rs.getInt(1));
			  vo.setName(rs.getString(2));
			  vo.setPoster(rs.getString(3));
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
	  public SeoulNatureVO seoulNatureMainData()
	  {
		  SeoulNatureVO vo=new SeoulNatureVO();
		  try
		  {
			  getConnection();
			  String sql="SELECT no,title,poster "
					    +"FROM seoul_nature "
					    +"WHERE no=1";
			  ps=conn.prepareStatement(sql);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  vo.setNo(rs.getInt(1));
			  vo.setTitle(rs.getString(2));
			  vo.setPoster(rs.getString(3));
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
	  // 명소 데이터 읽기
	  public List<SeoulLocationVO> seoulLocationData(int page)
	  {
		  List<SeoulLocationVO> list=new ArrayList<SeoulLocationVO>();
		  try
		  {
			  getConnection();
			  String sql="SELECT no,title,poster,num "
					    +"FROM (SELECT no,title,poster,rownum as num "
					    +"FROM (SELECT no,title,poster "
					    +"FROM seoul_location ORDER BY no ASC)) "
					    +"WHERE num BETWEEN ? AND ?";
			  ps=conn.prepareStatement(sql);
			  int rowSize=12;
			  int start=(rowSize*page)-(rowSize-1);
			  int end=rowSize*page;
			  ps.setInt(1, start);
			  ps.setInt(2, end);
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
	  public int seoulLocationTotalPage()
	  {
		  int total=0;
		  try
		  {
			  getConnection();
			  String sql="SELECT CEIL(COUNT(*)/12.0) FROM seoul_location";
		      ps=conn.prepareStatement(sql);
		      ResultSet rs=ps.executeQuery();
		      rs.next();
		      total=rs.getInt(1);
		      rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return total;
	  }
	  
	  
	     // 자연
		  public List<SeoulNatureVO> seoulNatureData(int page)
		  {
			  List<SeoulNatureVO> list=new ArrayList<SeoulNatureVO>();
			  try
			  {
				  getConnection();
				  String sql="SELECT no,title,poster,num "
						    +"FROM (SELECT no,title,poster,rownum as num "
						    +"FROM (SELECT no,title,poster "
						    +"FROM seoul_nature ORDER BY no ASC)) "
						    +"WHERE num BETWEEN ? AND ?";
				  ps=conn.prepareStatement(sql);
				  int rowSize=12;
				  int start=(rowSize*page)-(rowSize-1);
				  int end=rowSize*page;
				  ps.setInt(1, start);
				  ps.setInt(2, end);
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
		  public int seoulNatureTotalPage()
		  {
			  int total=0;
			  try
			  {
				  getConnection();
				  String sql="SELECT CEIL(COUNT(*)/12.0) FROM seoul_nature";
			      ps=conn.prepareStatement(sql);
			      ResultSet rs=ps.executeQuery();
			      rs.next();
			      total=rs.getInt(1);
			      rs.close();
			  }catch(Exception ex)
			  {
				  ex.printStackTrace();
			  }
			  finally
			  {
				  disConnection();
			  }
			  return total;
		  }
		  
		// 호텔
		  public List<SeoulHotelVO> seoulHotelData(int page)
		  {
			  List<SeoulHotelVO> list=new ArrayList<SeoulHotelVO>();
			  try
			  {
				  getConnection();
				  String sql="SELECT no,name,poster,num "
						    +"FROM (SELECT no,name,poster,rownum as num "
						    +"FROM (SELECT no,name,poster "
						    +"FROM seoul_hotel ORDER BY no ASC)) "
						    +"WHERE num BETWEEN ? AND ?";
				  ps=conn.prepareStatement(sql);
				  int rowSize=12;
				  int start=(rowSize*page)-(rowSize-1);
				  int end=rowSize*page;
				  ps.setInt(1, start);
				  ps.setInt(2, end);
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
		  public int seoulHotelTotalPage()
		  {
			  int total=0;
			  try
			  {
				  getConnection();
				  String sql="SELECT CEIL(COUNT(*)/12.0) FROM seoul_hotel";
			      ps=conn.prepareStatement(sql);
			      ResultSet rs=ps.executeQuery();
			      rs.next();
			      total=rs.getInt(1);
			      rs.close();
			  }catch(Exception ex)
			  {
				  ex.printStackTrace();
			  }
			  finally
			  {
				  disConnection();
			  }
			  return total;
		  }
	  
	  // 명소상세보기 
	  public SeoulLocationVO seoulLocDetailData(int no)
	  {
		  /* 
		   *  private int no;
              private String title,poster,msg,address;
		   */
		  SeoulLocationVO vo=new SeoulLocationVO();
		  try
		  {
			  getConnection();
			  String sql="SELECT no,title,poster,msg,address "
					    +"FROM seoul_location "
					    +"WHERE no=?"; //no => 중복이 없는 데이터
			  ps=conn.prepareStatement(sql);
			  ps.setInt(1, no);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  vo.setNo(rs.getInt("no")); // getInt(1)  getInt("no")(MyBatis)
			  vo.setTitle(rs.getString("title"));
			  vo.setPoster(rs.getString("poster"));
			  vo.setMsg(rs.getString("msg"));
			  vo.setAddress(rs.getString("address"));// 오버로딩
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
	  
	  // 해당구에 있는 맛집 (15)
	  public List<FoodVO> seoulFoodListData(String gu)
	  {
		  List<FoodVO> list=new ArrayList<FoodVO>();
		  try
		  {
			  getConnection();
			  String sql="SELECT no,poster,name,rownum "
					    +"FROM (SELECT no,poster,name "
					    +"FROM project_food_location WHERE address LIKE '%'||?||'%' ORDER BY no ASC) "
					    +"WHERE rownum<=15";
			  ps=conn.prepareStatement(sql);
			  ps.setString(1, gu);
			  ResultSet rs=ps.executeQuery();
			  while(rs.next())
			  {
				  FoodVO vo=new FoodVO();
				  vo.setNo(rs.getInt(1));
				  String image=rs.getString(2);
				  image=image.substring(0,image.indexOf("^"));
				  vo.setPoster(image.replace("#", "&"));
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
	  // 해당구에 잇는 호텔 (15)
	  public List<SeoulHotelVO> seoulHotelListData(String gu)
	  {
		  List<SeoulHotelVO> list=new ArrayList<SeoulHotelVO>();
		  try
		  {
			  getConnection();
			  String sql="SELECT no,poster,name,rownum "
					    +"FROM (SELECT no,poster,name "
					    +"FROM seoul_hotel WHERE address LIKE '%'||?||'%' ORDER BY no ASC) "
					    +"WHERE rownum<=15";
			  ps=conn.prepareStatement(sql);
			  ps.setString(1, gu);
			  ResultSet rs=ps.executeQuery();
			  while(rs.next())
			  {
				  SeoulHotelVO vo=new SeoulHotelVO();
				  vo.setNo(rs.getInt(1));
				  vo.setPoster(rs.getString(2).replace("#", "&"));
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
	  
	  // 호텔 상세보기
	  public SeoulHotelVO seoulHotelDetailData(int no)
	  {
		  SeoulHotelVO vo=new SeoulHotelVO();
		  try
		  {
			  getConnection();
			  String sql="SELECT no,poster,name,address,score,images "
					    +"FROM seoul_hotel "
					    +"WHERE no=?";
			  ps=conn.prepareStatement(sql);
			  ps.setInt(1, no);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  vo.setNo(rs.getInt(1));
			  vo.setPoster(rs.getString(2));
			  vo.setName(rs.getString(3));
			  vo.setAddress(rs.getString(4));
			  vo.setScore(rs.getDouble(5));
			  vo.setImages(rs.getString(6));
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
	     
}













