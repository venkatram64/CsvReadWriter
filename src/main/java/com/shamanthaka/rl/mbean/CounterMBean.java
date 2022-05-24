package com.shamanthaka.rl.mbean;

public interface CounterMBean {
    public void setCount(int count);
    public int getCount();
    public void increment();
    public void decrement();
}
