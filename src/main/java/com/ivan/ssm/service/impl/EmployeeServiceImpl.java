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
	
	//ע��dao
	@Autowired
	private EmployeeDao dao;

	//��ѯ����Ա��
	@Override
	public List<Employee> getAllEmpWithDept() {
		return dao.getAllEmpWithDept();
	}

	//��������Ա����Ϣ
	@Override
	public void saveEmp(Employee employee) {
		dao.saveEmp(employee);
	}

	//У���û����Ƿ��Ѵ���
	@Override
	public boolean checkUser(String empName) {
		
		int count = dao.findByName(empName);
		
		return count == 0;
	}

	//����id��ѯԱ����Ϣ
	@Override
	public Employee getEmp(Integer id) {
		return dao.getEmp(id);
	}

	//����Ա��������Ϣ
	@Override
	public void updateEmp(Employee employee) {
		dao.updateEmp(employee);
	}

	//ɾ������Ա��
	@Override
	public void deleteEmp(Integer id) {
		dao.deleteEmp(id);
	}

	//����ɾ��Ա��
	@Override
	@Transactional//dao.deleteEmpBatch(del_ids);//�ò���?����foreach��ʽ,��Ҫ����
	public void deleteEmpBatch(List<Integer> del_ids) {
		for (Integer id : del_ids) {
			dao.deleteEmp(id);
		}
//		dao.deleteEmpBatch(del_ids);//�ò���?
	}

}
