package com.sist.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.MovieDAO;
import com.sist.vo.MovieVO;
/*
 *    public void display(int a,int b){}
 *    display(); => 오류
 */
@Controller
public class MovieModel {
	   @RequestMapping("movie/movie_list.do") // 사용자가 메뉴에서 현재상영영화 요청 
	   public String movie_list(HttpServletRequest request,HttpServletResponse response)
	   {
		   // 페이지 나누기 
		   // 사용자가 페이지 번호를 전송 
		   String cno=request.getParameter("cno");
		   String page=request.getParameter("page");
		   if(page==null)
			   page="1"; 
		   // 현재페이지 출력 
		   int curpage=Integer.parseInt(page);
		   // 현재페이지에 해당되는 데이터를 읽어 온다 (오라클)
		   MovieDAO dao=MovieDAO.newInstance();
		   List<MovieVO> list=dao.movieListData(Integer.parseInt(cno), curpage);
		   // 총페이지 구하기
		   int totalpage=dao.movieTotalPage(Integer.parseInt(cno));
		   // 블럭 나누기 
		   final int BLOCK=5;
		   int startPage=((curpage-1)/BLOCK*BLOCK)+1; // 1, 6 , 11 , 16...
		   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;// 5 10 15 20...
		   if(endPage>totalpage)
			     endPage=totalpage;
		   
		   // 전송 
		   request.setAttribute("curpage", curpage);
		   request.setAttribute("totalpage", totalpage);
		   request.setAttribute("list", list);
		   request.setAttribute("BLOCK", BLOCK);
		   request.setAttribute("startPage", startPage);
		   request.setAttribute("endPage", endPage);
		   request.setAttribute("cno", cno);
		   request.setAttribute("main_jsp", "../movie/movie_list.jsp");
		   return "../main/main.jsp";// 메뉴/footer가 사라진다 
	   }
	   // 영화 상세
	   @RequestMapping("movie/movie_detail.do")
	   public String movie_detail(HttpServletRequest request,HttpServletResponse response)
	   {
		   // mno,cno
		   String mno=request.getParameter("mno");
		   String cno=request.getParameter("cno");
		   // 오라클 연결 
		   MovieDAO dao=MovieDAO.newInstance();
		   // 상세보기 메소드를 호출 
		   MovieVO vo=dao.movieDetailData(Integer.parseInt(mno));
		   
		   request.setAttribute("vo", vo);
		   request.setAttribute("cno", cno);// 목록으로 이동 (개봉/상영)
		   request.setAttribute("main_jsp", "../movie/movie_detail.jsp");
		   return "../main/main.jsp";
	   }
	   
}








