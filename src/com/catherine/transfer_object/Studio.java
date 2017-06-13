package com.catherine.transfer_object;

import java.util.LinkedList;
import java.util.List;

public class Studio {
	private List<Employee> stuff = new LinkedList<>();

	public void addEmployee(Employee e) {
		stuff.add(e);
	}

	public void fireEmployee(int ID) {
		for (int i = 0; i < stuff.size(); i++) {
			if (stuff.get(i).getID() == ID)
				stuff.remove(i);
		}
	}

	public void updateEmployee(int ID, String dept) {
		for (int i = 0; i < stuff.size(); i++) {
			if (stuff.get(i).getID() == ID)
				stuff.get(i).setDept(dept);
		}
	}

	public void showAllStuff() {
		for (int i = 0; i < stuff.size(); i++) {
			System.out.println(stuff.get(i).toString());
		}
	}
}
