package com.catherine.intercepting_filter;

import com.catherine.intercepting_filter.member.MemberInfo;

public class DebuggerFilter implements Filter {

	@Override
	public boolean run(MemberInfo info) {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		for (int i = 0; i < elements.length; i++)
			System.out.println(String.format("Running %s() of %s", elements[i].getMethodName(),
					elements[elements.length - 1].getClassName()));
		return true;
	}

}
