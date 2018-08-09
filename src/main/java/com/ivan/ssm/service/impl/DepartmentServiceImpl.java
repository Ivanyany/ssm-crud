package com.ivan.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivan.ssm.domain.Department;
import com.ivan.ssm.domain.Employee;
import com.ivan.ssm.mapper.DepartmentDao;
import com.ivan.ssm.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentDao dao;

	//��ȡ���в�����Ϣ
	@Override
	public List<Department> getDepts() {
		return dao.getDepts();
	}
}
