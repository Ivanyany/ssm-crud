package com.ivan.ssm.service;

import java.util.List;

import com.ivan.ssm.domain.Department;

public interface DepartmentService {

	//获取所有部门信息
	List<Department> getDepts();
	
}
