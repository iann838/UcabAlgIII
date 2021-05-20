package com.ucab.taller1;

import java.util.Scanner;

public final class Circle extends Shape {

	double radius;
	
	public Circle(double radius) {
		super();
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	double getArea() {
		return Math.PI * Math.pow(this.getRadius(), 2);
	}

	static Circle fromStdin(Scanner scan) {
		System.out.print(">> Radius: ");
		double radius = scan.nextDouble();
		return new Circle(radius);
	}

}
