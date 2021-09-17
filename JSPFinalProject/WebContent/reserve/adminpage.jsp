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
$(function(){
	$('.oks').click(function(){
		let no=$(this).attr("data-no");
		location.href="../reserve/ok.do?no="+no;
	})
})
/*
 *   Vue => new Vue({
	           el:'#app',
	           method:
	           data:
            })
      class / function (hooks) 
 */
</script>
</head>
<body>
<div class="wrapper row3 reserve_row">
  <main class="container clear">
    <h2 class="sectiontitle">맛집 예약목록</h2>
    <table class="table">
      <tr>
       <th class="text-center">번호</th>
       <th class="text-center">아이디</th>
       <th class="text-center">맛집명</th>
       <th class="text-center">예약일</th>
       <th class="text-center">시간</th>
       <th class="text-center">인원</th>
       <th class="text-center">승인여부</th>
      </tr>
      <c:forEach var="vo" items="${list }">
       <tr>
	       <td class="text-center">${vo.no }</td>
	       <td class="text-center">${vo.id }</td>
	       <td class="text-center">${vo.name }</td>
	       <td class="text-center">${vo.rday }</td>
	       <td class="text-center">${vo.rtime }</td>
	       <td class="text-center">${vo.inwon }</td>
	       <td class="text-center">
	         <c:if test="${vo.isCheck==0 }">
	            <%-- HTML에서 태그는 만들 수 없다 , 속성은 만들 수 있다 --%>
	            <span class="btn btn-sm btn-danger oks" data-no="${vo.no }">승인대기</span>
	            <%-- JSP프로젝트 : DB , Spring:lib중심(결제모듈)
	                                트랙젝션
	                 public void insert()
	                 {
	                     try
	                     {
	                         getConnection();
	                         conn.setAutoCommit(false);
	                         insert문장 
	                         String sql="";
	                         ps=conn.preparedStatement(sql);
	                         ps.setString()
	                         ps.execteUpdate()
	                         insert문장 
	                         sql="";
	                         ps=conn.preparedStatement(sql);
	                         ps.setString()
	                         ps.execteUpdate()
	                         conn.commit()
	                     }catch(Exception ex)
	                     {
	                          conn.rollback();
	                     }
	                     finally
	                     {
	                         conn.setAutoCommit(true);
	                     }
	                 } 
	                 
	                 @Transactional
	                 public void insert()
	                 {
	                         insert문장  => insert(sql) => mybatis
	                         insert문장  => insert(sql) 
	                 }
	             --%>
	         </c:if>
	          <c:if test="${vo.isCheck==1 }">
	            <span class="btn btn-sm btn-default">승인완료</span>
	         </c:if>
	       </td>
      </tr>
      </c:forEach>
    </table>

  </main>
</div>
</body>
</html>