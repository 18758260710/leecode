package problems;

import java.util.HashMap;

/**
 * Created by Administrator on 2020/3/4.
 */
public class P432_AllOoneDataStructure {
//    class MyNode{
//        public MyNode(MyNode previous, MyNode next, int var, String key) {
//            this.previous = previous;
//            this.next = next;
//            this.var = var;
//            this.key = key;
//        }
//
//        MyNode previous;
//        MyNode next;
//        int var;
//        String key;
//    }
//    //mysolution1 33ms
//    MyNode head;
//    MyNode tail;
//    Map<String,MyNode> map = new HashMap<>();
//    public AllOoneDataStructure_432() {
//
//    }
//
//    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
//    public void inc(String key) {
//        if (head==null){
//            head=new MyNode(null,null,1,key);
//            tail=head;
//            map.put(key,head);
//        }else {
//            if (map.containsKey(key)){
//                MyNode current = map.get(key);
//                MyNode previous = current;
//                current.var++;
//                while (previous.previous!=null&&previous.previous.var<current.var){
//                    previous=previous.previous;
//                }
//                if (previous!=current){
//                    current.previous.next=current.next;
//                    if (current.next!=null){
//                        current.next.previous = current.previous;
//                    }else {
//                        tail=current.previous;
//                    }
//                    current.next = previous;
//                    current.previous = previous.previous;
//                    previous.previous = current;
//                    if (current.previous!=null){
//                        current.previous.next=current;
//                    }else {
//                        head=current;
//                    }
//                }
//            }else {
//                tail.next = new MyNode(tail,null,1,key);
//                tail.next.previous=tail;
//                tail = tail.next;
//                map.put(key,tail);
//            }
//        }
//    }
//
//    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
//    public void dec(String key) {
//        if (map.containsKey(key)){
//            MyNode current = map.get(key);
//            if (current.var==1){
//                map.remove(key);
//                if (current==tail && current==head){
//                    tail=null;
//                    head=null;
//                }else if (current==tail){
//                    tail = current.previous;
//                    tail.next=null;
//                }else if (current==head){
//                    head = current.next;
//                    head.previous=null;
//                }else {
//                    current.previous.next=current.next;
//                    current.next.previous=current.previous;
//                }
//            }else {
//                current.var--;
//                MyNode next = current;
//                while (next.next!=null&&next.next.var>current.var){
//                    next=next.next;
//                }
//                if (next!=current){
//                    current.next.previous=current.previous;
//                    if (current.previous!=null){
//                        current.previous.next=current.next;
//                    }else {
//                        head=current.next;
//                    }
//                    current.previous = next;
//                    current.next = next.next;
//                    next.next = current;
//                    if (current.next!=null){
//                        current.next.previous=current;
//                    }else {
//                        tail=current;
//                    }
//                }
//            }
//        }
//    }
//
//    /** Returns one of the keys with maximal value. */
//    public String getMaxKey() {
//        if (head==null)return "";
//        return head.key;
//    }
//
//    /** Returns one of the keys with Minimal value. */
//    public String getMinKey() {
//        if (tail==null)return "";
//        return tail.key;
//    }

    public static void main(String[] args) {
        P432_AllOoneDataStructure aa = new P432_AllOoneDataStructure();
        aa.inc("a");
        aa.inc("b");
        aa.inc("b");
        aa.inc("a");
        aa.inc("a");
    }
    //20ms
    private static class Node {
        String key;
        int count;
        Node pre;
        Node next;

        public Node(String key, int count) {
            this.key = key;
            this.count = count;
        }
    }
    //虚拟节点
    private Node head = new Node("", Integer.MIN_VALUE);
    private Node tail = new Node("", Integer.MAX_VALUE);
    private HashMap<String, Node> map = new HashMap<>();

    /** Initialize your data structure here. */
    public P432_AllOoneDataStructure() {
        head.next = tail;
        tail.pre = head;
    }

    private void remove(Node node) {
        Node before = node.pre;
        Node after = node.next;
        before.next = after;
        after.pre = before;
    }

    private void insertAfter(Node position, Node node) {
        Node before = position;
        Node after = position.next;
        node.pre = before;
        node.next = after;
        before.next = node;
        after.pre = node;
    }

    private void insertBefore(Node position, Node node) {
        Node before = position.pre;
        Node after = position;
        node.pre = before;
        node.next = after;
        before.next = node;
        after.pre = node;
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.count++;
            while (node.count > node.next.count) {
                remove(node);
                insertAfter(node.next, node);
            }
        } else {
            Node node = new Node(key, 1);
            insertAfter(head, node);
            map.put(key, node);
        }

    }

    public void dec(String key) {
        if (!map.containsKey(key)) return;
        Node node = map.get(key);
        if (node.count > 1) {
            node.count--;
            while (node.count < node.pre.count) {
                remove(node);
                insertBefore(node.pre, node);
            }
        } else {
            remove(node);
            map.remove(key);
        }
    }

    public String getMaxKey() {
        if (map.isEmpty()) return "";
        return tail.pre.key;
    }

    public String getMinKey() {
        if (map.isEmpty()) return "";
        return head.next.key;
    }
}
