package edu.ucab.taller6.models;

public class Circle extends Shape {

    private double radius;

    public Circle() {
        super();
    };

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
    public double calculateArea() {
        this.area = Math.PI * Math.pow(this.getRadius(), 2);
        return this.area;
    }

}
