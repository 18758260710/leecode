package test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 生产者消费者模式：使用Object.wait() / notify()方法实现
 */
public class ProducerConsumer {
    private static final int CAPACITY = 5;

    public static void main(String args[]){
        Queue<Integer> queue = new LinkedList<>();

        Thread producer1 = new Producer("P-1", queue, CAPACITY);
        Thread producer2 = new Producer("P-2", queue, CAPACITY);
        Thread consumer1 = new Consumer("C1", queue, CAPACITY);
        Thread consumer2 = new Consumer("C2", queue, CAPACITY);
        Thread consumer3 = new Consumer("C3", queue, CAPACITY);

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
    }

}