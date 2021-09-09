<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
     회원가입 : 아이디중복체크 / 우편번호 검색 
           ======================
     로그인  (회원수정 , 아이디찾기 , 회원 탈퇴) 
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
// 매개변수설정 => 변수명만 사용한다 
function ok(zip,addr)
{
	// 데이터를 join.jsp => 전송
	parent.joinFrm.post1.value=zip.substring(0,3);//012 (3 - )
	parent.joinFrm.post2.value=zip.substring(4,7);//456
	parent.joinFrm.addr1.value=addr;
	parent.Shadowbox.close();
}
</script>
</head>
<body>
  <c:if test="${count==0 }">
    <table class="table">
      <tr>
        <td class="text-center">
          <span style="color:red">검색된 결과가 없습니다</span>
        </td>
      </tr>
    </table>
  </c:if>
  <c:if test="${count!=0 }">
     <table class="table">
       <tr>
         <th class="text-center">우편번호</th>
         <th class="text-center">주소</th>
       </tr>
       <c:forEach var="vo" items="${list }">
         <tr>
           <td class="text-center">${vo.zipcode }</td>
           <%-- 자바스크립트의 매개변수 = 숫자,문자 (문자일때 '') --%>
           <td><a href="javascript:ok('${vo.zipcode }','${vo.address }')">${vo.address }</a></td>
         </tr>
       </c:forEach>
     </table>
  </c:if>
</body>
</html>




