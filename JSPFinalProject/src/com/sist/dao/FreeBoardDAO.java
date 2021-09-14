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
import com.sist.vo.ReplyVO;

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
   public FreeBoardVO freeboardDetailData(int no)
   {
	   FreeBoardVO vo=new FreeBoardVO();
	   try
	   {
		   getConnection();
		   // 조회수 증가 
		   String sql="UPDATE project_freeboard SET "
				     +"hit=hit+1 "
				     +"WHERE no=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, no);
		   ps.executeUpdate(); 
		   
		   // 상세볼 게시물 읽기
		   sql="SELECT no,name,subject,content,regdate,hit "
			  +"FROM project_freeboard "
			  +"WHERE no=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, no);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   vo.setNo(rs.getInt(1));
		   vo.setSubject(rs.getString(3));
		   vo.setName(rs.getString(2));
		   vo.setContent(rs.getString(4));
		   vo.setRegdate(rs.getDate(5));
		   vo.setHit(rs.getInt(6));
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
   // 수정 
   public FreeBoardVO freeboardUpdateData(int no)
   {
	   FreeBoardVO vo=new FreeBoardVO();
	   try
	   {
		   getConnection();
		   // 조회수 증가 
		   String sql="SELECT no,name,subject,content "
			  +"FROM project_freeboard "
			  +"WHERE no=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, no);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   vo.setNo(rs.getInt(1));
		   vo.setSubject(rs.getString(3));
		   vo.setName(rs.getString(2));
		   vo.setContent(rs.getString(4));
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
   // 실제 수정 
   public boolean freeboardUpdate(FreeBoardVO vo)
   {
	   boolean bCheck=false;// 비밀번호 체크 (true/수정,false/다시 입력)
	   try
	   {
		   getConnection();
		   // 비밀번호 확인 
		   String sql="SELECT pwd FROM project_freeboard "
				     +"WHERE no=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, vo.getNo());
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   String db_pwd=rs.getString(1);
		   rs.close();
		   
		   if(db_pwd.equals(vo.getPwd())) 
		   {
			   bCheck=true;
			   // 실제 수정 
			   sql="UPDATE project_freeboard SET "
				  +"name=?,subject=?,content=? "
				  +"WHERE no=?";
			   ps=conn.prepareStatement(sql);
			   ps.setString(1, vo.getName());
			   ps.setString(2, vo.getSubject());
			   ps.setString(3, vo.getContent());
			   ps.setInt(4, vo.getNo());
			   ps.executeUpdate();
		   }
		   else
		   {
			   bCheck=false;
		   }
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   disConnection();
	   }
	   return bCheck;
   }
   // 삭제 => 트랜잭션 프로그램 (일괄처리) => SQL문장 전체가 실행 , error가 났을 경우에 전체 취소
   
   public boolean freeboardDelete(int no,String pwd)
   {
	   boolean bCheck=false;
	   try
	   {
		   getConnection();
		   conn.setAutoCommit(false);
		   // 비밀번호 체크 
		   String sql="SELECT pwd FROM project_freeboard "
				     +"WHERE no=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, no);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   String db_pwd=rs.getString(1);
		   rs.close();
		   if(pwd.equals(db_pwd)) 
		   {
			   bCheck=true;//freeboard/list.jsp
			   // 삭제 한다 
			   sql="DELETE FROM project_reply "
				  +"WHERE bno=?";
			   ps=conn.prepareStatement(sql);
			   ps.setInt(1, no);
			   ps.executeUpdate();// 참조하고 있는 데이터를 먼저 삭제한다 
			   
			   sql="DELETE FROM project_freeboard "
				  +"WHERE no=?";
			   ps=conn.prepareStatement(sql);
			   ps.setInt(1, no);
			   ps.executeUpdate();
			   conn.commit();
		   }
		   else
		   {
			   bCheck=false;// history.back()
		   }
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
		   try
		   {
			   conn.rollback();
		   }catch(Exception e)
		   {
			   e.printStackTrace();
			   
		   }
	   }
	   finally
	   {
		   try
		   {
			   conn.setAutoCommit(true);
		   }catch(Exception ex) {}
		   disConnection();
	   }
	   return bCheck;
   }
   /*
    *     메소드()
    *     {
    *         try
    *         {
    *             getConnection();
    *             conn.setAutoCommit(false); => true(자동 commit=> true)
    *             ** oracle 단점 : 오류 발생시 => 다음 문장을 실행 (commit을 실행해야 저장)
    *             =SQL문장  error
    *             =SQL문장  error
    *             =SQL문장 정상 수행  => commit
    *             conn.commit()
    *         }catch(Exception ex)
    *         {
    *             conn.rollback() ;  SQL문장 전체 취소
    *         }
    *         finally
    *         {
    *              conn.setAutoCommit(true);
    *         }
    *     }
    *     트랜잭션 프로그램 (중요) 
    *     
    *     기본 )
    *         입고 (INSERT) => 재고 (INSERT) => 동시 처리
    *         출고 (INSERT) => 재고 (UPDATE) => 동시 처리 
    *         ======================================== 트랜잭션(일괄처리)
    *         여러개의 문장이 동시에 정상 수행 => commit()
    *         여러개의 문장중에 1개라도 에러 발생시 => rollback()
    */
   // 글쓰기 
   // JSP => .do ==> Model에서 처리 (DAO연결) => 화면출력 이동 
   public void freeboardInsert(FreeBoardVO vo)
   {
	   try
	   {
		   getConnection();
		   String sql="INSERT INTO project_freeboard(no,name,subject,content,pwd) "
				     +"VALUES(pf_no_seq.nextval,?,?,?,?)";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, vo.getName());
		   ps.setString(2, vo.getSubject());
		   ps.setString(3, vo.getContent());
		   ps.setString(4, vo.getPwd());
		   ps.executeUpdate(); //commit이 존재  => autocommit()
		   // INSERT , UPDATE ,DELETE 여러개 존재 => 일괄처리(트랜잭션)
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   disConnection();
	   }
   }
   // 찾기 
   // ======================= PL/SQL (오라클에서 한수 호출) => 자동 처리 (Trigger) 
   // 댓글 목록=> Model이 받아서 => request에 담아서 => JSP
   // 자바 / HTML을 분리 
   public List<ReplyVO> replyListData(int bno,int type)
   {
	   List<ReplyVO> list=new ArrayList<ReplyVO>();
	   try
	   {
		   getConnection();
		   String sql="SELECT no,bno,id,name,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') "
				     +"FROM project_reply "
				     +"WHERE bno=? AND type=? "
				     +"ORDER BY no DESC";
		   //bno => 어떤 게시물 번호(1번), 어떤 맛집 번호(1번)
		   //type => 구분 (맛집,게시판)
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, bno);
		   ps.setInt(2, type);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   ReplyVO vo=new ReplyVO();
			   vo.setNo(rs.getInt(1));
			   vo.setBno(rs.getInt(2));
			   vo.setId(rs.getString(3));
			   vo.setName(rs.getString(4));
			   vo.setMsg(rs.getString(5));
			   vo.setDbday(rs.getString(6));
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
   // 댓글 쓰기
   /*
    *      no NUMBER,
		   bno NUMBER,  -- 게시판 번호,영화번호 , 맛집번호 , 레시피번호
		   type NUMBER, -- 게시판(1) , 영화(2) , 맛집(3) 레시피(4) ,여행(5)
		   id VARCHAR2(20),
		   name VARCHAR2(34) CONSTRAINT pr_name_nn NOT NULL,
		   msg CLOB CONSTRAINT pr_msg_nn NOT NULL,
		   regdate DATE DEFAULT SYSDATE,
		   CONSTRAINT pr_no_pk PRIMARY KEY(no),
		   CONSTRAINT pr_type_fk FOREIGN KEY(id)
		   REFERENCES project_member(id)
		   
		   CREATE SEQUENCE pr_no_seq 
    */
   public void replyInsert(ReplyVO vo)
   {
	   try
	   {
		   // 1. 연결 
		   getConnection();
		   // 2. SQL문장 만들기  => SQL문장 (마이바티스)
		   String sql="INSERT INTO project_reply VALUES("
				     +"pr_no_seq.nextval,?,?,?,?,?,SYSDATE)";
		   // SQL문장을 미리 전송 
		   ps=conn.prepareStatement(sql);
		   // 나중에 ?에 값을 채운다 
		   ps.setInt(1, vo.getBno());
		   ps.setInt(2, vo.getType());
		   ps.setString(3, vo.getId());
		   ps.setString(4, vo.getName());
		   ps.setNString(5, vo.getMsg());
		   
		   // 실행 명령 
		   ps.executeUpdate(); //commit() 포함 
		   // setInt() setString() setDouble()
		   //   있는 그대로 값을 설정  , ''를 붙여서 첨부 , 있는 그대로 설정 
		   /*
		    *   INSERT INTO project_reply VALUES(?,?,?)
		    *   setInt(1,1)
		    *   setString(2,"홍길동")
		    *   setDouble(3,185.45)
		    *   
		    *   INSERT INTO project_reply VALUES(1,'홍길동',185.45)
		    */
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   disConnection();
	   }
   }
   // 댓글 수정 
   public void replyUpdate(int no,String msg)
   {
	   try
	   {
		   getConnection();
		   String sql="UPDATE project_reply SET "
				     +"msg=? "
				     +"WHERE no=?";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, msg);
		   ps.setInt(2, no);
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
   // 댓글 삭제  => 70~80% => DAO
   public void replyDelete(int no)
   {
	   try
	   {
		   getConnection();
		   String sql="DELETE FROM project_reply WHERE no=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, no);
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










