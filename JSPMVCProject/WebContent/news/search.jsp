<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 30px;
}
.row {
   margin: 0px auto;
   width:800px;
}
</style>
</head>
<body>
   <div class="container">
     <h1 class="text-center">네이버 뉴스 검색</h1>
     <div class="row">
      <table class="table">
        <tr>
         <td>
          <form method="post" action="search.do">
	          <input type=text name=fd size=20 class="input-sm">
	          <input type=submit value="검색" class="btn btn-sm btn-danger">
          </form>
         </td>
        </tr>
      </table>
      <hr>
      <table class="table">
       <tr>
         <td>
           <c:forEach var="vo" items="${list }">
            <table class="table">
             <tr>
               <td><span style="color:orange">${vo.title }</span></td>
             </tr>
             <tr>
               <td><a href="${vo.link }">${vo.description }</a></td>
             </tr>
             <tr>
               <td class="text-right">
                ${vo.author }
               </td>
             </tr>
            </table>
           </c:forEach>
         </td>
       </tr>
      </table>
     </div>
   </div>
</body>
</html>




