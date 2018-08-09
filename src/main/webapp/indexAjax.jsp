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
<title>Ajax形式请求数据</title>
<style type="text/css">
.pointer{
	cursor:pointer;
}
</style>
</head>
<body>

	<!-- ====================添加员工Modal框:点击新增按钮时打开模态框==================== -->
	<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">员工新增</h4>
	      </div>
	      <div class="modal-body">
	      	<form class="form-horizontal">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">员工姓名</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="name" id="empName_add_input" placeholder="员工姓名">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">员工性别</label>
				    <div class="col-sm-10">
					    <label class="radio-inline">
						 	<input type="radio" name="gender" id="gender1_add_input" value="M" checked="checked">男
						</label>
						<label class="radio-inline">
							<input type="radio" name="gender" id="gender2_add_input" value="F">女
						</label>
			    	</div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">邮箱地址</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="email" id="email_add_input" placeholder="ivan@163.com">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">所属部门</label>
				    <div class="col-sm-4">
				    	<!-- 部门 -->
					    <select class="form-control" name="dept_id"></select>
			    	</div>
			  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- ====================修改员工Modal框:点击编辑按钮时打开模态框==================== -->
	<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">员工修改</h4>
	      </div>
	      <div class="modal-body">
	      	<form class="form-horizontal">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">员工姓名</label>
			    <div class="col-sm-10">
			      <p class="form-control-static" id="empName_update_static"></p>
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">员工性别</label>
				    <div class="col-sm-10">
					    <label class="radio-inline">
						 	<input type="radio" name="gender" id="gender1_update_input" value="M" checked="checked">男
						</label>
						<label class="radio-inline">
							<input type="radio" name="gender" id="gender2_update_input" value="F">女
						</label>
			    	</div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">邮箱地址</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="email" id="email_update_input" placeholder="ivan@163.com">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">所属部门</label>
				    <div class="col-sm-4">
				    	<!-- 部门 -->
					    <select class="form-control" name="dept_id"></select>
			    	</div>
			  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="emp_update_btn">更新</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- =============================================================================== -->

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
				<button class="btn btn-primary" id="emp_add_modal_btn">新增</button>
				<button class="btn btn-danger" id="emp_delete_all_btn">删除</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="emps_table">
					<thead>
						<tr>
							<th>
								<input type="checkbox" id="check_all"/>
							</th>
							<th>#</th>
							<th>员工姓名</th>
							<th>员工性别</th>
							<th>邮箱地址</th>
							<th>所属部门</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					
					</tbody>	
				</table>
			</div>
		</div>
		<!-- 显示分页信息 -->
		<div class="row">
			<!-- 分页文字信息 -->
			<div class="col-md-6" id="page_info_area">
				
			</div>
			<!-- 分页条信息 -->
			<div class="col-md-6" id="page_nav_area">
				
			</div>
		</div>
	</div>
	<script type="text/javascript">
		
		var totalRecord;//全局变量存储总记录数
		var currentPage;//全局变量存储当前页
	
		//1.页面加载完成后发送ajax请求,获取分页数据
		$(function(){
			//初始去首页
			to_page(1);
		});
		//调到指定页码
		function to_page(pn){
			$.ajax({
				url:"<c:url value='/empsjson'/>",
				data:"pn=" + pn,
				type:"GET",
				success:function(result){
					//1.解析并显示员工数据
					build_emps_table(result);
					//2.解析并显示分页信息
					build_page_info(result);
					//3.解析显示分页条数据
					build_page_nav(result);
				}
			});
		}
		
		//1.解析并显示员工数据
		function build_emps_table(result){
			//每次先清空表格
			$("#emps_table tbody").empty();
			console.log(result);
			var emps = result.extend.pageinfo.list;//获取员工数据
			$.each(emps,function(index,item){//遍历员工数据
				//创建复选框标签
				var checkBox = $("<td><input type='checkbox' class='check_item'/></td>");
				//创建员工数据的td标签
				var empId = $("<td></td>").append(item.id);
				var empName = $("<td></td>").append(item.name);
				var gender = item.gender=="M"?"男":"女";//判断男或女
				var empGender = $("<td></td>").append(gender);
				var empEmail = $("<td></td>").append(item.email);
				var empDeptName = $("<td></td>").append(item.department.name);
				
				//两个操作按钮  edit_btn用于标识一个编辑按钮,后面绑定点击事件
				var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
									.append($("<span></span>")
									.addClass("glyphicon glyphicon-pencil"))
									.append("编辑");
				//为编辑按钮添加自定义属性(标识id)
				editBtn.attr("edit_id",item.id);
				
				//两个操作按钮  delete_btn用于标识一个编辑按钮,后面绑定点击事件
				var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
									.append($("<span></span>")
									.addClass("glyphicon glyphicon-trash"))
									.append("删除");
				//为删除按钮添加自定义属性(标识id)
				delBtn.attr("del_id",item.id);				
				
				var opBtn = $("<td></td>").append(editBtn).append(" ").append(delBtn);
				
				//append()可以链式操作
				$("<tr></tr>").append(checkBox)
							.append(empId)
							.append(empName)
							.append(empGender)
							.append(empEmail)
							.append(empDeptName)
							.append(opBtn)
							.appendTo("#emps_table tbody");
			});
		}
		
		//2.解析并显示分页信息
		function build_page_info(result){
			//每次先清空
			$("#page_info_area").empty();
			$("#page_info_area").append("当前" + result.extend.pageinfo.pageNum + 
										"页，总" + result.extend.pageinfo.pages + 
										"页，总" + result.extend.pageinfo.total + "条记录");
			totalRecord = result.extend.pageinfo.total;
			currentPage = result.extend.pageinfo.pageNum;
		}
		
		//3.解析显示分页条数据
		function build_page_nav(result){
			//每次先清空
			$("#page_nav_area").empty();
			var ul = $("<ul></ul>").addClass("pagination");
			var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
			var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));//前一页
			
			//判断是否有前一页
			if(result.extend.pageinfo.hasPreviousPage == false){
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			} else{
				//首页点击事件
				firstPageLi.click(function(){
					to_page(1);
				});
				//前一页点击事件
				prePageLi.click(function(){
					to_page(result.extend.pageinfo.pageNum - 1);
				});
			}
			
			//添加首页和前一页
			ul.append(firstPageLi).append(prePageLi);
			
			//遍历中间页1,2,3,4,5
			$.each(result.extend.pageinfo.navigatepageNums,function(index,item){
				var numLi = $("<li></li>").append($("<a></a>").append(item));				
				//当前页高亮
				if(result.extend.pageinfo.pageNum == item){
					numLi.addClass("active");
				}
				numLi.click(function(){
					to_page(item);
				});
				//添加中间页
				ul.append(numLi);
			});
			
			var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));//后一页
			var lastPageLi = $("<li></li>").append($("<a></a>").append("尾页").attr("href","#"));
			//判断是否有下一页
			if(result.extend.pageinfo.hasNextPage == false){
				nextPageLi.addClass("disabled");
				lastPageLi.addClass("disabled");
			} else{
				//下一页点击事件
				nextPageLi.click(function(){
					to_page(result.extend.pageinfo.pageNum + 1);
				});
				//尾页点击事件
				lastPageLi.click(function(){
					to_page(result.extend.pageinfo.pages);
				});
			}
			
			//添加下一页和尾页
			ul.append(nextPageLi).append(lastPageLi);
			//添加导航标签
			var nav = $("<nav></nav>").append(ul);
			nav.appendTo($("#page_nav_area"));
			
			$("li").addClass("pointer");//为li添加手型图标
		}
		
		//重置表单
		function reset_form(ele){
			$(ele)[0].reset();//重置表单内容
			$(ele).find("*").removeClass("has-error has-success");//重置表单样式
			$(ele).find(".help-block").text("");//重置提示信息
		}
		//点击新增员工时打开模态框
		$("#emp_add_modal_btn").click(function(){
			//弹出模态框前清除数据--表单重置
			reset_form("#empAddModal form");
			
			//发送ajax请求,查询部门信息
			getDepts($("#empAddModal select"));
			
			//弹出模态框
			$("#empAddModal").modal({
				backdrop: "static"
			});
		});
		
		//查询所有部门信息并显示到下拉列表中
		function getDepts(ele){
			//每次先清空
			$(ele).empty();
			$.ajax({
				url:"<c:url value='/depts'/>",
				type:"GET",
				success:function(result){
					//result为服务器返回的部门信息
					//将部门信息显示到下拉列表中
					$.each(result.extend.depts,function(){
						var optionEl = $("<option></option>").append(this.name).attr("value",this.id);
						optionEl.appendTo(ele);
					});
				}
			});
		}
		
		//显示校验信息
		function show_validate_msg(ele,status,msg){
			//清除当前校验状态
			$(ele).parent().removeClass("has-success has-error");
			$(ele).next("span").text("");
			
			if(status=="success"){
				$(ele).parent().addClass("has-success");
				$(ele).next("span").text(msg);
			} else if(status=="error"){
				$(ele).parent().addClass("has-error");
				$(ele).next("span").text(msg);
			}
		}
		
		//校验表单数据--员工姓名和email
		function validate_add_form(){
			//正则表达式方式校验
			var name = $("#empName_add_input").val();
			//员工姓名校验
			var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5}$)/;//a-z、A-Z、0-9、_、-(6-16个) 或  汉字(2-5个)			
			if(!regName.test(name)){
				//alert("用户名可以是2-5个汉字或6-16位英文、数字、下划线、横杠组成");
				//$("#empName_add_input").parent().addClass("has-error");
				//$("#empName_add_input").next("span").text("用户名可以是2-5个汉字或6-16位英文、数字、下划线、横杠组成");
				show_validate_msg("#empName_add_input","error","用户名可以是2-5个汉字或6-16位英文、数字、下划线、横杠组成");
				return false;
			} else{
				//$("#empName_add_input").parent().addClass("has-success");
				//$("#empName_add_input").next("span").text("");
				show_validate_msg("#empName_add_input","success","");
			}
			
			var email = $("#email_add_input").val();
			//email校验			
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!regEmail.test(email)){
				//alert("邮箱地址非法！");
				//$("#email_add_input").parent().addClass("has-error");
				//$("#email_add_input").next("span").text("邮箱地址非法！");
				show_validate_msg("#email_add_input","error","邮箱地址非法");
				return false;
			} else{
				//$("#email_add_input").parent().addClass("has-success");
				//$("#email_add_input").next("span").text("");
				show_validate_msg("#email_add_input","success","");
			}
			return true;
		}
		
		//保存员工前先校验是否已存在
		$("#empName_add_input").change(function(){
			//发送ajax请求服务器校验用户名是否存在
			var empName = this.value;
			$.ajax({
				url:"<c:url value='/checkuser'/>",
				data:"empName="+empName,
				type:"POST",
				success:function(result){
					if(result.code==100){
						show_validate_msg("#empName_add_input","success","用户名可用");
						$("#emp_save_btn").attr("ajax-val","success");
					} else{
						show_validate_msg("#empName_add_input","error",result.extend.val_msg);
						$("#emp_save_btn").attr("ajax-val","error");
					}
				}
			});
		});
		
		//点击保存事件
		$("#emp_save_btn").click(function(){
			//1.模态框中的数据提交到服务器
			//对数据进行校验
			if(!validate_add_form()){
				return false;
			}
			//判断员工名是否可用
			if($(this).attr("ajax-val")=="error"){
				return false;
			}
			//2.发送ajax请求
			$.ajax({
				url:"<c:url value='/emp'/>",
				type:"POST",
				data:$("#empAddModal form").serialize(),
				success:function(result){
					//判断后端校验信息
					if(result.code==100){//校验成功
						//1.关闭模态框
						$("#empAddModal").modal("hide");
						//2.来到最后一页,显示新增信息页
						to_page(totalRecord);//传一个很大值会返回最后一页
					} else{//校验失败
						//显示校验信息
						if(result.extend.errorFields.name != undefined){
							//显示员工名校验信息
							show_validate_msg("#empName_add_input","success",result.extend.errorFields.name);
						}
						if(result.extend.errorFields.email != undefined){
							//显示邮箱校验信息
							show_validate_msg("#email_add_input","error",result.extend.errorFields.email);
						}
					}
				}
			});
		});
		
		//编辑按钮,按钮创建之前绑定了click,绑定不上
		//解决:jquery on
		//edit_btn用class类型标识一个btn
		$(document).on("click",".edit_btn",function(){
			//查询员工信息
			getEmp($(this).attr("edit_id"));//发送ajax请求,根据id查询员工信息
			
			//将员工id传递给更新按钮
			$("#emp_update_btn").attr("edit_id",$(this).attr("edit_id"));
			
			//查询部门信息			
			getDepts($("#empUpdateModal select"));//发送ajax请求,查询部门信息
			
			$("#empUpdateModal").modal({
				backdrop:"static"
			});
		});
		
		//查询员工信息显示到修改模态框中
		function getEmp(id){
			$.ajax({
				url:"<c:url value='/emp/'/>"+id,
				type:"GET",
				success:function(result){
					console.log(result);
					var emp=result.extend.emp;
					$("#empName_update_static").text(emp.name);//给标签赋值
					$("#email_update_input").val(emp.email);//给文本框赋值
					$("#empUpdateModal input[name='gender']").val([emp.gender]);//单选框选中
					$("#empUpdateModal select").val([emp.dept_id]);//下拉框选中
				}
			});
		}
		
		//点击更新按钮,更新员工信息
		$("#emp_update_btn").click(function(){
			//邮箱校验
			var email = $("#email_update_input").val();		
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!regEmail.test(email)){
				show_validate_msg("#email_update_input","error","邮箱地址非法");
				return false;
			} else{
				show_validate_msg("#email_update_input","success","");
			}
			//发送ajax请求,保存更新的数据
			$.ajax({
				url:"<c:url value='/emp/'/>"+$(this).attr("edit_id"),
				type:"PUT",
				data:$("#empUpdateModal form").serialize(),
				success:function(result){
					//关闭模态框
					$("#empUpdateModal").modal("hide");
					//回到页面
					to_page(currentPage);
				}
			})
		});
		
		//删除员工
		//删除按钮创建之前绑定了click,绑定不上
		//解决:jquery on
		//edit_btn用class类型标识一个btn
		$(document).on("click",".delete_btn",function(){
			//弹出确认删除删除框
			var empName = $(this).parents("tr").find("td:eq(2)").text();//第3个td元素,索引为2
			if(confirm("确认删除【"+empName+"】吗？")){
				//确认删除,发送ajax请求
				$.ajax({
					url:"<c:url value='/emp/'/>"+$(this).attr("del_id"),
					type:"DELETE",
					success:function(result){
						alert(result.msg);
						//回到本页
						to_page(currentPage);
					}
				});
			}
		});
		
		//全选checkbox
		$("#check_all").click(function(){
			//attr("checked")返回undefined,可以用prop修改和读取DOM原生属性的值,用attr获取自定义属性的值
			$(".check_item").prop("checked",$(this).prop("checked"));
		});
		
		//单个checkbox
		$(document).on("click",".check_item",function(){
			//判断当前选中的元素是否选满
			var flag = $(".check_item:checked").length == $(".check_item").length;
			$("#check_all").prop("checked",flag);
		});
		
		//全部删除->批量删除
		$("#emp_delete_all_btn").click(function(){
			var empNames = "";
			var del_idstr = "";
			//遍历被选中的checkbox
			$.each($(".check_item:checked"),function(){
				empNames += $(this).parents("tr").find("td:eq(2)").text()+",";//第3个td元素,索引为2
				del_idstr += $(this).parents("tr").find("td:eq(1)").text()+"-";//第2个td元素,索引为1
			});
			empNames = empNames.substring(0,empNames.length-1);
			del_idstr = del_idstr.substring(0,del_idstr.length-1);
			if(confirm("确认删除【"+empNames+"】吗？")){
				//确认删除,发送ajax请求
				//组装批量删除del_ids
				$.ajax({
					url:"<c:url value='/emp/'/>"+del_idstr,
					type:"DELETE",
					success:function(result){
						alert(result.msg);
						//回到本页
						to_page(currentPage);
					}
				});
			}
		});
	</script>
</body>
</html>