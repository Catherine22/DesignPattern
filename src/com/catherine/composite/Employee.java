package com.catherine.composite;

import java.util.LinkedList;
import java.util.List;

/**
 * 简言之，树状结构，用{@code List<List>}实现。
 * 
 * @author Catherine
 *
 */
public class Employee {
	private String name;
	private String dept;
	private int salary;
	private List<Employee> subordinates;

	public Employee(String name, String dept, int salary) {
		this.name = name;
		this.dept = dept;
		this.salary = salary;
		subordinates = new LinkedList<>();
	}

	/**
	 * 添加下属
	 * 
	 * @param subordinate
	 */
	public void add(Employee subordinate) {
		subordinates.add(subordinate);
	}

	/**
	 * 移除下属
	 * 
	 * @param subordinate
	 */
	public void remove(Employee subordinate) {
		subordinates.remove(subordinate);
	}

	public List<Employee> getSubordinates() {
		return subordinates;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", dept=" + dept + ", salary="
				+ salary /* + ", subordinates=" + subordinates */ + "]";
	}
}
