package com.catherine.filter;

import java.util.ArrayList;
import java.util.List;

import com.catherine.filter.criteria.Criteria;

/**
 * Both A and B are included
 * 
 * @author Catherine
 *
 */
public class AndCriteria implements Criteria {
	private Criteria criteria1;
	private Criteria criteria2;

	public AndCriteria(Criteria criteria1, Criteria criteria2) {
		this.criteria1 = criteria1;
		this.criteria2 = criteria2;
	}

	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> tmp = criteria1.meetCriteria(persons);
		return criteria2.meetCriteria(tmp);
	}

}
