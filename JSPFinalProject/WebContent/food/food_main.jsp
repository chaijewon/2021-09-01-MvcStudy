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
  <div id="slider" class="clear"> 
    <!-- ################################################################################################ -->
    <div class="flexslider basicslider">
      <ul class="slides">
        <li><a href="#"><img style="width:978px;height:400px" class="radius-10" src="../images/demo/slides/food_img1.jpg" alt=""></a></li>
        <li><a href="#"><img style="width:978px;height:400px" class="radius-10" src="../images/demo/slides/food_img2.jpg" alt=""></a></li>
        <li><a href="#"><img style="width:978px;height:400px" class="radius-10" src="../images/demo/slides/food_img3.jpeg" alt=""></a></li>
      </ul>
    </div>
    <!-- ################################################################################################ --> 
  </div>
</div>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<div class="wrapper row3">
  <main class="container clear"> 
   
    <!-- ################################################################################################ -->
    <h2 class="sectiontitle">믿고 보는 맛집 리스트</h2>
    <!-- ################################################################################################ -->
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
       <c:forEach var="vo" items="${list }" varStatus="s">
        <c:if test="${s.index>=0 && s.index<12 }">
	         <li>
	          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" style="width:320px;height:185px">
	            <figcaption><a href="../food/food_list.do?cno=${vo.cno }">${vo.title }</a></figcaption>
	          </figure>
	         </li>
         </c:if>
       </c:forEach>  
      </ul>
    </div>
    <h2 class="sectiontitle">지역별 인기 맛집</h2>
    <!-- ################################################################################################ -->
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
        <c:forEach var="vo" items="${list }" varStatus="s">
        <c:if test="${s.index>=12 && s.index<18 }">
	         <li>
	          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" style="width:320px;height:185px">
	            <figcaption><a href="../food/food_list.do?cno=${vo.cno }">${vo.title }</a></figcaption>
	          </figure>
	         </li>
         </c:if>
       </c:forEach>  
      </ul>
    </div>
    <h2 class="sectiontitle">메뉴별 인기 맛집</h2>
    <!-- ################################################################################################ -->
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
        <c:forEach var="vo" items="${list }" varStatus="s">
         <c:if test="${s.index>=18 && s.index<30 }">
	         <li>
	          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" style="width:320px;height:185px">
	            <figcaption><a href="../food/food_list.do?cno=${vo.cno }">${vo.title }</a></figcaption>
	          </figure>
	         </li>
         </c:if>
       </c:forEach>  
      </ul>
    </div>
    <!-- ################################################################################################ -->
    <h2 class="sectiontitle">최신 방문 맛집</h2>
    <!-- ################################################################################################ -->
	    <div class="inline">
	     <c:forEach var="cvo" items="${fList }">
	      <img class="radius-10" src="${cvo.poster }" style="width:100px;height:100px">
	     </c:forEach>
	    </div>
    <!-- ################################################################################################ --> 
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
</body>
</html>