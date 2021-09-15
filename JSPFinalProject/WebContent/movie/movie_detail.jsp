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
    <table class="table">
     <tr>
       <td>
        <embed src="http://youtube.com/embed/${vo.key }" style="width:970px;height:350px">
       </td>
     </tr>
    </table>
    <table class="table">
      <tr>
        <td width=30% rowspan="9" class="text-center">
         <img src="${vo.poster }" style="width:290px;height:350px">
        </td>
        <td width=70% colspan="2"><h3>${vo.title }&nbsp;<span style="color:orange">${vo.score }</span></h3></td>
      </tr>
      <tr>
        <th class="text-center" width=10%>감독</th>
        <td width=60%>${vo.director }</td>
      </tr>
      <tr>
        <th class="text-center" width=10%>출연</th>
        <td width=60%>${vo.actor }</td>
      </tr>
      <tr>
        <th class="text-center" width=10%>장르</th>
        <td width=60%>${vo.genre }</td>
      </tr>
      <tr>
        <th class="text-center" width=10%>시간</th>
        <td width=60%>${vo.time }분</td>
      </tr>
      <tr>
        <th class="text-center" width=10%>등급</th>
        <td width=60%>${vo.grade }</td>
      </tr>
      <tr>
        <th class="text-center" width=10%>상영일</th>
        <td width=60%>${vo.regdate }</td>
      </tr>
      <tr>
        <th class="text-center" width=10%>예매율</th>
        <td width=60%>${vo.reserve }</td>
      </tr>
      <tr>
        <th class="text-center" width=10%>누적관객</th>
        <td width=60%>${vo.showUser }</td>
      </tr>
      <tr>
        <td colspan="3">
          ${vo.story }
        </td>
      </tr>
      <tr>
        <td colspan="3" class="text-right">
         <a href="#" class="btn btn-sm btn-success">예매</a>
         <a href="#" class="btn btn-sm btn-info">추천</a>
         <a href="../movie/movie_list.do?cno=${cno }" class="btn btn-sm btn-danger">목록</a>
        </td>
      </tr>
    </table>
  </main>
</div>
</body>
</html>