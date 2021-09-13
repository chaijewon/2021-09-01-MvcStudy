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
  <main class="container clear">
    <table class="table">
     <tr>
      <td class="inline">
         <form method="post" action="../news/news.do">
           <input type=text name=fd size=20 class="input-sm">
           <input type=submit value="검색">
          </form>
      </td>
     </tr>
    </table>
    <table>
      <tr>
        <td>
         <c:forEach var="vo" items="${list }">
           <table class="table">
             <tr>
               <td style="color:orange">${vo.title }</td>
             </tr>
             <tr>
               <td><a href="${vo.link }" style="color:gray;">${vo.description }</a></td>
             </tr>
             <tr>
               <td align="right">${vo.author }</td>
             </tr>
           </table>
         </c:forEach>
        </td>
      </tr>
    </table>
  </main>
</div>
</body>
</html>