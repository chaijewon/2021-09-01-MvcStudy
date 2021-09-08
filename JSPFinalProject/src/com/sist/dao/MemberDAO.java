package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;

import com.sist.vo.ZipcodeVO;

import javax.naming.*;
public class MemberDAO {
  private Connection conn;
  private PreparedStatement ps;
  private static MemberDAO dao; // 싱글턴 패턴 (static:메모리 공간을 한개만 사용이 가능) => 재사용 
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
  public static MemberDAO newInstance()
  {
	  if(dao==null) // 미생성시에는 
		  dao=new MemberDAO();
	  return dao; // 이미 만들어진 dao객체를 사용한다 
		  
  }
  // 기능 => 아이디 중복 체크
  public int memberidCheck(String id)
  {
	  int count=0;
	  try
	  {
		  getConnection();
		  String sql="SELECT COUNT(*) FROM project_member "
				    +"WHERE id=?";
		  // count=0 (사용가능한 ID), count=1 (사용중인 ID)
		  ps=conn.prepareStatement(sql);
		  ps.setString(1,id);
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  count=rs.getInt(1);
		  rs.close();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  disConnection();
	  }
	  return count;
  }
  // 기능 => 우편번호 검색 
  public List<ZipcodeVO> postfind(String dong)
  {
	  List<ZipcodeVO> list=new ArrayList<ZipcodeVO>();
	  try
	  {
		  getConnection();
		  String sql="SELECT zipcode,sido,gugun,dong,NVL(bunji,' ') "
				    +"FROM zipcode "
				    +"WHERE dong LIKE '%'||?||'%'";
		  ps=conn.prepareStatement(sql);
		  ps.setString(1,dong);
		  ResultSet rs=ps.executeQuery();
		  while(rs.next())
		  {
			  ZipcodeVO vo=new ZipcodeVO();
			  vo.setZipcode(rs.getString(1));
			  vo.setSido(rs.getString(2));
			  vo.setGugun(rs.getString(3));
			  vo.setDong(rs.getString(4));
			  vo.setBunji(rs.getString(5));
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
  public int postfindCount(String dong)
  {
	  int count=0;
	  try
	  {
		  getConnection();
		  String sql="SELECT COUNT(*) "
				    +"FROM zipcode "
				    +"WHERE dong LIKE '%'||?||'%'";
		  ps=conn.prepareStatement(sql);
		  ps.setString(1,dong);
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  count=rs.getInt(1);
		  rs.close();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  disConnection();
	  }
	  return count;
  }
  // 실제 회원가입 
  // 로그인 
  // 회원수정 
  // 회원탈퇴
  // 아이디찾기 , 비밀번호찾기 
}
