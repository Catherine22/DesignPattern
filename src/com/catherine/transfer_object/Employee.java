package com.catherine.transfer_object;

public class Employee {
	private int ID;
	private String name;
	private String dept;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Employee [ID=" + ID + ", name=" + name + ", dept=" + dept + "]";
	}

}
