package com.sist.dao;
// Oracle => 한개만 사용 (5~6)  =>  고정IP (조별 => ip) 
// localhost => 조별(X) , 개인
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import com.sist.vo.*;
public class FoodDAO {
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
    // 데이터베이스 연결 
    /*
     *   1. 템플릿 추가 => 같은 프로젝트 공유 => 오너 (수정, 삭제,추가)
     *                 ============= 공동 작업
     *   2. 지정된 오라클 확인 
     */
    public List<FoodVO> foodListData(int page)
    {
    	List<FoodVO> list=new ArrayList<FoodVO>();
    	
    	return list;
    }
}






