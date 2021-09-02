<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
   <h1 class="text-center">&lt;${vo.title }&gt; 상세보기</h1>
   <div class="row">
     <table class="table">
       <tr>
        <td class="text-center" rowspan="4" width=30%>
         <img src="${vo.poster }" width=100%>
        </td>
        <td colspan="2"><h1>${vo.title }</h1></td>
       </tr>
       <tr>
         <td width=20%>장르</td>
         <td width=50%>${vo.genre }</td>
       </tr>
       <tr>
         <td width=20%>등급</td>
         <td width=50%>${vo.grade }</td>
       </tr>
       <tr>
         <td width=20%>상영일</td>
         <td width=50%>${vo.regdate }</td>
       </tr>
       <tr>
         <td colspan="3" class="text-right">
          <a href="list.do" class="btn btn-sm btn-danger">목록</a>
         </td>
       </tr>
     </table>
   </div>
  </div>
</body>
</html>







