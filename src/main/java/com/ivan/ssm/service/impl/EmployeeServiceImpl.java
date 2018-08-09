package com.ivan.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivan.ssm.domain.Employee;
import com.ivan.ssm.mapper.EmployeeDao;
import com.ivan.ssm.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	//注入dao
	@Autowired
	private EmployeeDao dao;

	//查询所有员工
	@Override
	public List<Employee> getAllEmpWithDept() {
		return dao.getAllEmpWithDept();
	}

	//保存新增员工信息
	@Override
	public void saveEmp(Employee employee) {
		dao.saveEmp(employee);
	}

	//校验用户名是否已存在
	@Override
	public boolean checkUser(String empName) {
		
		int count = dao.findByName(empName);
		
		return count == 0;
	}

	//根据id查询员工信息
	@Override
	public Employee getEmp(Integer id) {
		return dao.getEmp(id);
	}

	//保存员工更新信息
	@Override
	public void updateEmp(Employee employee) {
		dao.updateEmp(employee);
	}

	//删除单个员工
	@Override
	public void deleteEmp(Integer id) {
		dao.deleteEmp(id);
	}

	//批量删除员工
	@Override
	@Transactional//dao.deleteEmpBatch(del_ids);//用不了?改用foreach形式,需要事务
	public void deleteEmpBatch(List<Integer> del_ids) {
		for (Integer id : del_ids) {
			dao.deleteEmp(id);
		}
//		dao.deleteEmpBatch(del_ids);//用不了?
	}

}
