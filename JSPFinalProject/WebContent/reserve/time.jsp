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
	$('.times').hover(function(){
		$(this).css("cursor","pointer");
	},function(){
		$(this).css("cursor","none");
	})
	$('.times').click(function(){
		let time=$(this).text();
		$('#food_time1').text(time);
		$('#ftime').val(time);
		$.ajax({
			type:'post',
			url:'../reserve/inwon.do',
			success:function(result)
			{
				$('#food_inwon').html(result);
			}
		})
	})
	
})
</script>
</head>
<body>
  <table class="table">
    <tr>
     <td class="text-center">
       <c:forEach var="time" items="${list }">
        <span class="times btn btn-sm btn-info">${time }</span>
       </c:forEach>
     </td>
    </tr>
  </table>
</body>
</html>