package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;

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
}
