package test;

/**
 * Created by Administrator on 2019/12/3.
 */

import java.util.Queue;
import java.util.Random;

/**
 * 生产者
 */
public class Producer extends Thread{
    private Queue<Integer> queue;
    String name;
    int maxSize,i;


    public Producer(String name, Queue<Integer> queue, int maxSize){
        super(name);
        this.name = name;
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run(){
        while(true){
            synchronized(queue){
                while(queue.size() == maxSize){
                    try {
                        System.out .println("Queue is full, Producer[" + name + "] thread waiting for " + "consumer to take something from queue.");
                        queue.wait();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                System.out.println("[" + name + "] Producing value : +" + i);
                queue.offer(i++);
                queue.notifyAll();

                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
