package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import com.sist.vo.*;
public class MovieDAO {
	private Connection conn;
    private PreparedStatement ps;
    public void getConnection()
    {
    	// DBCP 소스코딩 (톰캣이 Connection객체를 만든다 => 만들어진 주소만 사용)
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
    public void disConnection()
    {
    	try
    	{
    		if(ps!=null) ps.close();
    		if(conn!=null) conn.close();
    	}catch(Exception ex) {}
    	
    }
    public List<MovieVO> movieRealData()
    {
    	List<MovieVO> list=new ArrayList<MovieVO>();
    	try
    	{
    		getConnection();
    		// 원하는 순서와 갯수만큼 잘라오기 => 인라인뷰 사용(rownum=순서를 변경)
    		String sql="SELECT mno,title,poster, rownum "
    				  +"FROM (SELECT mno,title,poster,cno  "
    				  +"FROM project_movie ORDER BY mno ASC) "
    				  +"WHERE cno=1 AND rownum<=9";
    		ps=conn.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next())
    		{
    			MovieVO vo=new MovieVO();
    			vo.setMno(rs.getInt(1));
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
    public List<MovieVO> movieSchData()
    {
    	List<MovieVO> list=new ArrayList<MovieVO>();
    	try
    	{
    		getConnection();
    		// 원하는 순서와 갯수만큼 잘라오기 => 인라인뷰 사용(rownum=순서를 변경)
    		String sql="SELECT mno,title,poster, rownum "
    				  +"FROM (SELECT mno,title,poster,cno  "
    				  +"FROM project_movie ORDER BY mno ASC) "
    				  +"WHERE cno=2 AND rownum<=9";
    		ps=conn.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next())
    		{
    			MovieVO vo=new MovieVO();
    			vo.setMno(rs.getInt(1));
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
    // 동영상 받기
    public String moviedDetailData(int mno)
    {
    	String key="";
    	try
    	{
    		getConnection();
    		String sql="SELECT key FROM project_movie "
    				  +"WHERE mno=?";
    		ps=conn.prepareStatement(sql);
    		ps.setInt(1, mno);
    		ResultSet rs=ps.executeQuery();
    		rs.next();
    		key=rs.getString(1);
    		rs.close();
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		disConnection();
    	}
    	return key;
    }
}
