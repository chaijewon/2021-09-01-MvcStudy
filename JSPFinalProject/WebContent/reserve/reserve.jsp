<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.reserve_row{
  width: 1200px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$.ajax({
		type:'post',
		url:'../reserve/food_list.do', /*ReserveModel => RequestMapping()*/
		success:function(result)
		{
			$('#food_list').html(result);
		}
	})
});
</script>
</head>
<body>
<div class="wrapper row3 reserve_row">
  <main class="container clear">
    <h2 class="sectiontitle">맛집 예약</h2>
    <table class="table">
     <tr>
      <td width="20%" valign="top" class="text-center" height="500">
        <table class="table">
          <caption><h3>맛집정보</h3></caption>
          <tr>
            <td>
              <div style="overflow-y:auto;height:500px" id="food_list"></div>
            </td>
          </tr>
        </table>
      </td>
      <td width="40%" valign="top" class="text-center" height="500">
        <table class="table">
          <caption><h3>예약일정보</h3></caption>
          <tr>
           <td id="food_date">
            
           </td>
          </tr>
        </table>
      </td>
      <td rowspan=2 width="40%" valign="top" class="text-center" height="700">
        <table class="table">
          <caption><h3>예약 정보</h3></caption>
          <tr>
            <td class="text-center" colspan="2">
              <img src="" style="width:300px;height:300px" id="food_poster">
            </td>
          </tr>
          <tr>
            <td class="text-right" style="width:30%">맛집명</td>
            <td id="food_name" style="width:70%"></td>
          </tr>
          <tr>
            <td class="text-right" style="width:30%">예약일</td>
            <td id="food_date1" style="width:70%"></td>
          </tr>
        </table>
      </td>
     </tr>
     <tr>
       <td width="35%" valign="top" class="text-center" height="200">
         <table class="table">
          <caption><h3>시간 정보</h3></caption>
         </table>
       </td>
       <td width="35%" valign="top" class="text-center" height="200">
         <table class="table">
          <caption><h3>인원 정보</h3></caption>
         </table>
       </td>
     </tr>
    </table>
    </main>
   </div>
</body>
</html>







