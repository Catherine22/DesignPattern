package com.catherine.filter.criteria;

import java.util.ArrayList;
import java.util.List;

import com.catherine.filter.Person;

public class CriteriaFemale implements Criteria {

	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> women = new ArrayList<>();
		for (Person p : persons) {
			if (p.getGender().equalsIgnoreCase("FEMALE"))
				women.add(p);
		}
		return women;
	}

}