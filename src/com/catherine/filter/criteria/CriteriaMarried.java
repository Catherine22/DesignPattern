package com.catherine.filter.criteria;

import java.util.ArrayList;
import java.util.List;

import com.catherine.filter.Person;

public class CriteriaMarried implements Criteria {

	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> marriedPeople = new ArrayList<>();
		for (Person p : persons) {
			if (p.getMaritalStatus().equalsIgnoreCase("MARRIED"))
				marriedPeople.add(p);
		}
		return marriedPeople;
	}

}
