package com.catherine.prototype;

public abstract class Color implements Cloneable {

	private Type type;

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public abstract void printID();
	/**
	 * 如果拿到的物件是克隆，那么这个方法不会有效果，因为物件的引用扔是旧的物件。
	 */
	public abstract void resetID();

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
