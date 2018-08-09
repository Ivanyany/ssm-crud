<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
</head>
<body>
	<h1>注册</h1>
	<form action="<c:url value='/regist'/>" method="post">
		<input type="text" name="name" placeholder="请输入用户名"><br>
		<input type="text" name="age" placeholder="请输入密码"><br>
		<input type="submit" value="注册" />
		<input type="reset" value="重置" />
	</form>
</body>
</html>