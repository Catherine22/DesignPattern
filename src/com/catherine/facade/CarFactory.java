package com.catherine.facade;

/**
 * 外观模式简单来说就是把内部实现的方法包装后再让用户呼叫（原本是用户直接呼叫），优点是未来内部实现的类有变动时，用户端可能可以不用更动。
 * 
 * @author Catherine
 *
 */
public class CarFactory {
	private Coupe coupe;
	private Convertible convertible;

	public CarFactory() {
		coupe = new Coupe();
		convertible = new Convertible();
	}

	public void buildCoupe() {
		coupe.buildModel();
	}

	public void buildConvertible() {
		convertible.buildModel();
	}
}
