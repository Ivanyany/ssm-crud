package com.ivan.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import com.ivan.ssm.domain.Employee;

public interface EmployeeDao {
//	//添加员工
//	@Insert("insert into tb_emp(name, gender, email) values(#{name}, #{gender}, #{email})")
//	void addEmployee(Employee emp);

	//查询所有员工
	@Select("select e.*, d.* from tb_emp e inner join tb_dept d on e.dept_id = d.id order by e.id")
	@Results({
		@Result(id=true,column="id",property="id"),
        @Result(column="name",property="name"),
        @Result(column="gender",property="gender"),
        @Result(column="email",property="email"),
        @Result(column="dept_id",property="dept_id"),
        @Result(column="dept_id",property="department",
        	one=@One(
            	select="com.ivan.ssm.mapper.DepartmentDao.getDeptById",
            	fetchType=FetchType.EAGER
            ))
	})
	List<Employee> getAllEmpWithDept();
	
	//保存新增员工信息
	@Insert("insert into tb_emp(name, gender, email, dept_id) values(#{name}, #{gender}, #{email}, #{dept_id})")
	void saveEmp(Employee employee);

	//校验用户名是否已存在
	@Select("select count(*) from tb_emp where name = #{empName}")
	int findByName(String empName);

	//根据id查询员工信息
	@Select("select * from tb_emp where id = #{id}")
	Employee getEmp(Integer id);

	//保存员工更新信息
	@Update("update tb_emp set gender = #{gender}, email = #{email}, dept_id = #{dept_id} where id = #{id}")
	void updateEmp(Employee employee);

	//删除单个员工
	@Delete("delete from tb_emp where id = #{id}")
	void deleteEmp(Integer id);

	//批量删除员工//不能使用?
	@Delete("delete from tb_emp where id in #{del_ids}")
	void deleteEmpBatch(List<Integer> del_ids);
}
