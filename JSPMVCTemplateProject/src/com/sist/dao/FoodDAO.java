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
     *   3. 깃=>수정 (commit) => 신호 (commit시 신호) 
     *      =============== pull 
     *   ================== 프로젝트 준비 ==================== 데이터 수집
     */
    public List<FoodVO> foodListData(int page)
    {
    	List<FoodVO> list=new ArrayList<FoodVO>();
    	try
    	{
    		getConnection();
    		String sql="SELECT no,name,poster,num "
    				  +"FROM (SELECT no,name,poster,rownum as num "
    				  +"FROM (SELECT no,name,poster "
    				  +"FROM food_house ORDER BY no ASC)) "
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
    			FoodVO vo=new FoodVO();
    			vo.setNo(rs.getInt(1));
    			vo.setName(rs.getString(2));
    			String poster=rs.getString(3);
    			vo.setPoster(poster.substring(0,poster.indexOf("^")));
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
    
    public int foodTotalPage()
    {
    	int total=0;
    	try
    	{
    		getConnection();
    		String sql="SELECT CEIL(COUNT(*)/12.0) FROM food_house";
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
    // 카테고리 읽기
    public List<FoodCategoryVO> foodCategoryData()
    {
    	List<FoodCategoryVO> list=new ArrayList<FoodCategoryVO>();
    	try
    	{
    		getConnection();
    		String sql="SELECT cno,title,subject,poster "
    				  +"FROM project_food_category";
    		ps=conn.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		// 소스표준화 : 스프링 , 마이바티스 , Jquery (Ajax) => 패턴 (소스 구현이 거의 비슷)
    		//                           ============== Vue,React === MVC
    		//                                   vuex , Redux,훅
    		while(rs.next())
    		{
    			FoodCategoryVO vo=new FoodCategoryVO();
    			vo.setCno(rs.getInt(1));
    			vo.setTitle(rs.getString(2));
    			vo.setSubject(rs.getString(3));
    			vo.setPoster(rs.getString(4));
    			list.add(vo);
    		}
    		rs.close();
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	finally
    	{
    		disConnection();
    	}
    	return list;
    }
    // 카테고리별 맛집 목록
    // 맛집 상세보기 
    // 지역별 맛집 찾기 => Ajax
    public List<FoodLocationVO> foodFindData(int page,int locno)
    {
    	List<FoodLocationVO> list=new ArrayList<FoodLocationVO>();
    	try
    	{
    		getConnection();
    		String sql="SELECT no,locno,name,poster,num "
    				  +"FROM (SELECT no,locno,name,poster,rownum as num "
    				  +"FROM (SELECT no,locno,name,poster "
    				  +"FROM project_food_location WHERE locno=? ORDER BY no ASC)) "
    				  +"WHERE num BETWEEN ? AND ? ";
    				  
    		ps=conn.prepareStatement(sql);
    		int rowSize=12;
    		int start=(rowSize*page)-(rowSize-1);
    		int end=rowSize*page;
    		ps.setInt(2, start);
    		ps.setInt(3, end);
    		ps.setInt(1, locno);
    		
    		ResultSet rs=ps.executeQuery();
    		while(rs.next())
    		{
    			FoodLocationVO vo=new FoodLocationVO();
    			vo.setNo(rs.getInt(1));
    			vo.setLocno(rs.getInt(2));
    			vo.setName(rs.getString(3));
    			String s=rs.getString(4);
    			s=s.substring(0,s.indexOf("^"));
    			s=s.replace("#", "&");
    			vo.setPoster(s);
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
    public int foodLoactionTotalPage(int locno)
    {
    	int total=0;
    	try
    	{
    		getConnection();
    		String sql="SELECT CEIL(COUNT(*)/12.0) FROM project_food_location "
    				  +"WHERE locno=?";
    		ps=conn.prepareStatement(sql);
    		ps.setInt(1, locno);
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
    // 검색어가 없는 경우 / 검색이 된 경우 => count=0 => 검색된 결과값 없다
    public int foodLocationCountData(int locno)
    {
    	int count=0;
    	try
    	{
    		getConnection();
    		String sql="SELECT COUNT(*) FROM project_food_location "
    				  +"WHERE locno=?";
    		ps=conn.prepareStatement(sql);
    		ps.setInt(1, locno);
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
}






