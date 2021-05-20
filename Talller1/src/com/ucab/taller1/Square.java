package com.ucab.taller1;

import java.util.Scanner;

public final class Square extends Shape {

	double width;
	double height;

	public Square(double width, double height) {
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
		return this.getWidth() * this.getHeight();
	}

	static Square fromStdin(Scanner scan) {
		System.out.print(">> Base: ");
		double width = scan.nextDouble();
		System.out.print(">> Altura: ");
		double height = scan.nextDouble();
		return new Square(width, height);
	}

}
