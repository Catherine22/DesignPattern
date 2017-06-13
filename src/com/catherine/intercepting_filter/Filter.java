package com.catherine.intercepting_filter;

import com.catherine.intercepting_filter.member.MemberInfo;

public interface Filter {
	public boolean run(MemberInfo info);
}
