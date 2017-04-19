package com.catherine.decorator;

public abstract class AbstractDecorator implements Car {
	protected Car car;

	public AbstractDecorator(Car car) {
		this.car = car;
	}

	public void show() {
		System.out.println("Decorator: AbstractDecorator");
		// 这边必须实现，否则没有（汽车）本体
		car.show();
	}
}
