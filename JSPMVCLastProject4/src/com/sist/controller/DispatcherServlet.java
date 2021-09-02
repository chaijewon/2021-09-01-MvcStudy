package com.sist.controller;

import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 *    웹 프로그램 : 브라우저에 출력하는 프로그램 
 *    ========
 *      JSP =====> 톰캣에 의해 컴파일 ===> 서블릿 코드로 변경(실행=>톰캣에 의해 자바파일로 변경)
 *          ==> 스크립트 프로그램 (단순한 프로그램)
 *          ==> JSP를 사용하는 이유 => 서블릿단점:수정시마다 컴파일을 하고 톰캣에 올리기
 *          ==> 자동 컴파일이 가능하게 만들어서 수정한 바로 볼 수 잇게 만드는 파일 (수정=>브라우저에서 새로고침)
 *          ==> 보안 (자바로 만든 파일은 컴파일후 .class파일만 넘겨준다(소스를 볼 수 없다)
 *          ==> 유지보수를 할 수 없다 
 *      서블릿 ====> 보안 
 *      
 *      보안 => Controller , Model , DAO , VO
 *            ===========
 *            실제 사용자의 요청을 받아서 => 결과을 보내주는 역할 (서블릿:spring,struts)
 *                                                        ====== ======자바전용
 *                                                        모든 언어 사용이 가능 
 *      화면만 출력시 => JSP => 자바를 사용하지 않고 태그형으로 프로그램 제작 (View)
 *      
 *      MVC : 
 *        Model => ~VO,~DAO,~Model,~Manager,~Service
 *                 VO : 필요한 데이터를 모아서 한번에 브라우저에 전송할 목적으로 만든다 
 *                      => 오리클 테이블 column과 일치 (한개에 대한 정보)
 *                      => 영화정보(상세보기),게시판 (row,record,tuple)
 *                 DAO : 오라클 연결하는 프로그램 (DML담당)
 *                 Model : 사용자가 요청한 내용을 받아서 요청 처리를 하는 클래스 
 *                         => 스프링 : ~Controller => ListModel => ListController
 *                         => 스트럿츠 : ~Action  =. ListAction
 *                 Manager : 데이터베이스가 아니고 다른 읽기 
 *                           => XML파싱 , JSON파싱 , 실시간 데이터 읽기, 뉴스데이터 
 *                 Service : DAO여러개를 한번 묶어서 처리 
 *                 DAO(한개의 테이블만 제어) , Service(여러개의 테이블을 동시 제어)
 *                 Order를 모르기 때문에 
 *                 
 *       서블릿 
 *         init() => web.xml에 등록된 데이터를 읽어 온다 
 *                   한번만 수행한다 
 *         service()(GET/POST) => 화면 출력이 없고 연결만 (자바 <==> JSP)
 *        =========== ==> 사용자 요청시마다 호출 (.do) main.do => service()
 *                                              list.do => service()
 *        |         |
 *      doGet()   doPost() => 화면 출력이 있는 경우 (html)
 *      
 *      => @RequestMapping()
 *            GET/POST
 *         =================
 *         |               |
 *       @GetMapping()   @PostMapping() => Spring버전 4.0이상 
 */
// 서블릿은 프로그래머가 호출해지 않는다 => .do 마다 톰캣에 의해 호출 => 서블릿을 톰캣에 알려준다 
// 서블릿 위치 => web.xml ==> *.do
// Front-Controller (사용자의 요청받기, 결과값 보내기)
// MVC(Controller)
import java.net.*;
import java.util.*;
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // service() 동작하기 위한 데이터를 저장하는 역할 (생성자역할) 
	private List<String> clsList=new ArrayList<String>();
	public void init(ServletConfig config) throws ServletException {
		String path=config.getInitParameter("contextConfigLocation");
		System.out.println(path);// /WEB-INF/application.xml
		try
		{
			URL url=DispatcherServlet.class.getResource("/");// 루트 경로 , 현재 경로 .
			String temp=url.getPath();
			// ============>   
			temp=temp.substring(0,temp.lastIndexOf("/"));
			temp=temp.substring(0,temp.lastIndexOf("/"));
			temp=temp+"/"+path;
			
			WebApplicationContext wc=new WebApplicationContext(temp.substring(1).replace("/", "\\"));
			clsList=wc.getList();
			System.out.println("aa:"+temp.substring(1).replace("/", "\\"));
			for(String s:clsList)
			{
				System.out.println(s); // com.sist.model.A , com.sist.model.B ,
			}
			
		    
		}catch(Exception ex){ex.printStackTrace();}
	}
    // URL주소 끝이 .do => 호출되는 메소드 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
