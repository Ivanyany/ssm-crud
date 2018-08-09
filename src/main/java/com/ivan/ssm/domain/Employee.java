package com.ivan.ssm.domain;

import javax.validation.constraints.Pattern;

/**
 * 员工
 * @author Administrator
 *
 */
public class Employee {
    private Integer id;

    //后端JSR303校验
    @Pattern(regexp="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5}$)",
    		message="用户名必须是2-5个汉字或6-16位英文、数字、下划线、横杠组成")
    private String name;

    private String gender;

    //@Email
    @Pattern(regexp="^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$",
    		message="邮箱格式不正确")
    private String email;

    private Integer dept_id;
    
    //查询员工时带上部门信息
    private Department department;

    
	public Employee() {
		super();
	}

	public Employee(String name, String gender, String email, Integer dept_id) {
		super();
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.dept_id = dept_id;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getDept_id() {
		return dept_id;
	}


	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}


	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", gender=" + gender + ", email=" + email + ", dept_id="
				+ dept_id + ", department=" + department + "]";
	}
	
}