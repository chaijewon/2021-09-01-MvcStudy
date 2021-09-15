<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
  <div class="wrapper row3">
    <main class="container clear"> 
      <h1 class="text-center">${vo.title }</h1>
      <table class="table">
        <tr>
         <td>
          <img src="${vo.poster }" style="width:970px;height:450px">
         </td>
        </tr>
        <tr>
          <td>${vo.msg }</td>
        </tr>
        <tr>
          <td>${vo.address }</td>
        </tr>
        <tr>
          <td class="text-right"><a href="../seoul/seoul_1.do" class="btn btn-sm btn-danger">목록</a></td>
        </tr>
      </table>
      <%-- 지도  --%>
      <div id="map" style="width:100%;height:350px;"></div>

		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=676eb5fa2637b234997b24dd7566e9ba&libraries=services"></script>
		<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  
		
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch('${addr}', function(result, status) {
		
		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {
		
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
		
		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">${vo.title}</div>'
		        });
		        infowindow.open(map, marker);
		
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		});    
		</script>
      <%-- 인근 맛집 --%>
      <h2 class="sectiontitle">근처 맛집 리스트</h2>
	    <!-- ################################################################################################ -->
	    <div class="flexslider carousel basiccarousel btmspace-80">
	      <ul class="slides">
	       <c:forEach var="vo" items="${list }" varStatus="s">
	        <c:if test="${s.index>=0 && s.index<12 }">
		         <li>
		          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" style="width:320px;height:185px">
		            <figcaption><a href="../food/food_list.do?cno=${vo.cno }">${vo.title }</a></figcaption>
		          </figure>
		         </li>
	         </c:if>
	       </c:forEach>  
	      </ul>
	    </div>
	    <h2 class="sectiontitle">근처 호텔 리스트</h2>
	    <!-- ################################################################################################ -->
	    <div class="flexslider carousel basiccarousel btmspace-80">
	      <ul class="slides">
	       <c:forEach var="vo" items="${list }" varStatus="s">
	        <c:if test="${s.index>=0 && s.index<12 }">
		         <li>
		          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" style="width:320px;height:185px">
		            <figcaption><a href="../food/food_list.do?cno=${vo.cno }">${vo.title }</a></figcaption>
		          </figure>
		         </li>
	         </c:if>
	       </c:forEach>  
	      </ul>
	    </div>
      <%-- 댓글 --%>
    </main>
  </div>
</body>
</html>