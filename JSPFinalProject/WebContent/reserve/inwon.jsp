<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.inwons').hover(function(){
		$(this).css('cursor','pointer')
	},function(){
		$(this).css('cursor','none')
	})
	
	$('.inwons').click(function(){
		let inwon=$(this).text();
		$('#food_inwon1').text(inwon+"명");
		$('#finwon').val(inwon);
		$('#show_btn').show()
	})
})
</script>
</head>
<body>
  <table class="table">
    <tr>
     <td class="text-center">
       <c:forEach var="i" begin="1" end="9">
         <span class="inwons btn btn-sm btn-success">${i }</span>
       </c:forEach>
     </td>
    </tr>
  </table>
</body>
</html>