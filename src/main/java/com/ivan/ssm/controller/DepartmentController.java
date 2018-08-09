package com.ivan.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ivan.ssm.domain.Department;
import com.ivan.ssm.domain.Employee;
import com.ivan.ssm.domain.Message;
import com.ivan.ssm.service.DepartmentService;

/**
 * 处理与部门相关的请求
 * @author Administrator
 *
 */
@Controller
public class DepartmentController {
	//注入service
	@Autowired
	private DepartmentService service;
	
	//返回所有部门信息
	@ResponseBody
	@RequestMapping("/depts")
	public Message getDepts() {
		//获取所有部门信息
		List<Department> depts = service.getDepts();
		
		return Message.success().add("depts", depts);
	}
}
