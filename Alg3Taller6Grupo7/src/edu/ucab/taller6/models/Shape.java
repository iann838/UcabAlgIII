package edu.ucab.taller6.models;

import com.google.gson.annotations.SerializedName;

public abstract class Shape {

    public enum Type {

        @SerializedName("circle")
        CIRCLE("circle"),
        @SerializedName("square")
        SQUARE("square"),
        @SerializedName("triangle")
        TRIANGLE("triangle");

        public final String label;

        private Type(String label) {
            this.label = label;
        }
        
        public static Type nameOf(String label){
            for(Type e : Type.values()){
                if(e.label.equals(label)) return e;
            }
            return null;
        }

    }

    private Type type;
    protected double area;

    public double getArea() {
        return this.calculateArea();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    public abstract double calculateArea();

}
