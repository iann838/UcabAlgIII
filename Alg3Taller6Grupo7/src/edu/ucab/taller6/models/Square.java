package edu.ucab.taller6.models;

public class Square extends Shape {

    private double width;
    private double height;
    
    public Square() {
        super();
    }

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
    public double calculateArea() {
        this.area = this.getWidth() * this.getHeight();
        return this.area;
    }

}
