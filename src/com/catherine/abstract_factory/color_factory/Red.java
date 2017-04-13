package com.catherine.abstract_factory.color_factory;

/**
 * Created by Catherine on 2016/10/4. Soft-World Inc.
 * catherine919@soft-world.com.tw
 */

public class Red implements Color {
	@Override
	public void onDraw() {
		System.out.println("Abstract Factory:Drew red.");
	}
}
