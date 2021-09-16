<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<%-- 라이브러리  --%>
<style type="text/css">
.container{
   margin-top: 50px;
}
.row {
   margin: 0px auto;
   width:450px;
}
</style>
</head>
<body>
   <div class="container">
     <h3 class="text-center">${year } 년도 ${month }월 ${day }일</h3>
     <div class="row">
	     <table class="table">
	       <%-- String[] strWeek={"일","월","화","수","목","금","토"}; --%>
	       <tr class="success" style="height:50px">
	         <c:forEach var="strWeek" items="${strWeek }">
	           <th class="text-center">${strWeek }</th>
	         </c:forEach>
	       </tr>
	     </table>
	     <%-- 달력 출력 request.setAttribute("week", week);--%>
	     <c:set var="week" value="${week }"/>
	     <table class="table">
	       <c:forEach var="i" begin="1" end="${lastday }"> 
	         <c:if test="${i==1 }">
	           <tr style="height:45px">
	            <c:forEach var="j" begin="1" end="${week }">
	              <td class="text-center">&nbsp;</td>
	            </c:forEach>
	         </c:if>
	         <c:if test="${i>=day }"><%--예약이 가능한 날짜 --%>
	           <td class="danger text-center">${i }</td>
	         </c:if>
	         <c:if test="${i<day }"><%--예약이 안되는 날짜 --%>
	           <td class="text-center">${i }</td>
	         </c:if>
	         <c:set var="week" value="${week+1 }"/><%--요일 변경 --%>
	         <c:if test="${week>6 }">
	           <c:set var="week" value="0"/>
	           </tr>
	           <tr style="height:45px">
	         </c:if>
	       </c:forEach>
	       </tr>
	     </table>
     </div>
   </div>
</body>
</html>







