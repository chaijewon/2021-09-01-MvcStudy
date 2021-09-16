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
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.rdays').hover(function(){
		$(this).css("cursor","pointer");
		$(this).css("color","red");
	},function(){
		$(this).css("cursor","none");
		$(this).css("color","#999");
	})
	
	// 선택 => 시간
	$('.rdays').click(function(){
		let year=$(this).attr("data-year");
		let month=$(this).attr("data-month");
		let day=$(this).text();
		$('#food_date1').text(year+"년 "+month+"월 "+day+"일");
		$('#fdate').val(year+"년 "+month+"월 "+day+"일");
		// 시간 정보를 가지고 온다 
		$.ajax({
			type:'post',
			url:'../reserve/time.do',
			data:{"no":day},
			success:function(result)
			{
				$('#food_time').html(result);
			}
		})
	})
})
</script>
</head>
<body>
   <!-- <div class="container"> -->
     <h3 class="text-center">${year } 년도 ${month }월 ${day }일</h3>
     <!-- <div class="row"> -->
	     <table class="table">
	       <%-- String[] strWeek={"일","월","화","수","목","금","토"}; --%>
	       <tr style="height:50px">
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
	           <td class="danger text-center"><span class="rdays" data-year="${year }" data-month="${month }">${i }</span></td>
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
    <!--  </div>
   </div> -->
</body>
</html>







