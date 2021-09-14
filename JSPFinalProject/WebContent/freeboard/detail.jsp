<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
// 더보기 
let u=0;
$(function(){
	$('.ubtn').click(function(){
		let no=$(this).attr("data-no");
		$('.updateForm').hide();
		if(u==0)
		{
			$(this).text("취소");
			$('#u'+no).show();
			u=1;
		}
		else
		{
			$(this).text("수정");
			$('#u'+no).hide();
			u=0;
		}
	})
})
</script>
</head>
<body>
<div class="wrapper row3">
  <main class="container clear">
    <table class="table">
      <tr>
        <th class="text-center" width=20%>번호</th>
        <td class="text-center" width=30%>${vo.no }</td>
        <th class="text-center" width=20%>작성일</th>
        <td class="text-center" width=30%>${vo.regdate }</td>
      </tr>
      <tr>
        <th class="text-center" width=20%>이름</th>
        <td class="text-center" width=30%>${vo.name }</td>
        <th class="text-center" width=20%>조회수</th>
        <td class="text-center" width=30%>${vo.hit }</td>
      </tr>
      <tr>
        <th class="text-center" width=20%>제목</th>
        <td colspan="3">${vo.subject }</td>
      </tr>
      <tr>
        <td colspan="4" class="text-left" valign="top" height="200">
         <pre style="white-space: pre-wrap;border:none;background-color:white">${vo.content }</pre>
        </td>
      </tr>
      <tr>
        <td colspan="4" class="text-right">
          <a href="../freeboard/update.do?no=${vo.no }" class="btn btn-xs btn-success">수정</a>
          <a href="../freeboard/delete.do?no=${vo.no }" class="btn btn-xs btn-info">삭제</a>
          <a href="../freeboard/list.do" class="btn btn-xs btn-warning">목록</a>
        </td>
      </tr>
    </table>
    <div id="comments">
        <h2>댓글</h2>
        <ul>
         <c:forEach var="rvo" items="${list }">
	          <li>
	            <article>
	              <header>
	                <figure class="avatar">
	                  <c:if test="${sessionScope.id==rvo.id }">
	                    <a href="#" class="btn btn-xs btn-danger ubtn" style="color:black" data-no="${rvo.no }">수정</a>
	                    <a href="../freeboard/reply_delete.do?no=${rvo.no }&bno=${vo.no}" class="btn btn-xs btn-success" style="color:black">삭제</a>
	                  </c:if>
	                </figure>
	                <address>
	                By <a href="#">${rvo.name }</a>
	                </address>
	                <span>(${rvo.dbday })</span>
	              </header>
	              <div class="comcont">
	                <p>${rvo.msg }</p>
	              </div>
	            </article>
	            <form method="post" action="../freeboard/reply_update.do" 
	              class="updateForm" id="u${rvo.no }" style="display:none">
			       <table class="table">
			          <tr>
			            <td class="inline">
			             <input type=hidden value="${vo.no }" name="bno">
                         <input type=hidden value="${rvo.no }" name="no">
			             <textarea rows="5" cols="80" name="msg" style="float:left">${rvo.msg }</textarea>
			             <input type="submit" class="btn btn-sm btn-primary"
			              style="height:100px" value="댓글수정" style="float:left">
			            </td>
			          </tr>
			         </table>
			      </form>
	          </li>
          </c:forEach>
         </ul>
      </div>
      <%-- 로그인이 된 상태 (id(fk),name(not null)) --%>
      <%--
                        요청 
             <a href="../movie/list.do">
             <form action="../freeboard/reply_insert.do">
             Ajax => button 클릭 
             === $.ajax({
                      url:"../freeboard/reply_insert.do"
                 })
       --%>
      <c:if test="${sessionScope.id!=null }">
         <form method="post" action="../freeboard/reply_insert.do">
	       <table class="table">
	          <tr>
	            <td class="inline">
	             <textarea rows="5" cols="115" name="msg"></textarea>
	             <input type=hidden value="${vo.no }" name="bno">
	             <input type=hidden value="1" name="type">
	             <input type="submit" class="btn btn-sm btn-primary"
	              style="height:100px" value="댓글쓰기">
	            </td>
	          </tr>
	         </table>
	      </form>
        </c:if>
  </main>
</div>
</body>
</html>