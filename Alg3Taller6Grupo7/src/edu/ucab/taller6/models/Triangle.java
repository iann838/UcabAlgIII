package edu.ucab.taller6.models;

public class Triangle extends Shape {

    private double width;
    private double height;
    
    public Triangle() {
        super();
    }

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
    public double calculateArea() {
        this.area = this.getWidth() * this.getHeight() / 2;
        return this.area;
    }

}
