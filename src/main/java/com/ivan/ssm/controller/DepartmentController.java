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
 * �����벿����ص�����
 * @author Administrator
 *
 */
@Controller
public class DepartmentController {
	//ע��service
	@Autowired
	private DepartmentService service;
	
	//�������в�����Ϣ
	@ResponseBody
	@RequestMapping("/depts")
	public Message getDepts() {
		//��ȡ���в�����Ϣ
		List<Department> depts = service.getDepts();
		
		return Message.success().add("depts", depts);
	}
}
