package com.catherine.intercepting_filter;

import java.util.ArrayList;
import java.util.List;

import com.catherine.intercepting_filter.member.Level;
import com.catherine.intercepting_filter.member.MemberInfo;

public class FilterChain {
	private List<Filter> filters = new ArrayList<>();

	public void addFilter(Filter filter) {
		filters.add(filter);
	}

	public int run(MemberInfo info) {
		boolean next = true;
		int result = 0;
		for (Filter f : filters) {
			if (!f.run(info))
				next = false;
		}

		if (next) {
			System.out.println(String.format("Member:%s", info.getName()));
			if (info.getLevel() == Level.PRIMIUM)
				result = Country.CHINA | Country.UK | Country.US | Country.GLOBAL;
			else if (info.getLevel() == Level.STANDARD) {
				result = Country.GLOBAL | info.getCountry();
			} else
				result = Country.GLOBAL;
		}
		return result;
	}
}
