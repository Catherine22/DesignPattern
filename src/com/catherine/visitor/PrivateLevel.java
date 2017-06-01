package com.catherine.visitor;

public class PrivateLevel implements AccessLevel{

	@Override
	public void showInfo(MethodHandler retrieveMethod) {
		retrieveMethod.accessPrivateMethod(this);
	}

}
