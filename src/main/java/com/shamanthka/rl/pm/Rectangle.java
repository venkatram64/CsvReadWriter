package com.shamanthka.rl.pm;

public class Rectangle extends Shape{

    private float length;
    private float breadth;

    public Rectangle(){}

    public Rectangle(float l, float b){
        this.length = l;
        this.breadth = b;
    }
    @Override
    public float area() {
        return this.length * this.breadth;
    }
}
