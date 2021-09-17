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
    <div class="content">
	    <table class="table">
	        <tr>
	         <c:forTokens var="image" items="${vo.poster }" delims="^">
	           <td class="text-center"><img src="${image }" style="width:100%"></td>
	         </c:forTokens>
	        </tr>
	      </table>
    </div>
    <div class="clear"></div>
    <div class="content two_quarter first"> 
      <table class="table">
        <tr>
          <td colspan="2">
           <h3>${vo.name }&nbsp;<span style="color:orange">${vo.score }</span></h3>
          </td>
        </tr>
        <tr>
         <th class="text-center">예약번호</th>
         <td>${ rvo.no }</td>
        </tr>
        <tr>
         <th class="text-center">예약일</th>
         <td>${ rvo.rday }</td>
        </tr>
        <tr>
         <th class="text-center">예약시간</th>
         <td>${ rvo.rtime }</td>
        </tr>
        <tr>
         <th class="text-center">예약시간</th>
         <td>${ rvo.rtime }</td>
        </tr>
        <tr>
         <th class="text-center">예약인원</th>
         <td>${ rvo.inwon}</td>
        </tr>
        <tr>
         <th class="text-center">주소</th>
         <td>${ vo.addr1 }
         <br><sup>${vo.addr2 }</sup>
         </td>
        </tr>
        <tr>
          <th class="text-center">전화</th>
          <td>${vo.tel }</td>
        </tr>
        <tr>
          <th class="text-center">음식종류</th>
          <td>${vo.type }</td>
        </tr>
        <c:if test="${vo.price!='no' }">
	        <tr>
	          <th class="text-center">가격대</th>
	          <td>${vo.price }</td>
	        </tr>
        </c:if>
        <c:if test="${vo.parking!='no' }">
	        <tr>
	          <th class="text-center">주차</th>
	          <td>${vo.parking }</td>
	        </tr>
        </c:if>
        <c:if test="${vo.time!='no' }">
	        <tr>
	          <th class="text-center">영업시간</th>
	          <td>${vo.time }</td>
	        </tr>
        </c:if>
        <c:if test="${vo.menu!='no' }">
	        <tr>
	          <th class="text-center">메뉴</th>
	          <td>
	            <%--
	              사시미8종 (2인) 41,000원 마구로치즈아에 19,000원 양갈비 스미비 야끼 23,000원 노도구로 시오 야끼 35,000원규 스테이크 41,000원
	             --%>
	            <ul>
	              <%-- ^ | 원 --%>
	              <c:forTokens items="${vo.menu }" delims="원" var="m">
	                <li>${m }원</li>
	              </c:forTokens>
	              <%--
	                 StringTokenizer st=new StringToeknizer(items,delims)
	                 StringTokenizer st=new StringToeknizer(vo.menu,"원")
	                 while(st.hasMoreTokens())
	                 {
	                     String m=st.nextToken();
	                 }
	               --%>
	            </ul>
	          </td>
	        </tr>
        </c:if>
      </table>
    </div>
    <div class="sidebar two_quarter">
      <%-- 지도 --%>
      <div id="map" style="width:100%;height:350px;"></div>

		
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
		geocoder.addressSearch('${vo.addr1}', function(result, status) {
		
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
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">${vo.name}</div>'
		        });
		        infowindow.open(map, marker);
		
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		});    
		</script>
	</div>
 </main>
 </div>
	
</body>
</html>