package org.example;

public class CircleFactory extends HoleFactory{
    private double radius;

    public CircleFactory(double radius) {
        this.radius = radius;
    }

    public CircleFactory() {}

    @Override
    public Hole createHole() {
        return new Circle(radius);
    }
}






//        double radius = InputValidator.positiveDouble("Введите радиус круга: ", "Ошибка: число недопустимо", "Введите коректное число");
