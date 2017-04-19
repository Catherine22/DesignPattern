package com.catherine.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight模式的用意在于减少内存的使用。
 * 好比要画出20个圆形，总共只有五种颜色随意填充，创建一个HashMap以颜色作为key，一共只需要创建5个物件。
 * 
 * @author Catherine
 *
 */
public class ShapeFactory {
	private static Map<String, Shape> shapes = new HashMap<>();

	public Circle getCircle(String color) {
		Circle circle = (Circle) shapes.get(color);
		if (circle == null) {
			Circle c = new Circle(color);
			shapes.put(color, c);
			circle = c;
			System.out.println(String.format("Flyweight: create a new %s circle", color));
		}
		return circle;
	}

	public void debug() {
		// radius的值都是最后一次修改的变量，也就是之前的并没有被记录，而是被新的值覆盖。
		System.out.println(shapes.toString());
	}
}
