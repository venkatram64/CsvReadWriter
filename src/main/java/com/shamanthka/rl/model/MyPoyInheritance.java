package com.shamanthka.rl.model;

public class MyPoyInheritance {

    public static void main(String[] args) {
        Shape shape = new Circle(6);
        float circleArea = shape.area();
        System.out.println(circleArea);

    }
}
