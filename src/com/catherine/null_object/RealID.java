package com.catherine.null_object;

public class RealID extends AbstractID {
	public RealID(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isAvailable() {
		return true;
	}
}
