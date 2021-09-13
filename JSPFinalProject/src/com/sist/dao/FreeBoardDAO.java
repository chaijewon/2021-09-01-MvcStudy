package com.sist.dao;
// 댓글 포함 
/*
 *   FreeBoardDAO 
 *   ReplyDAO 
 *   ============= FreeBoardService
 *   
 *   JDBC 단점 => 연결/해제(반복) 
 *              === 연결할때 소모되는 시간이 많이 걸린다 (X)
 *   DBCP => 연결 시간을 없앤다 => 미리 연결한후에 연결된 Connection주소만 사용이 가능하게 만든다 
 *           => 사용후에 반환 => 재사용이 가능하게 만는다
 *              ============================== 관리(Connection) => POOL
 *           => Connection만 관리 
 *           => 일반 웹에서는 주로 사용 
 *           => 직접 제작/라이브러리(아파치=> common-dbcp.jar,common-pool.jar)
 *    ORM => (데이터베이스 연결하는 라이브러리) 
 *           => 기존의 DAO의 소스 코딩을 (1/10)로 들일 수 있다 
 */
import java.util.*;
import java.sql.*;
import javax.sql.*;

import com.sist.vo.FreeBoardVO;

import javax.naming.*;
// 1. DAO , 2. MVC  ==> Spring (MVC) , XML,Annotation 
// 실무 => 지금 프로젝트 
// Ajax / React,Vue 
/*
 *   역할 분담 
 *   =======
 *     일반 자바 => 메소드호출후에 매개변수 전송 
 *   Model : 사용자 요청 => 결과값 전송 
 *            | request/session(id,name) => setAttribute()
 *            | request=> 한개의 JSP에서 사용 , session=> 모든 JSP에서 사용이 가능 
 *   View : 결과값만 받아서 화면(브라우저) 출력 (jsp에는 메소드가 없다)
 *   
 */
public class FreeBoardDAO {
   private Connection conn;
   private PreparedStatement ps;
   private static FreeBoardDAO dao;
   // 공통으로 사용되는 소스를 모아서 따라 관리 (공통모듈) => 관리(AOP:스프링)
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
   public void disConnection()
   {
	      try
		  {
			  if(ps!=null) ps.close();
			  if(conn!=null) conn.close();
		  }catch(Exception ex) {}
   }
   // 메모리 누수현상을 처리 => 한개의 공간을 이용해서 메모리 관리(싱글턴패턴) = 스프링은 거의 싱글턴 
   public static FreeBoardDAO newInstance()
   {
	   if(dao==null)
		   dao=new FreeBoardDAO();
	   return dao;
   }
   // 기능 
   // 목록 출력 
   // 페이지 나누기 => 데이터가 많은 경우(총페이지 클 경우: 블록별,작은 페이지: 이전/다음)
   public List<FreeBoardVO> freeboardListData(int page)
   {
	   List<FreeBoardVO> list=new ArrayList<FreeBoardVO>();
	   try
	   {
		   getConnection();
		   String sql="SELECT no,subject,name,regdate,hit,num "
				     +"FROM (SELECT no,subject,name,regdate,hit,rownum as num "
				     +"FROM (SELECT no,subject,name,regdate,hit "
				     +"FROM project_freeboard ORDER BY no DESC)) "
				     +"WHERE num BETWEEN ? AND ?";
		   ps=conn.prepareStatement(sql);
		   int rowSize=10;
		   int start=(rowSize*page)-(rowSize-1);
		   int end=rowSize*page;
		   ps.setInt(1, start);
		   ps.setInt(2, end);
		   
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   FreeBoardVO vo=new FreeBoardVO();
			   vo.setNo(rs.getInt(1));
			   vo.setSubject(rs.getString(2));
			   vo.setName(rs.getString(3));
			   vo.setRegdate(rs.getDate(4));
			   vo.setHit(rs.getInt(5));
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
   // 총페이지 
   public int freeboardTotalPage()
   {
	   int total=0;
	   try
	   {
		   getConnection();
		   String sql="SELECT CEIL(COUNT(*)/10.0) FROM project_freeboard";
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
   // 수정 
   // 삭제
   // 글쓰기 
   // 찾기 
   // ======================= PL/SQL (오라클에서 한수 호출) => 자동 처리 (Trigger) 
   // 댓글 목록
   // 댓글 쓰기
   // 댓글 수정 
   // 댓글 삭제 
}









