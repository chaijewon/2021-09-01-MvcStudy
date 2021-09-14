<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<%--
     일반 게시판 : 등록/수정/삭제 ==> 어드민 
             일반 => 보기만 한다 
 --%>
<div class="wrapper row3">
  <main class="container clear"> 
    <table class="table">
      <tr>
        <th width=20% class="text-center">번호</th>
        <td width=30% class="text-center">${vo.no }</td>
        <th width=20% class="text-center">작성일</th>
        <td width=30% class="text-center">${vo.dbday }</td>
      </tr>
      <tr>
        <th width=20% class="text-center">작성자</th>
        <td width=30% class="text-center">${vo.name }</td>
        <th width=20% class="text-center">조회수</th>
        <td width=30% class="text-center">${vo.hit }</td>
      </tr>
      <tr>
        <th width=20% class="text-center">제목</th>
        <td colspan="3">${vo.subject }</td>
      </tr>
      <tr>
        <td colspan="4" valign="top" class="text-left" height="200">
          <pre style="white-space: pre-wrap;border:none;background-color:white;">${vo.content }</pre>
        </td>
      </tr>
      <tr>
        <td colspan="4" class="text-right">
         <c:if test="${sessionScope.admin=='y' }">
          <a href="../notice/notice_update.do?no=${vo.no }" class="btn btn-sm btn-success">수정</a>
          <a href="../notice/notice_delete.do?no=${vo.no }" class="btn btn-sm btn-info">삭제</a>
         </c:if>
         <a href="../notice/list.do" class="btn btn-sm btn-warning">목록</a>
        </td>
      </tr>
    </table>
  </main>
</div>
</body>
</html>






