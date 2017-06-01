package com.catherine.visitor;

public class PublicLevel implements AccessLevel {
	private AccessLevel protectedLevel;

	public PublicLevel() {
		protectedLevel = new ProtectedLevel();
	}

	@Override
	public void showInfo(MethodHandler retrieveMethod) {
		protectedLevel.showInfo(retrieveMethod);
		retrieveMethod.accessPublicMethod(this);
	}

}
