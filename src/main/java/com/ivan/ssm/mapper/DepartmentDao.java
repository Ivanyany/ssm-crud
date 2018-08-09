package com.ivan.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.ivan.ssm.domain.Department;

public interface DepartmentDao {
	//添加部门
	@Insert("insert into tb_dept(name) values(#{name})")
	void addDepartment(Department dept);

	//获取所有部门信息
	@Select("select * from tb_dept")
	List<Department> getDepts();
	
	//根据部门id获取部门信息
	@Select("select * from tb_dept where id = #{id}")
	Department getDeptById(Integer id);

}
