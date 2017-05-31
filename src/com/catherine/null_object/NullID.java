package com.catherine.null_object;

public class NullID  extends AbstractID {


	@Override
	public String getName() {
		return "This ID is illegal";
	}

	@Override
	public boolean isAvailable() {
		return false;
	}
}
