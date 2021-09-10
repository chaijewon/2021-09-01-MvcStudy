<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<div class="wrapper row3">
  <div id="breadcrumb" class="clear"> 
    <!-- ################################################################################################ -->
    <ul>
      <li><a href="#">Home</a></li>
      <li><a href="#">맛집</a></li>
      <li><a href="#">카테고리</a></li>
      <li><a href="#">상세보기</a></li>
    </ul>
    <!-- ################################################################################################ --> 
  </div>
</div>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<div class="wrapper row3">
  <main class="container clear"> 
    <div class="content three_quarter first"> 
      <!-- 이미지 출력 -->
      <table class="table">
        <tr>
         <c:forTokens var="image" items="${vo.poster }" delims="^">
           <td class="text-center"><img src="${image }" style="width:100%"></td>
         </c:forTokens>
        </tr>
      </table>
      <table class="table">
        <tr>
          <td colspan="2">
           <h3>${vo.name }&nbsp;<span style="color:orange">${vo.score }</span></h3>
          </td>
        </tr>
        <tr>
         <th class="text-center">주소</th>
         <td>${ vo.addr1 }
         <br><sup>${vo.addr2 }</sup>
         </td>
        </tr>
        <tr>
          <th class="text-center">전화</th>
          <td>${vo.tel }</td>
        </tr>
        <tr>
          <th class="text-center">음식종류</th>
          <td>${vo.type }</td>
        </tr>
        <c:if test="${vo.price!='no' }">
	        <tr>
	          <th class="text-center">가격대</th>
	          <td>${vo.price }</td>
	        </tr>
        </c:if>
        <c:if test="${vo.parking!='no' }">
	        <tr>
	          <th class="text-center">주차</th>
	          <td>${vo.parking }</td>
	        </tr>
        </c:if>
        <c:if test="${vo.time!='no' }">
	        <tr>
	          <th class="text-center">영업시간</th>
	          <td>${vo.time }</td>
	        </tr>
        </c:if>
        <c:if test="${vo.menu!='no' }">
	        <tr>
	          <th class="text-center">메뉴</th>
	          <td>
	            <%--
	              사시미8종 (2인) 41,000원 마구로치즈아에 19,000원 양갈비 스미비 야끼 23,000원 노도구로 시오 야끼 35,000원규 스테이크 41,000원
	             --%>
	            <ul>
	              <c:forTokens items="${vo.menu }" delims="원" var="m">
	                <li>${m }원</li>
	              </c:forTokens>
	            </ul>
	          </td>
	        </tr>
        </c:if>
        <tr>
          <td colspan="2">
           <a href="#" class="btn">좋아요</a>
           <a href="#" class="btn">찜하기</a>
           <a href="#" class="btn">예약하기</a>
           <a href="#" class="btn">목록</a>
          </td>
        </tr>
      </table>
    </div>
    <div class="sidebar two_quarter">
    </div>
    <div class="clear"></div>
  </main>
</div>
</body>
</html>