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
    <c:if test="${sessionScope.admin=='y'}">
      <table class="table">
        <tr>
         <td>
           <a href="../notice/insert.do" class="btn btn-sm btn-danger">공지등록</a>
         </td>
        </tr>
      </table>
    </c:if>
    <table class="table">
      <tr>
        <th width=10% class="text-center">번호</th>
        <th width=40% class="text-center">공지</th>
        <th width=15% class="text-center">작성자</th>
        <th width=25% class="text-center">작성일</th>
        <th width=10% class="text-center">조회수</th>
      </tr>
      <c:forEach var="vo" items="${list }">
       <tr>
        <td width=10% class="text-center">${vo.no }</td>
        <td width=40%><a href="../notice/detail.do?no=${vo.no }">${vo.subject }</a></td>
        <td width=15% class="text-center">${vo.name }</td>
        <td width=25% class="text-center">${vo.dbday }</td>
        <td width=10% class="text-center">${vo.hit }</td>
      </tr>
      </c:forEach>
    </table>
    <table class="table">
      <tr>
        <td class="text-center">
          <a href="../notice/list.do?page=${curpage>1?curpage-1:curpage }" class="btn btn-sm btn-danger">이전</a>
            ${curpage } page / ${totalpage } pages
          <a href="../notice/list.do?page=${curpage<totalpage?curpage+1:curpage }" class="btn btn-sm btn-danger">다음</a>
        </td>
      </tr>
    </table>
  </main>
</div>
</body>
</html>





