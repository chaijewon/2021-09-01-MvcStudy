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
   width:500px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
// jquery시작점 (jquery:javascript라이브러리) => window.onload=function(){}
// main()
$(function(){
	// dong을 받는다 
	let dong=$('#dong').val();
	if(dong.trim()=="") // 공백문자 제거 (space문자)
	{
		$('#dong').focus();
		return;
	}
	$.ajax({
		type:'post',
		url:'../member/postfind.do', // 요청 
		data:{"dong":dong},
		// 증권 , 좌석
		success:function(res) //응답  ==> 한곳에서 요청/응답을 동시에 처리 (페이지유지 상태에서 데이터 읽기)
		{
			$('#print').html(res);
		}
		
	})
})
</script>
</head>
<body>
  <div class="container">
    <div class="row">
      <table class="table">
       <tr>
         <td>
         입력:<input type=text name=dong id=dong size=15 class="input-sm">
         <input type=button value="검색" class="btn btn-sm btn-primary" id="postBtn">
         </td>
       </tr>
       <tr>
        <td class="text-right">
          <sup style="color:red">※동/읍/면을 입력하세요</sup>
        </td>
       </tr>
      </table>
      <div id="print"></div>
    </div>
  </div>
</body>
</html>




