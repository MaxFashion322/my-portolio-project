package org.example;

public class RectangleFactory extends HoleFactory{
    private double height;
    private double length;

    public RectangleFactory(double _height,double _length) {
        height = _height;
        length = _length;
    }
    public RectangleFactory(){}

    public Hole createHole(){
        return new Rectangle(height, length);
    }
}






//        double width = InputValidator.positiveDouble("Введите длину в метрах: ", "Ошибка: число недопустимо", "Введите коректное число");
//        double height = InputValidator.positiveDouble("Введите высоту в метрах: ", "Ошибка: число недопустимо", "Введите коректное число");