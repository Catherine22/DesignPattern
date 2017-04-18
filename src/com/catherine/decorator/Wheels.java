package com.catherine.decorator;

public class Wheels extends AbstractDecorator {

	public Wheels(Car car) {
		super(car);
	}

	@Override
	public void show() {
		// 这边必须实现，否则没有（汽车）本体
		car.show();
		upgrade();
	}

	private void upgrade() {
		System.out.print("Wheels upgraded,\t");
	}

}
