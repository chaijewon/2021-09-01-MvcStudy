package com.sist.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @RequestMapping("a.do")
// 메소드 구분 , 클래스 구분 => 찾기목적 (index) 
// 직접 제작 (X) => 이미 만들어져 있는 프레임워크 (스프링,마이바티스)
@Retention(RetentionPolicy.RUNTIME) // CLASS,SOURCE => 컴파일후에 메모리 삭제
// RUNTIME => 프로그램 종료시까지 메모리 유지 
@Target(ElementType.METHOD) // 찾기 => 메소드 찾기 
public @interface RequestMapping {
   public String value();
}
