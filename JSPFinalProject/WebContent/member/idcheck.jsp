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
  margin-top: 50px;
}
.row {
   margin: 0px auto;
   width:300px;
}
</style>
</head>
<body>
   <div class="container">
     <div class="row">
      <table class="table">
       <tr>
         <td class="text-center">
         입력:<input type=text id=id size=15 class="input-sm">
         <input type=button value="중복체크" class="btn btn-sm btn-primary" id="idcheckBtn">
         </td>
       </tr>
       <tr>
         <td class="text-center"></td>
       </tr>
       <tr>
         <td class="text-center">
           <input type=button value="확인" class="btn btn-sm btn-success" id=okBtn>
         </td>
       </tr>
      </table>
     </div>
   </div>
</body>
</html>




