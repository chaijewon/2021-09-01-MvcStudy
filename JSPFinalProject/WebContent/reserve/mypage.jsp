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
<div class="wrapper row3 reserve_row">
  <main class="container clear">
    <h2 class="sectiontitle">맛집 예약목록</h2>
    <table class="table">
      <tr>
       <th class="text-center">번호</th>
       <th class="text-center">맛집명</th>
       <th class="text-center">예약일</th>
       <th class="text-center">시간</th>
       <th class="text-center">인원</th>
       <th class="text-center">예약여부</th>
      </tr>
      <c:forEach var="vo" items="${list }">
       <tr>
	       <td class="text-center">${vo.no }</td>
	       <td class="text-center">${vo.name }</td>
	       <td class="text-center">${vo.rday }</td>
	       <td class="text-center">${vo.rtime }</td>
	       <td class="text-center">${vo.inwon }</td>
	       <td class="text-center">
	         ${ vo.isCheck==0?"예약대기":"예약완료" }
	       </td>
      </tr>
      </c:forEach>
    </table>
    <h2 class="sectiontitle">맛집 찜목록</h2>
    <%-- 본인이 작성한  게시물,댓글 --%>
  </main>
</div>
</body>
</html>