<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=676eb5fa2637b234997b24dd7566e9ba&libraries=services"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.oks').click(function(){
		let no=$(this).attr("data-no"); // front-end(jquery,nodejs,vuejs,reactjs)
		// 추천 => 95% back-end (SM/솔루션) => Spring (이력서)
		$.ajax({
			type:"post",
			url:'../reserve/reserve_info.do',
			data:{"no":no}, //예약번호 
			success:function(result) // 정상 수행 => 200 , 404,500
			{
				$('#print').html(result);//include
			}
		})
	})
})
</script>
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
	         <c:if test="${vo.isCheck==1 }">
	           <span class="btn btn-sm btn-success oks" data-no="${vo.no }">예약완료</span>
	         </c:if>
	         <c:if test="${vo.isCheck==0 }">
	           <span class="btn btn-sm btn-default">예약대기</span>
	         </c:if>
	       </td>
      </tr>
      </c:forEach>
    </table>
    <div id="print"></div>
    <h2 class="sectiontitle">맛집 찜목록</h2>
    <%-- 본인이 작성한  게시물,댓글 --%>
  </main>
</div>
</body>
</html>