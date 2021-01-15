package com.shamanthka.rl.model;

public class Circle extends Shape{

    private float radius;

    public Circle(){}

    public Circle(float r){
        this.radius = r;
    }
    @Override
    public float area() {
        return (float) (this.radius * this.radius * 3.14);
    }
}
