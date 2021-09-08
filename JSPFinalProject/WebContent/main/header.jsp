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
<div class="wrapper row1">
  <header id="header" class="clear"> 
    <!-- ################################################################################################ -->
    <div id="logo" class="fl_left">
      <h1><a href="../main/main.do">추천시스템  & 예약시스템</a></h1>
    </div>
    <div class="fl_right">
      <ul class="inline">
        <!-- <li><i class="fa fa-phone"></i> +00 (123) 456 7890</li>
        <li><i class="fa fa-envelope-o"></i> info@domain.com</li> -->
      </ul>
    </div>
    <!-- ################################################################################################ --> 
  </header>
</div>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<div class="wrapper row2">
  <nav id="mainav" class="clear"> 
    <!-- ################################################################################################ -->
    <ul class="clear">
      <li class="active"><a href="../main/main.do">홈</a></li>
      <li><a class="drop" href="#">회원</a>
        <c:if test="${sessionScope.id==null }"><%--로그인이 안된 상태 --%>
	        <ul>
	          <li><a href="pages/gallery.html">회원가입</a></li>
	          <li><a href="pages/full-width.html">아이디찾기</a></li>
	          <li><a href="pages/sidebar-left.html">비밀번호찾기</a></li>
	        </ul>
        </c:if>
        <c:if test="${sessionScope.id!=null }"><%--로그인이 된 상태 --%>
	        <ul>
	          <li><a href="pages/gallery.html">회원수정</a></li>
	          <li><a href="pages/full-width.html">회원탈퇴</a></li>
	        </ul>
        </c:if>
      </li>
      <!-- 맛집 -->
      <li><a class="drop" href="#">맛집</a>
        <ul>
          <li><a href="pages/gallery.html">맛집 목록</a></li>
          <li><a href="pages/full-width.html">지역별 맛집 찾기</a></li>
        </ul>
      </li>
      <!-- 레시피 -->
      <li><a class="drop" href="#">레시피</a>
        <ul>
          <li><a href="pages/gallery.html">레시피 목록</a></li>
          <li><a href="pages/full-width.html">쉐프</a></li>
          <li><a href="pages/full-width.html">레시피 만들기</a></li>
        </ul>
      </li>
      <!-- 영화 -->
      <li><a class="drop" href="#">영화</a>
        <ul>
          <li><a href="pages/gallery.html">현재상영영화</a></li>
          <li><a href="pages/full-width.html">개봉예정영화</a></li>
        </ul>
      </li>
      <!-- 서울 여행  -->
      <li><a class="drop" href="#">서울여행</a>
        <ul>
          <li><a href="pages/gallery.html">명소</a></li>
          <li><a href="pages/gallery.html">자연&관광</a></li>
          <li><a href="pages/full-width.html">호텔</a></li>
          <li><a href="pages/full-width.html">날씨</a></li>
        </ul>
      </li>
      <!-- 커뮤니티 : 자료실 ,묻고답하기 , 자유게시판  -->
      <li><a class="drop" href="#">커뮤니티</a>
        <ul>
          <li><a href="pages/gallery.html">자유게시판</a></li>
          <li><a href="pages/gallery.html">묻고답하기</a></li>
          <li><a href="pages/full-width.html">공지사항</a></li>
          <li><a href="pages/full-width.html">이벤트</a></li>
        </ul>
      </li>
      <c:if test="${sessionScope.id!=null }">
	      <li><a class="drop" href="#">예약시스템</a>
	        <ul>
	          <li><a href="pages/gallery.html">맛집예약</a></li>
	          <li><a href="pages/gallery.html">영화예매</a></li>
	        </ul>
	      </li>
	      <li><a class="drop" href="#">추천시스템</a>
	        <ul>
	          <li><a href="pages/gallery.html">맛집추천</a></li>
	          <li><a href="pages/gallery.html">영화추천</a></li>
	          <li><a href="pages/gallery.html">레시피추천</a></li>
	        </ul>
	      </li>
      </c:if>
      <li><a href="#">오늘의 뉴스</a></li>
      <c:if test="${sessionScope.id!=null }">
        <c:if test="${admin=='n' }">
          <li><a href="#">마이페이지</a></li>
        </c:if>
        <c:if test="${admin=='y' }">
          <li><a href="#">어드민페이지</a></li>
        </c:if>
      </c:if>
    </ul>
    <!-- ################################################################################################ --> 
  </nav>
</div>
</body>
</html>



