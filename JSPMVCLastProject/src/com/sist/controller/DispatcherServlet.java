package com.sist.controller;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.model.*;
// interface는 여러개 클래스를 한개로 통합해서 사용 (Model 기능 동일 => 요청처리후 결과값만 넘겨준다)
import java.util.*;
// 프로그램 시작점 => MVC (고정)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Map clsMap=new HashMap();
	public void init(ServletConfig config) throws ServletException {
		// 메뉴판 => clsMap()
		clsMap.put("main.do", new MainModel());
		clsMap.put("ko.do", new KoModel());
		clsMap.put("ch.do", new ChModel());
		clsMap.put("ja.do", new JaModel()); // 여러명이 동시 작업 => 문서작업(XML)
		// 라이브러리화 => spring.jar(스프링)
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자가 주문 내용을 받는다 (ko.do(한식) , ch.do(중식) , ja.do(일식))
		String cmd=request.getRequestURI();
		// http://localhost:8080/JSPMVCLastProject/ko.do
		// /JSPMVCLastProject ContextPath 
		// +1 /
		cmd=cmd.substring(request.getContextPath().length()+1);
		// 요청처리하기 위해서 => Model을 호출 (execute()호출) => 처리
		Model model=(Model)clsMap.get(cmd);
		
		// JSP로 값을 전송 
		String jsp=model.execute(request, response);
		// 결과값을 넘겨준다 
		RequestDispatcher rd=request.getRequestDispatcher("main/"+jsp);
		rd.forward(request, response);
	}

}







