<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  
  <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script type="text/javascript">
  $(function() {
    $("#tabs").tabs();
    
    $('#emailBtn').click(function(){
    	let email=$('#email').val();
    	if(email.trim()=="")
    	{
    		$('#email').focus();
    		return;
    	}
    	$.ajax({
    		type:'post',
    		url:'../member/idfind_email.do',
    		data:{"email":email},
    		success:function(result)
    		{
    			let s=result.trim();
    			if(s=="no")
    			{
    				alert("이메일이 존재하지 않습니다");
    				$('#email').val("");
    			}
    			else
    			{
    			    $('#email_print').text(s);	
    			}
    		}
    	})
    });
    
    $('#telBtn').click(function(){
    	let tel2=$('#tel2').val();
    	if(tel2.trim()=="")
    	{
    		$('#tel2').focus();
    		return;
    	}
    	let tel3=$('#tel3').val();
    	if(tel3.trim()=="")
    	{
    		$('#tel3').focus();
    		return;
    	}
    	$.ajax({
    		type:'post',
    		url:'../member/idfind_tel.do',
    		data:{"tel":"010-"+tel2+"-"+tel3},
    		success:function(result)
    		{
    			let s=result.trim();
    			if(s=="no")
    			{
    				alert("전화번호가 존재하지 않습니다");
    				$('#tel2').val("");
    				$('#tel3').val("");
    			}
    			else
    			{
    			    $('#tel_print').text(s);	
    			}
    		}
    	})
    });
    
  });
  </script>
</head>
<body>
	<div class="wrapper row3 mypage_row">
	  <main class="container clear"> 
	  <h2 class="sectiontitle">아이디 찾기</h2>
	  <div style="height: 500px;width:550px;overflow-y:auto;margin:0px auto">
	    <div id="tabs">
		  <ul>
		    <li><a href="#tabs-1">이메일로 찾기</a></li>
		    <li><a href="#tabs-2">전화번호로 찾기</a></li>
		  </ul>
		  <div id="tabs-1">
		    <p>이메일 입력</p>
		    <p><input type=text id=email size=20 class="input-sm" style="float:left">
		      <input type=button value="찾기" class="btn btn-sm btn-danger" id="emailBtn">
		    </p>
		    <p></p>
		    <p id="email_print" style="color:red;font-size: 15pt"></p>
		  </div>
		  <div id="tabs-2">
		    <p>전화번호 입력</p>
		    <p><input type=text id=tel1 size=5 class="input-sm" value="010" readonly style="float:left">
		      <input type=text id=tel2 size=10 class="input-sm" style="float:left">
		      <input type=text id=tel3 size=10 class="input-sm" style="float:left">
		      <input type=button value="찾기" class="btn btn-sm btn-danger" id="telBtn">
		    </p>
		    <p id="tel_print" style="color:red;font-size: 15pt"></p>
		  </div>
		</div>
      </div>
      </main>
    </div>
</body>
</html>
