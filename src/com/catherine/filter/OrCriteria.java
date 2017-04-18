package com.catherine.filter;

import java.util.ArrayList;
import java.util.List;

import com.catherine.filter.criteria.Criteria;

/**
 * A U B (并集)
 * 
 * @author Catherine
 *
 */
public class OrCriteria implements Criteria {
	private Criteria criteria1;
	private Criteria criteria2;

	public OrCriteria(Criteria criteria1, Criteria criteria2) {
		this.criteria1 = criteria1;
		this.criteria2 = criteria2;
	}

	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> set1 = criteria1.meetCriteria(persons);
		List<Person> set2 = criteria2.meetCriteria(persons);
		for (Person p : set2) {
			if (!set1.contains(p))
				set1.add(p);
		}
		return set1;
	}

}
