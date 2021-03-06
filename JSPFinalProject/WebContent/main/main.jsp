<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 조립 (include) --%>
<!DOCTYPE html>
<html>
<head>
<title>예약/추천 시스템</title>
<meta charset="utf-8">
<!--  
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
-->
<link href="../layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">

</head>
<body id="top">
<%--header --%>
<%@ include file="../main/header.jsp" %>
<%-- content: 수시로 변경 --%>
<jsp:include page="${main_jsp }"></jsp:include>
<%-- footer --%>
<%@ include file="../main/footer.jsp" %>
<script src="../layout/scripts/jquery.min.js"></script> 
<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="../layout/scripts/jquery.backtotop.js"></script> 
<script src="../layout/scripts/jquery.mobilemenu.js"></script> 
<script src="../layout/scripts/jquery.flexslider-min.js"></script>
</body>
</html>