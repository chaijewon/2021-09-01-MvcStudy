package com.sist.controller;

import java.io.*;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
// Model 찾기 => return 받은 jsp에 request를 전송한다 
/*
 *  게시판 => BoardModel => 메소드 (목록,추가,수정,삭제...)
 *          =========== 영화목록 ,예매하기 => 재사용 
 *          => 메뉴마다 (클래스 1개)
 *          회원 => 회원가입 , 회원수정, 회원탈퇴 , 로그인 , 아이디 찾기 , 비밀번호 찾기 
 *          예매하기 => 영화 출력 , 극장출력 , 달력출력 , 시간출력 ,인원출력 , 선택내용 출력 
 *                    좌석설정 
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    List<String> clsList=new ArrayList<String>();
	public void init(ServletConfig config) throws ServletException {
		clsList.add("com.sist.model.MainModel");//기능별 모델처리 
		// init메소드는 구동시에 한번만 호출이 된다 
	}
    // 수시로 호출되는 메소드 => URL주소에 마지막에 .do => 무조건 호출되는 메소드 
	// main.do => service , ko.do => service
	// 사용자가 URL주소 변경할때마다 호출 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자가 요청한 주소 확인 
		// http://localhost:8080/JSPLastProject2/main/main.do
		/*
		 *   @ => Target("TYPE") => 클래스 찾기 
		 *   class class_name
		 *   {
		 *       @ => Target("FIELD") => 멤버변수
		 *       BoardDAO dao;
		 *       
		 *       @ => Target("PARAMETER") => 매개변수
		 *       public void setDAO(@ BoardDAO dao)
		 *       {
		 *       }
		 *       @ => Target("METHOD") => 메소드 찾기 
		 *       public void display()
		 *       {
		 *       }
		 *   }
		 */
		try {
			String cmd=request.getRequestURI();
			// cmd="/JSPLastProject2/main/main.do"
			cmd=cmd.substring(request.getContextPath().length()+1);
			// cmd="main/main.do";
			
			// cmd문자열을 가지고 있는 어노테이션을 찾아서 => 밑에 있는 메소드를 호출 한다 
			
			for(String cls:clsList)
			{
				// 메모리 할당 
				Class clsName=Class.forName(cls);// 클래스 정보 읽기
				// ClassNotFoundException => 컴파일(체크) 예외처리 
				// 클래스 정보 기반으로 메모리 할당 
				Object obj=clsName.getDeclaredConstructor().newInstance();
				// => new
				// clsName에서 메소드를 찾고 obj로 메소드 호출 (invoke())=>모든 메소드 호출이 가능 
				// 리플렉션 => 시스템에 의해 자동으로 호출되는 메소드 (main) => 솔루션
				// 솔루션 (프레임워크) => SI => SM
				/*
				 *   솔루션 : 자체 => SM => 스타트업 
				 *   SI / SM
				 *   SM 
				 *   ***프레임워크 : 위탁을 받아서 프로그램 제작 (AI)
				 */
				// MainModel이 가지고 있는 메소드를 읽어 온다 
				Method[] methods=clsName.getDeclaredMethods();
				// => main_page, main_ko,main_ch,main_ja
				for(Method m:methods)
				{
					RequestMapping rm=m.getAnnotation(RequestMapping.class);
					if(rm.value().equals(cmd))
					{
						String jsp=(String)m.invoke(obj, request);
						// 전송
						RequestDispatcher rd=
								request.getRequestDispatcher(jsp);
						rd.forward(request, response);
						return;
					}
				}
				
			}
		}catch(Exception ex) {}
	}

}














