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
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#idcheckBtn').click(function(){
		// id입력 확인
		let id=$("#id").val();
		if(id.trim()=="")
		{
			$('#id').focus();
			return;
		}
		// 요청처리 , 응답처리를 동시에 한다 
		$.ajax({
			type:'POST',
			url:'../member/idcheck.do',
			data:{"id":id}, // 데이터 전송 idcheck.do?id=hong
			success:function(res) //정상수행시에 => res는 실행된 모든 데이터를 읽어온다 
			{
				let count=res.trim();// 출력된 숫자를 받는다 (0,1)
				if(count==0) //id가 없는 경우
				{
					$('#result').html('<span style="color:blue">'+id+'는(은) 사용가능합니다</span>');
					$('#ok').show(); // 확인 버튼 출력 
				}
				else //id가 있는 경우
				{
					$('#result').html('<span style="color:red">'+id+'는(은) 이미 사용중입니다</span>');
				}
			}
		})
	})
	$('#okBtn').click(function(){
		parent.joinFrm.id.value=$('#id').val();// 사용가능한 id를 회원가입창에 전송
		parent.Shadowbox.close();// Shadowbox 닫기 
	})
})
</script>
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
         <td class="text-center" id="result"></td>
       </tr>
       <tr id="ok" style="display:none">
         <td class="text-center">
           <input type=button value="확인" class="btn btn-sm btn-success" id=okBtn>
         </td>
       </tr>
      </table>
     </div>
   </div>
</body>
</html>




