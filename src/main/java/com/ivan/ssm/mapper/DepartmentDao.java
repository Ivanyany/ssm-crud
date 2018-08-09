package com.ivan.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.ivan.ssm.domain.Department;

public interface DepartmentDao {
	//��Ӳ���
	@Insert("insert into tb_dept(name) values(#{name})")
	void addDepartment(Department dept);

	//��ȡ���в�����Ϣ
	@Select("select * from tb_dept")
	List<Department> getDepts();
	
	//���ݲ���id��ȡ������Ϣ
	@Select("select * from tb_dept where id = #{id}")
	Department getDeptById(Integer id);

}
