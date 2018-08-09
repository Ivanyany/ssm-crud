<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<<jsp:forward page="/emps"></jsp:forward><!-- 转到员工列表 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>普通形式</title>
<!-- 引入jquery -->
<script type="text/javascript" src="<c:url value='/static/js/jquery-3.2.1.min.js'/>"></script>
<!-- 引入bootstrap -->
<link href="<c:url value='/static/bootstrap-3.3.7/css/bootstrap.min.css'/>" rel="stylesheet">
<script src="<c:url value='/static/bootstrap-3.3.7/js/bootstrap.min.js'/>"></script>
</head>
<body>

	<button class="btn btn-success">Hello</button>

</body>
</html>