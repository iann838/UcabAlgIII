package com.ucab.taller1;

import java.util.Scanner;

public final class Triangle extends Shape {
	
	double width;
	double height;

	public Triangle(double width, double height) {
		super();
		this.width = width;
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	double getArea() {
		return this.getWidth() * this.getHeight() / 2;
	}

	static Triangle fromStdin(Scanner scan) {
		System.out.print(">> Base: ");
		double width = scan.nextDouble();
		System.out.print(">> Altura: ");
		double height = scan.nextDouble();
		return new Triangle(width, height);
	}

}
