package com.ivan.ssm.service;

import java.util.List;

import com.ivan.ssm.domain.Employee;

public interface EmployeeService {

	//查询所有员工
	List<Employee> getAllEmpWithDept();

	//保存新增员工信息
	void saveEmp(Employee employee);

	//校验用户名是否已存在
	boolean checkUser(String empName);

	//根据id查询员工信息
	Employee getEmp(Integer id);

	//保存员工更新信息
	void updateEmp(Employee employee);

	//删除单个员工
	void deleteEmp(Integer id);

	//批量删除员工
	void deleteEmpBatch(List<Integer> del_ids);

}
