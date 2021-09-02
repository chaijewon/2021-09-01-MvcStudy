package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
public class MovieDAO {
   private Connection conn;
   private PreparedStatement ps;
   // 주소 얻기
   public void getConnetion()
   {
	   try
	   {
		   Context init=new InitialContext();
		   Context c=(Context)init.lookup("java://comp//env");
		   DataSource ds=(DataSource)c.lookup("jdbc/oracle");
		   conn=ds.getConnection();
	   }catch(Exception ex) 
	   {
		   ex.printStackTrace();
	   }
   }
   // 반환
   public void disConnection()
   {
	   try
	   {
		   if(ps!=null) ps.close();
		   if(conn!=null) conn.close();
	   }catch(Exception ex) {}
   }
   // 목록 출력 
   public List<MovieVO> movieListData(int page)
   {
	   List<MovieVO> list=new ArrayList<MovieVO>();
	   try
	   {
		   getConnetion();
		   String sql="SELECT mno,poster,title,num "
				     +"FROM (SELECT mno,poster,title,rownum as num "
				     +"FROM (SELECT mno,poster,title "
				     +"FROM daum_movie ORDER BY mno ASC)) "
				     +"WHERE num BETWEEN ? AND ?";
		   ps=conn.prepareStatement(sql);
		   // ?에 값을 채운다 
		   int rowSize=12;
		   int start=(rowSize*page)-(rowSize-1);
		   int end=rowSize*page;
		   ps.setInt(1, start);
		   ps.setInt(2, end);
		   
		   // 실행 
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   MovieVO vo=new MovieVO();
			   vo.setMno(rs.getInt(1));
			   vo.setPoster(rs.getString(2));
			   vo.setTitle(rs.getString(3));
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
   public int movieTotalPage()
   {
	   int total=0;
	   try
	   {
		   getConnetion();
		   String sql="SELECT CEIL(COUNT(*)/12.0) FROM daum_movie";
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
   // 상세보기 
   public MovieVO movieDetailData(int mno)
   {
	   MovieVO vo=new MovieVO();
	   try
	   {
		   getConnetion();
		   String sql="SELECT mno,poster,title,genre,grade,regdate "
				     +"FROM daum_movie "
				     +"WHERE mno=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, mno);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   vo.setMno(rs.getInt(1));
		   vo.setPoster(rs.getString(2));
		   vo.setTitle(rs.getString(3));
		   vo.setGenre(rs.getString(4));
		   vo.setGrade(rs.getString(5));
		   vo.setRegdate(rs.getString(6));
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








