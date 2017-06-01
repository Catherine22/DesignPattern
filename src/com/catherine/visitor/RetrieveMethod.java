package com.catherine.visitor;
/**
 * 访问者模式：<br>
 * 有一种情况是类({@link PrivateLevel})内部某个方法{@code showInfo()}须常常修改，扩充这个方法只会改动方法内部的变量，和自身的类没啥关系，
 * 同时该类也很庞大或者复杂，希望在改动方法时尽量避免改到该类，可以通过访问者模式处理，
 * 让方法内部的实现在另一个类({@link RetrieveMethod})中处理，
 * 未来每次修改只需要更动({@link RetrieveMethod})。
 * 
 * @author Catherine
 *
 */
public class RetrieveMethod implements MethodHandler {

	@Override
	public void accessPrivateMethod(PrivateLevel level) {
		System.out.println("The member can be accessed in its own class.");
	}

	@Override
	public void accessProtectedMethod(ProtectedLevel level) {
		System.out.println("The member can be accessed in its own package.");
	}

	@Override
	public void accessPublicMethod(PublicLevel level) {
		System.out.println("The member can be accessed by all classes everywhere.");
	}

}
