package com.catherine.visitor;

public class ProtectedLevel implements AccessLevel {
	private AccessLevel privateLevel;

	public ProtectedLevel() {
		privateLevel = new PrivateLevel();
	}

	@Override
	public void showInfo(MethodHandler retrieveMethod) {
		privateLevel.showInfo(retrieveMethod);
		retrieveMethod.accessProtectedMethod(this);
	}

}