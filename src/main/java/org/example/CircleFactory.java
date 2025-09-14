package org.example;

public class CircleFactory extends HoleFactory{
    private final double radius;

    public CircleFactory(double radius) {
        this.radius = radius;
    }

    @Override
    public Hole createHole() {
        return new Circle(radius);
    }
}