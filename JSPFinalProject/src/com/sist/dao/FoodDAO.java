package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;

import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;

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
}






