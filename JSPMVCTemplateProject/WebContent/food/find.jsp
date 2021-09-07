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
    <form method=post action="../food/find.do">
	    <ul>
	      <li><input type=text name=loc sie=15 class="input-sm"></li>
	      <li><button class="btn btn-sm btn-danger">검색</button></li>
	    </ul>
    </form>
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
          <header class="heading">&lt;${loc }&gt;맛집 리스트</header>
          <ul class="nospace clear">
            <c:forEach var="vo" items="${list }" varStatus="s">
              <c:if test="${s.index%4==0}">
	             <li class="one_quarter first">
	              <a href="#">
	               <img src="${vo.poster }" title="${vo.name }">
	              </a>
	            </li>
	          </c:if>
	          <c:if test="${s.index%4!=0 }">
	             <li class="one_quarter">
	              <a href="#">
	               <img src="${vo.poster }" title="${vo.name }">
	              </a>
	            </li>
	          </c:if>
            </c:forEach>
            
          </ul>
          <figcaption>Gallery Description Goes Here</figcaption>
        </figure>
      </div>
      <!-- ################################################################################################ --> 
      <!-- ################################################################################################ -->
      <nav class="pagination">
        <ul>
          <c:forEach var="i" begin="1" end="${totalpage }">
              <c:if test="${curpage==i }">
                <c:set var="ss" value="class=current"/>
              </c:if>
              <c:if test="${curpage!=i }">
                <c:set var="ss" value=""/>
              </c:if>
              <li ${ss }><a href="../food/find.do?page=${i }&loc=${loc}">${i }</a></li>
           </c:forEach>
          
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