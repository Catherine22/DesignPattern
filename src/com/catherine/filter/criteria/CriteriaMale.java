package com.catherine.filter.criteria;

import java.util.ArrayList;
import java.util.List;

import com.catherine.filter.Person;

public class CriteriaMale implements Criteria {

	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> men = new ArrayList<>();
		for (Person p : persons) {
			if (p.getGender().equalsIgnoreCase("MALE"))
				men.add(p);
		}
		return men;
	}

}
