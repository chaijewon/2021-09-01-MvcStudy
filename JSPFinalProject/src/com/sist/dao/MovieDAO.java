package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;

import com.sist.vo.MovieVO;

import javax.naming.*;
public class MovieDAO {
   private Connection conn;
   private PreparedStatement ps;
   private static MovieDAO dao; // new (메모리 누적) , static은 한번만 생성(메모리 1개) 
   // 메모리 누수 방지 
   // => VO => 일반 데이터형으러 사용 
   // Spring => new(VO) => 결합성을 낮게 (결합성;영향력) 
   // Connection주소 얻기
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
   // Connection반환 
      public void disConnection()
	  {
		  try
		  {
			  if(ps!=null) ps.close();
			  if(conn!=null) conn.close();
		  }catch(Exception ex) {}
	  }
   // 싱글턴 => DAO를 한번만 생성할 수 있게 만든다 
      public static MovieDAO newInstance()
      {
    	  if(dao==null) // 생성이 안된 상태
    		  dao=new MovieDAO();
    	  // 생성이 된 상태라면 => 기존에 생성된 dao를 사용한다
    	  return dao; // 서버는 게속 가동 
      }
   // 기능 
   // 1. 상영 출력  cno=1(상영) cno=2(개봉)  movieListData(1,1)
      public List<MovieVO> movieListData(int cno,int page)
      {
    	  List<MovieVO> list=new ArrayList<MovieVO>();
    	  try
    	  {
    		  getConnection();
    		  String sql="SELECT mno,poster,title,num "
    				    +"FROM (SELECT mno,poster,title,rownum as num "
    				    +"FROM (SELECT mno,poster,title "
    				    +"FROM project_movie WHERE cno=? ORDER BY mno ASC)) "
    				    +"WHERE num BETWEEN ? AND ?";
    		 ps=conn.prepareStatement(sql);
    		 int rowSize=12;
    		 int start=(rowSize*page)-(rowSize-1);
    		 int end=rowSize*page;
    		 ps.setInt(1, cno);
    		 ps.setInt(2, start);
    		 ps.setInt(3, end);
    		 
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
    	  }
    	  catch(Exception ex)
    	  {
    		  ex.printStackTrace();
    	  }
    	  finally
    	  {
    		  disConnection();
    	  }
    	  return list;
      }
      
      public int movieTotalPage(int cno)
      {
    	  int total=0;
    	  try
    	  {
    		  getConnection();
    		  String sql="SELECT CEIL(COUNT(*)/12.0) FROM project_movie "
    				    +"WHERE cno=?";
    		  ps=conn.prepareStatement(sql);
    		  ps.setInt(1,cno);
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
   // 2. 상영영화 상세보기
      /*
       *  private double score;
    private String title,grade,reserve,genre,time,regdate,director,actor,
           showUser,poster,story,key;
	
       */
      public MovieVO movieDetailData(int mno)
      {
    	  MovieVO vo=new MovieVO();
    	  try
    	  {
    		  getConnection();
    		  String sql="SELECT mno,title,score,grade,reserve,genre,time,"
    				    +"regdate,director,actor,showUser,poster,story,key "
    				    +"FROM project_movie "
    				    +"WHERE mno=?";
    		  ps=conn.prepareStatement(sql);
    		  ps.setInt(1, mno);
    		  ResultSet rs=ps.executeQuery();
    		  rs.next();
    		  vo.setMno(rs.getInt(1));
    		  vo.setTitle(rs.getString(2));
    		  vo.setScore(rs.getDouble(3));
    		  vo.setGrade(rs.getString(4));
    		  vo.setReserve(rs.getString(5));
    		  vo.setGenre(rs.getString(6));
    		  vo.setTime(rs.getString(7));
    		  vo.setRegdate(rs.getString(8));
    		  vo.setDirector(rs.getString(9));
    		  vo.setActor(rs.getString(10));
    		  vo.setShowUser(rs.getString(11));
    		  vo.setPoster(rs.getString(12));
    		  vo.setStory(rs.getString(13));
    		  vo.setKey(rs.getString(14));
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
   // 5. 영화 예매 , 영화 추천 
}





