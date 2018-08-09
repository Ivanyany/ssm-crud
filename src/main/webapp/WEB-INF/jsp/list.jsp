<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入jquery -->
<script type="text/javascript" src="<c:url value='/static/js/jquery-3.2.1.min.js'/>"></script>
<!-- 引入bootstrap -->
<link href="<c:url value='/static/bootstrap-3.3.7/css/bootstrap.min.css'/>" rel="stylesheet">
<script src="<c:url value='/static/bootstrap-3.3.7/js/bootstrap.min.js'/>"></script>
<title>员工列表</title>
</head>
<body>
	<!-- 搭建显示页面 -->
	<div class="container ">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12"><!-- bootstrap将一行分成12列 -->
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8"><!-- 占4列,偏移8列 -->
				<button class="btn btn-primary">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover">
					<tr>
						<th>#</th>
						<th>员工姓名</th>
						<th>员工性别</th>
						<th>邮箱地址</th>
						<th>所属部门</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${pageinfo.list }" var="emp">
						<tr>
							<th>${emp.id }</th>
							<th>${emp.name }</th>
							<th>${emp.gender }</th>
							<th>${emp.email }</th>
							<th>人事部</th>
							<th>
								<button class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									编辑
								</button>
								<button class="btn btn-danger btn-sm">
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
									删除
								</button>
							</th>
						</tr>
					</c:forEach>					
				</table>
			</div>
		</div>
		<!-- 显示分页信息 -->
		<div class="row">
			<!-- 分页文字信息 -->
			<div class="col-md-6">
				当前${pageinfo.pageNum }页，总${pageinfo.pages }页，总${pageinfo.total }条记录
			</div>
			<!-- 分页条信息 -->
			<div class="col-md-6">
				<nav aria-label="Page navigation">
				  <ul class="pagination">
				  	<li><a href="<c:url value='/emps?pn=1'/>">首页</a></li>
				  	<c:if test="${pageinfo.hasPreviousPage }">
				  		<li>
					      <a href="<c:url value='/emps?pn=${pageinfo.pageNum - 1 }'/>" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
				    	</li>
				  	</c:if>
				    
				    <c:forEach items="${pageinfo.navigatepageNums }" var="page_Num">
				    	<c:if test="${page_Num == pageinfo.pageNum }"><!-- 显示的页码是否是当前页,如果是则显示为高亮,否则显示为普通 -->
				    		<li class="active"><a href="#">${page_Num }</a></li><!-- 显示为高亮 -->
				    	</c:if>
				    	<c:if test="${page_Num != pageinfo.pageNum }">
				    		<li><a href="<c:url value='/emps?pn=${page_Num }'/>">${page_Num }</a></li><!-- 显示为普通 -->
				    	</c:if>
				    </c:forEach>
				    
				    <c:if test="${pageinfo.hasNextPage }">
					    <li>
					      <a href="<c:url value='/emps?pn=${pageinfo.pageNum + 1 }'/>" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
				    </c:if>
				    <li><a href="<c:url value='/emps?pn=${pageinfo.pages }'/>">尾页</a></li>
				  </ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>