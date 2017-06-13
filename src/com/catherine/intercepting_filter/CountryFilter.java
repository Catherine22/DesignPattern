package com.catherine.intercepting_filter;

import com.catherine.intercepting_filter.member.MemberInfo;

public class CountryFilter implements Filter {
	private int country;

	public CountryFilter(int country) {
		this.country = country;
	}

	@Override
	public boolean run(MemberInfo info) {
		// System.out.println(String.format("Country:%s", info.getCountry()));
		return country == info.getCountry();
	}

}
