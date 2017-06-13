package com.catherine.intercepting_filter;

import com.catherine.intercepting_filter.member.Level;
import com.catherine.intercepting_filter.member.MemberInfo;

public class LevelFilter implements Filter {
	private Level level;

	public LevelFilter(Level level) {
		this.level = level;
	}

	@Override
	public boolean run(MemberInfo info) {
		// System.out.println(String.format("Level:%s", info.getLevel()));
		return level.equals(info.getLevel());
	}

}