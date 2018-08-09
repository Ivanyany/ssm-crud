package com.ivan.ssm.service;

import java.util.List;

import com.ivan.ssm.domain.Employee;

public interface EmployeeService {

	//��ѯ����Ա��
	List<Employee> getAllEmpWithDept();

	//��������Ա����Ϣ
	void saveEmp(Employee employee);

	//У���û����Ƿ��Ѵ���
	boolean checkUser(String empName);

	//����id��ѯԱ����Ϣ
	Employee getEmp(Integer id);

	//����Ա��������Ϣ
	void updateEmp(Employee employee);

	//ɾ������Ա��
	void deleteEmp(Integer id);

	//����ɾ��Ա��
	void deleteEmpBatch(List<Integer> del_ids);

}
