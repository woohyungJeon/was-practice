package org.example.calculator.counter;

//카운터는 쓰레드입니다.
public class Counter implements Runnable{
    private int count = 0;

    public void increment(){
        count++;
    }

    public void decrement(){
        count--;
    }

    public int getValue(){
        return count;
    }
    @Override
    public void run() {
        this.increment();
        System.out.println("Value for Thread After increament" + Thread.currentThread().getName() + " " + this.getValue());
        this.decrement();
        System.out.println("Value for Trhead at last " + Thread.currentThread().getName() + " " +this.getValue());

    }
}
