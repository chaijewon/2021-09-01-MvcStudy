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
	$('.foods').hover(function(){
		$(this).css('cursor','pointer');
	},function(){
		$(this).css('cursor','none');
	})
	
	$('.foods').click(function(){
		$.ajax({
			type:'post',
			url:'../reserve/date.do',
			success:function(result)
			{
				$('#food_date').html(result);
			}
		});
		let poster=$(this).attr("data-poster");
		let name=$(this).attr("data-name");
		let no=$(this).attr("data-no");
		$('#food_poster').attr("src",poster);
		$('#food_name').text(name);
		
		$('#fno').val(no);
	})
})
</script>
</head>
<body>
  <table class="table">
    <c:forEach var="vo" items="${list }">
     <tr class="foods" data-no="${vo.no }" data-name="${vo.name }" 
        data-poster="${vo.poster }">
      <td class="text-center">
        <img src="${vo.poster }" style="width:30px;height:30px;">
      </td>
      <td class="text-left">
        ${vo.name }
      </td>
     </tr>
    </c:forEach>
  </table>
</body>
</html>