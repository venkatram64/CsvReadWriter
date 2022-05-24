package com.shamanthaka.rl.mbean;

public class Counter implements CounterMBean{
    private int count;
    @Override
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public void increment() {
        this.count++;
    }

    @Override
    public void decrement() {
        this.count--;
    }
}
