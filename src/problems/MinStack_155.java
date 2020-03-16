package problems;

import java.util.PriorityQueue;
import java.util.Stack;

public class MinStack_155 {
    //my solution1
//    PriorityQueue<Integer> queue;
//    Stack<Integer> stack;
//
//    public MinStack_155() {
//        queue = new PriorityQueue<>();
//        stack = new Stack<>();
//    }
//
//    public void push(int x) {
//        queue.add(x);
//        stack.push(x);
//    }
//
//    public void pop() {
//        Integer a = stack.pop();
//        queue.remove(a);
//    }
//
//    public int top() {
//        return stack.peek();
//    }
//
//    public int getMin() {
//        return queue.peek();
//    }

    public static void main(String[] args) {
        MinStack_155 minStack = new MinStack_155();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

    //O(1)
    class StackNode {
        int val;
        int min;
        StackNode next;

        public StackNode(int x) {
            this.val = x;
        }
    }

    StackNode top;

    /**
     * initialize your data structure here.
     */
    public MinStack_155() {

    }

    public void push(int x) {
        StackNode t = new StackNode(x);
        if (top == null) {
            t.min = x;
        } else {
            t.min = x <= top.min ? x : top.min;
        }

        t.next = top;
        top = t;
    }

    public void pop() {
        if (top != null) {
            top = top.next;
        }
    }

    public int top() {
        return top.val;
    }

    public int getMin() {
        return top.min;
    }
}
