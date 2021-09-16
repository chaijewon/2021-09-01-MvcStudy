package com.sist.temp;
import java.util.*;
import java.sql.*;
// 값 => 1일마다 (예약이 가능한 시간)
// main() => Application (DBCP가 적용되지 않는다) => tomcat(웹)
/*
 *   영화 
 *   
 *   movie  ==> 상영가능한 극장 참조 
 *   theater => 예약가능 날짜 
 *   date => 시간 
 *   time 
 */
public class TestDAO {
    private Connection conn;
    private PreparedStatement ps;
    private final String URL="jdbc:oracle:thin:@211.238.142.181:1521:XE";
    public TestDAO()
    {
    	try
    	{
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    	}catch(Exception ex){}
    }
    // 예약 ==> 화면 (mypage) ==> 예약 정보를 볼 수 있게 만든다 
    // 예약완료 ==> hong  ==> 
    public void getConnection()
    {
    	try
    	{
    		conn=DriverManager.getConnection(URL,"hr","happy");
    	}catch(Exception ex) {}
    }
    public void disConnection()
    {
    	try
    	{
    	   if(ps!=null) ps.close();
    	   if(conn!=null) conn.close();
    	}catch(Exception ex) {}
    }
    public void timeInsert(int no,String time)
    {
    	try
    	{
    		getConnection();
    		String sql="INSERT INTO food_date VALUES(?,?)";
    		ps=conn.prepareStatement(sql);
    		ps.setInt(1, no);
    		ps.setString(2, time);
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
    
}
