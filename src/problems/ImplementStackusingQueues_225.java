package problems;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Administrator on 2020/2/4.
 */
public class ImplementStackusingQueues_225 {
    //插入少读取多的情况
    class MyStack {
        private Queue<Integer> a = new LinkedList<>();
        private Queue<Integer> b = new LinkedList<>();

        /**
         * Initialize your data structure here.
         */
        public MyStack() {

        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            b.add(x);
            while (!a.isEmpty()) {
                b.add(a.poll());
            }
            a = b;
            b = new LinkedList<>();
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            return a.poll();
        }

        /**
         * Get the top element.
         */
        public int top() {
            return a.peek();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return a.isEmpty();
        }
    }

    //插入多读取少的情况
    class MyStack2 {
        private Queue<Integer> a = new LinkedList<>();
        private Queue<Integer> b = new LinkedList<>();

        /**
         * Initialize your data structure here.
         */
        public MyStack2() {

        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            a.offer(x);
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            while (a.size()>1){
                b.offer(a.poll());
            }
            int re = a.poll();
            a=b;
            b=new LinkedList<>();
            return re;
        }

        /**
         * Get the top element.
         */
        public int top() {
            while (a.size()>1){
                b.offer(a.poll());
            }
            int re = a.poll();
            b.offer(re);
            a=b;
            b=new LinkedList<>();
            return re;
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return a.isEmpty();
        }
    }
}
