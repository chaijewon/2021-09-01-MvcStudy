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
    <form method="post" action="../notice/insert_ok.do">
    <table class="table">
      <tr>
        <th width="20%" class="text-right">공지제목</th>
        <td width="80%">
          <input type=text name=subject size=60 class="input-sm">
        </td>
      </tr>
      <tr>
        <th width="20%" class="text-right">공지내용</th>
        <td width="80%">
          <textarea rows="10" cols="60" name=content></textarea>
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
          <input type=submit value="글쓰기" class="btn btn-sm btn-danger">
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