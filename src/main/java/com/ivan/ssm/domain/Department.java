package com.ivan.ssm.domain;

/**
 * ≤ø√≈
 * @author Administrator
 *
 */
public class Department {
    private Integer id;

    private String name;

    public Department() {
		super();
	}

	public Department(String name) {
		super();
		this.name = name;
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

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}        
}