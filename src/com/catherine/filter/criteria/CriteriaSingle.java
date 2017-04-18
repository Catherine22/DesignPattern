package com.catherine.filter.criteria;

import java.util.ArrayList;
import java.util.List;

import com.catherine.filter.Person;

public class CriteriaSingle implements Criteria {

	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> singlePeople = new ArrayList<>();
		for (Person p : persons) {
			if (p.getMaritalStatus().equalsIgnoreCase("SINGLE"))
				singlePeople.add(p);
		}
		return singlePeople;
	}
}