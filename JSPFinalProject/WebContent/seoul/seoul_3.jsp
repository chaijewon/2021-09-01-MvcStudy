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
      <li><a href="#">Lorem</a></li>
      <li><a href="#">Ipsum</a></li>
      <li><a href="#">Dolor</a></li>
    </ul>
    <!-- ################################################################################################ --> 
  </div>
</div>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<div class="wrapper row3">
  <main class="container clear"> 
    <!-- main body --> 
    <!-- ################################################################################################ -->
    <div class="content"> 
      <!-- ################################################################################################ -->
      <div id="gallery">
        <figure>
          <header class="heading"><h2 class="sectiontitle">잠드는 순간에도 특별한 경험을 원하는 여행객 모두를 위한 서울의 호텔</h2></header>
          <ul class="nospace clear">
           <%--
                 varStatus => List의 index번호를 읽어 온다 (0번=>11번)
                 List<String> list=new ArrayList<String>();
                 list.add("aaa") => list.get(0)
                 for(int i=0;i<list.size();i++)
                 {
                     String s=list.get(i); ==> 
                 }
            --%>
           <c:forEach var="vo" items="${list }" varStatus="s">
             <c:if test="${s.index%4==0 }">
               <li class="one_quarter first"><a href="../seoul/seoul_hotel.do?no=${vo.no }"><img style="width:250px;height:250px" src="${vo.poster }" title="${vo.name }"></a></li>
             </c:if>
             <c:if test="${s.index%4!=0 }">
               <li class="one_quarter"><a href="../seoul/seoul_hotel.do?no=${vo.no }"><img style="width:250px;height:250px" src="${vo.poster }" title="${vo.name }"></a></li>
             </c:if>
           </c:forEach>
          </ul>
          
        </figure>
      </div>
      <!-- ################################################################################################ --> 
      <!-- ################################################################################################ -->
      <nav class="pagination">
        <ul>
          <c:if test="${startPage>1 }">
            <li><a href="../seoul/seoul_3.do?page=${startPage-1 }">&laquo; 이전</a></li>
          </c:if>
          <c:forEach var="i" begin="${startPage }" end="${endPage }">
            <c:if test="${i==curpage }"><%--현재페이지라면 --%>
              <li class="current"><strong>${i }</strong></li>
            </c:if>
            <c:if test="${i!=curpage }"><%--현재페이지가 아닌경우 --%>
              <li><a href="../seoul/seoul_3.do?page=${i }">${i }</a></li>
            </c:if>
          </c:forEach>
          <c:if test="${endPage<totalpage }">
            <li><a href="../seoul/seoul_3.do?page=${endPage+1 }">다음 &raquo;</a></li>
          </c:if>
        </ul>
      </nav>
      <!-- ################################################################################################ --> 
    </div>
    <!-- ################################################################################################ --> 
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
</body>
</html>