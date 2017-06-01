package com.catherine.visitor;

public interface MethodHandler {
	public void accessPrivateMethod(PrivateLevel level);
	public void accessProtectedMethod(ProtectedLevel level);
	public void accessPublicMethod(PublicLevel level);
}
