package org.example;

public class Triangle extends Hole{
    private double base;
    private double height;

    public Triangle(double _base,double _height){
        base = _base;
        height = _height;
    }

    @Override
    public double getArea(){
        return 0.5 * base * height;
    }
}

