package problems;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache_146 {
    //my solution1 slow
//    int capacity = 0;
//    Map<Integer,Integer> storage;
//    LinkedList<Integer> quene = new LinkedList<>();
//    public LRUCache_146(int capacity) {
//        storage = new HashMap<>(capacity);
//        this.capacity = capacity;
//    }
//
//    public int get(int key) {
//        if (storage.containsKey(key)){
//            quene.remove(new Integer(key));
//            quene.addFirst(key);
//            return storage.get(key);
//        }
//        return -1;
//    }
//
//    public void put(int key, int value) {
//        if (storage.containsKey(key)){
//            quene.remove(new Integer(key));
//            quene.addFirst(key);
//            storage.put(key,value);
//        }else {
//            if (quene.size()==capacity){
//                int aa = quene.removeLast();
//                storage.remove(aa);
//            }
//            quene.addFirst(key);
//            storage.put(key,value);
//        }
//    }

    //搜索不需要遍历链表 所以更快
//    class DLinkedNode {
//        int key;
//        int value;
//        DLinkedNode prev;
//        DLinkedNode next;
//    }
//
//    private void addNode(DLinkedNode node) {
//        /**
//         * Always add the new node right after head.
//         */
//        node.prev = head;
//        node.next = head.next;
//
//        head.next.prev = node;
//        head.next = node;
//    }
//
//    private void removeNode(DLinkedNode node){
//        /**
//         * Remove an existing node from the linked list.
//         */
//        DLinkedNode prev = node.prev;
//        DLinkedNode next = node.next;
//
//        prev.next = next;
//        next.prev = prev;
//    }
//
//    private void moveToHead(DLinkedNode node){
//        /**
//         * Move certain node in between to the head.
//         */
//        removeNode(node);
//        addNode(node);
//    }
//
//    private DLinkedNode popTail() {
//        /**
//         * Pop the current tail.
//         */
//        DLinkedNode res = tail.prev;
//        removeNode(res);
//        return res;
//    }
//
//    private Hashtable<Integer, DLinkedNode> cache = new Hashtable<>();
//    private int size;
//    private DLinkedNode head, tail;
//    int capacity = 0;
//
//    public LRUCache_146(int capacity) {
//        this.size = 0;
//        this.capacity = capacity;
//
//        head = new DLinkedNode();
//        // head.prev = null;
//
//        tail = new DLinkedNode();
//        // tail.next = null;
//
//        head.next = tail;
//        tail.prev = head;
//    }
//
//    public int get(int key) {
//        DLinkedNode node = cache.get(key);
//        if (node == null) return -1;
//
//        // move the accessed node to the head;
//        moveToHead(node);
//
//        return node.value;
//    }
//
//    public void put(int key, int value) {
//        DLinkedNode node = cache.get(key);
//
//        if(node == null) {
//            DLinkedNode newNode = new DLinkedNode();
//            newNode.key = key;
//            newNode.value = value;
//
//            cache.put(key, newNode);
//            addNode(newNode);
//
//            ++size;
//
//            if(size > capacity) {
//                // pop the tail
//                DLinkedNode tail = popTail();
//                cache.remove(tail.key);
//                --size;
//            }
//        } else {
//            // update the value.
//            node.value = value;
//            moveToHead(node);
//        }
//    }

    //直接使用    LinkedHashMap

    LinkedHashMap<Integer, Integer> map;
    int capacity;
    public LRUCache_146(int capacity) {
        map = new LRU<Integer, Integer>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        return map.getOrDefault(key,-1);
    }

    public void put(int key, int value) {
        map.put(key,value);
    }

    class LRU<K,V> extends  LinkedHashMap<K,V>{
        private int capacity;
        public LRU(int capacity){
            super(16,0.5f,true);
            this.capacity = capacity;
        }
        @Override
        protected boolean removeEldestEntry(Map.Entry<K,V> eldest){
            return size() > capacity;
        }
    }
}
