package com.catherine.intercepting_filter;

import com.catherine.intercepting_filter.member.MemberInfo;

public class FilterManager {
	private FilterChain fChain;

	public FilterManager() {
		fChain = new FilterChain();
	}

	public void addFilter(Filter filter) {
		fChain.addFilter(filter);
	}

	public int filter(MemberInfo info) {
		return fChain.run(info);
	}

}
