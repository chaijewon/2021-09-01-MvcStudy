<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Gravity</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="../layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
<!-- <link href="../layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
<link rel="stylesheet" href="https://korean.visitseoul.net/humanframe/theme/visitseoul/assets/plugin/ionicons-2.0.1/css/ionicons.min.css?20170602_3" />
<link rel="stylesheet" href="https://korean.visitseoul.net/humanframe/theme/visitseoul/assets/plugin/jquery.bxslider/jquery.bxslider.css?20170602_3" />
<link rel="stylesheet" href="https://korean.visitseoul.net/humanframe/theme/visitseoul/assets/plugin/jquery-ui-1.11.4/jquery-ui.min.css?20170602_3" />
<link rel="stylesheet" href="https://korean.visitseoul.net/humanframe/theme/visitseoul/assets/plugin/lightGallery/dist/css/lightgallery.css?20170602_3" />
<link rel="stylesheet" href="https://korean.visitseoul.net/humanframe/theme/visitseoul/assets/style/base.css?bust=202107291909" />
<link rel="stylesheet" href="https://korean.visitseoul.net/humanframe/theme/visitseoul/assets/style/sub.css?20201209114337" />
<link rel="stylesheet" href="https://korean.visitseoul.net/humanframe/theme/visitseoul/assets/style/print.css" media="print" /> -->
<link rel="stylesheet" href="https://korean.visitseoul.net/humanframe/theme/visitseoul/assets/style/guidebook.css?20201209114337" />
<link rel="stylesheet" href="https://korean.visitseoul.net/humanframe/theme/visitseoul/assets/style/mayday_sub.css?20201209114337" />

</head>
<body id="top">
<%--
     메뉴가 들어가는 위치
 --%>
<jsp:include page="header.jsp"></jsp:include><%-- 고정 --%>
<jsp:include page="${main_jsp }"></jsp:include> <%-- 메뉴별 변경 --%>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<jsp:include page="footer.jsp"></jsp:include><%-- 고정 --%>
<!-- JAVASCRIPTS --> 
<script src="../layout/scripts/jquery.min.js"></script> 
<script src="../layout/scripts/jquery.backtotop.js"></script> 
<script src="../layout/scripts/jquery.mobilemenu.js"></script> 
<script src="../layout/scripts/jquery.flexslider-min.js"></script>
</body>
</html>