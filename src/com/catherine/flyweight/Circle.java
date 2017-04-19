package com.catherine.flyweight;

public class Circle implements Shape {
	private String color;
	private int radius;

	public Circle(String color) {
		this.color = color;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "Circle [color=" + color + ", radius=" + radius + "]";
	}

	@Override
	public void draw() {
		System.out.println("Circle [color=" + color + ", radius=" + radius + "]");
	}

}
