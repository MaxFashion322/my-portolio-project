package org.example;

public class TriangleFactory extends HoleFactory{
    private double base;
    private double height;

    public TriangleFactory(double _base, double _height) {
        base = _base;
        height = _height;
    }

    public TriangleFactory(){}

    public Hole createHole(){
        return new Triangle(base, height);
    }
}





//        double base = InputValidator.positiveDouble("Введите длину в метрах: ", "Ошибка: число недопустимо", "Введите коректное число");
//        double height = InputValidator.positiveDouble("Введите высоты в метрах: ", "Ошибка: число недопустимо", "Введите коректное число");


