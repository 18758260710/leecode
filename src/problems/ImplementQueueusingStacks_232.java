package problems;

import java.util.Stack;

/**
 * Created by Administrator on 2020/2/6.
 */
public class ImplementQueueusingStacks_232 {
    //插入少读取多的情况
    class MyQueue {
        Stack<Integer> a = new Stack<>();
        Stack<Integer> b = new Stack<>();

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {

        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            while (!a.isEmpty()) {
                b.push(a.pop());
            }
            b.push(x);
            while (!b.isEmpty()) {
                a.push(b.pop());
            }
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            return a.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            return a.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return a.isEmpty();
        }
    }

    //插入多读取少的情况
    class MyQueue2 {
        private Stack<Integer> a;// 输入栈
        private Stack<Integer> b;// 输出栈

        public MyQueue2() {
            a = new Stack<>();
            b = new Stack<>();
        }

        public void push(int x) {
            a.push(x);
        }

        public int pop() {
            // 如果b栈为空，则将a栈全部弹出并压入b栈中，然后b.pop()
            if(b.isEmpty()){
                while(!a.isEmpty()){
                    b.push(a.pop());
                }
            }
            return b.pop();
        }

        public int peek() {
            if(b.isEmpty()){
                while(!a.isEmpty()){
                    b.push(a.pop());
                }
            }
            return b.peek();
        }

        public boolean empty() {
            return a.isEmpty() && b.isEmpty();
        }
    }
}
