package com.ivan.ssm.domain;

import javax.validation.constraints.Pattern;

/**
 * Ա��
 * @author Administrator
 *
 */
public class Employee {
    private Integer id;

    //���JSR303У��
    @Pattern(regexp="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5}$)",
    		message="�û���������2-5�����ֻ�6-16λӢ�ġ����֡��»��ߡ�������")
    private String name;

    private String gender;

    //@Email
    @Pattern(regexp="^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$",
    		message="�����ʽ����ȷ")
    private String email;

    private Integer dept_id;
    
    //��ѯԱ��ʱ���ϲ�����Ϣ
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