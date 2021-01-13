package problems;

import java.util.PriorityQueue;

/**
 * Created by Administrator on 2020/3/14.
 */
public class P292_NimGame {
    public boolean canWinNim(int n) {
        return n%4!=0;
    }

    public static void main(String[] args) {
        String a = String.valueOf(255);
        String b = String.valueOf(255);
        System.out.println(a==b);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o1.equals(o2)?0:o1<o2?1:-1);
        priorityQueue.add(3);
        System.out.println(priorityQueue.peek());
        priorityQueue.add(39);
        System.out.println(priorityQueue.peek());
        priorityQueue.add(13);
        System.out.println(priorityQueue.peek());
        priorityQueue.add(20);
        System.out.println(priorityQueue.peek());
    }
}
