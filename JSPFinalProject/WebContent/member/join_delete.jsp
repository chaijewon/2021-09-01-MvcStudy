<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
   margin: 0px auto;
   width:350px;
}
</style>
</head>
<body>
<div class="wrapper row3">
  
  <main class="container clear">
    <h2 class="sectiontitle">삭제하기</h2>
    <div class="row">
      <form method=post action="../member/join_delete_ok.do">
	      <table class="table">
	       <tr>
	         <td class="inline">
	                비밀번호:<input type=password name=pwd size=25 class="input-sm">
	         </td>
	       </tr>
	       <tr>
	         <td class="text-center">
	           <input type=submit value="탈퇴" class="btn btn-sm btn-danger">
	           <input type=button value="취소" class="btn btn-sm btn-success"
	            onclick="javascript:history.back()">
	         </td>
	       </tr>
	      </table>
      </form>
    </div>
  </main>
</div>
</body>
</html>