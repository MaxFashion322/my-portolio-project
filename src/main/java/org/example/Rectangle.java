package org.example;

public class Rectangle extends Hole {
    private double height;
    private double length;

    public Rectangle(double _height,double _length){
        height=_height;
        length=_length;
    }

    @Override
    public double getArea(){
        return height*length;
    }
}

