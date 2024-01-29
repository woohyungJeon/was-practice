package org.example.calculator.counter;


/*멀티 스레드 환경에서 하나의 객체(자원)을 공유하게 되면
    우리가 뜻하지 않은 레이스 컨디션이 나오게 됩니다.*/

//#레이스컨디션 : 여러 프로세스 혹은 스레드가 동시에 하나의 자원에 접근하기 위해 경쟁하는 상태
//하기 문제는 동기화를 하면 해결이 됩니다.
/*console결과
Value for Thread After increamentThread-3 3
Value for Thread After increamentThread-2 2
Value for Trhead at last Thread-3 2
Value for Trhead at last Thread-2 1
Value for Thread After increamentThread-1 1
Value for Trhead at last Thread-1 0*/
public class RaceConditionDemo {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1 = new Thread(counter, "Thread-1");
        Thread t2 = new Thread(counter, "Thread-2");
        Thread t3 = new Thread(counter, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}

