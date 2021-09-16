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
<%--
      요청 처리 => Model (요청 / 응답) => @Controller
            ==================
            .do 
 --%>
<div class="wrapper row3">
  
  <main class="container clear">
  <h2 class="sectiontitle">수정하기</h2>
    <form method="post" action="../freeboard/update_ok.do">
    <table class="table">
      <tr>
        <th width="20%" class="text-right">이름</th>
        <td width="80%">
          <input type=text name=name size=20 class="input-sm" value="${vo.name }">
          <input type=hidden name=no value="${vo.no }">
        </td>
      </tr>
      <tr>
        <th width="20%" class="text-right">제목</th>
        <td width="80%">
          <input type=text name=subject size=60 class="input-sm" value="${vo.subject }">
        </td>
      </tr>
      <tr>
        <th width="20%" class="text-right">내용</th>
        <td width="80%">
          <textarea rows="10" cols="60" name=content>${vo.content }</textarea>
        </td>
      </tr>
      <tr>
        <th width="20%" class="text-right">비밀번호</th>
        <td width="80%">
          <input type=password name=pwd size=15 class="input-sm">
        </td>
      </tr>
      <%--
             브라우저와 관련 (javascript) => 라이브러리  (Jquery) => front-end
             데이터베이스 관련 (java)  => 라이브러리 .jar =. back-end
             화면 출력 (html/css)  => css(라이브러리) => bootstrip = 퍼블리셔(디자인너)
             ====> 웹 사이트 제작 
             DBA(X) => 5년 경력 
       --%>
      <tr>
        <td class="text-center" colspan="2">
          <input type=submit value="수정" class="btn btn-sm btn-danger">
          <input type=button value="취소" class="btn btn-sm btn-success"
            onclick="javascript:history.back()"
          >
        </td>
      </tr>
    </table>
    </form>
  </main>
</div>
</body>
</html>