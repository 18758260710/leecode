package problems;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2020/1/16.
 */
public class P460_LFUCache {
    public void main(String[] args) {
        new P460_LFUCache().test();
    }
    public void test(){
        LFUCache cache = new LFUCache( 2 /* capacity (缓存容量) */ );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回 1
        cache.put(3, 3);    // 去除 key 2
        cache.get(2);       // 返回 -1 (未找到key 2)
        cache.get(3);       // 返回 3
        cache.put(4, 4);    // 去除 key 1
        cache.get(1);       // 返回 -1 (未找到 key 1)
        cache.get(3);       // 返回 3
        cache.get(4);       // 返回 4

    }

    //单层链表
    class LFUCache {
        Node head = new Node(0,0);
        Node tail = new Node(0,0);
        Map<Integer,Node> map;
        int capacity;
        int size=0;
        public LFUCache(int capacity) {
            head.count=Integer.MAX_VALUE;
            head.next=tail;
            tail.previous=head;
            map = new HashMap<>(capacity);
            this.capacity =capacity;

        }

        public int get(int key) {
            if (map.containsKey(key)){
                Node target = map.get(key);
                target.count++;
                modifyTarget(target);
                System.out.println(target.val);
                return target.val;
            }else {
                System.out.println(-1);
                return -1;
            }
        }

        public void put(int key, int value) {
            if (capacity==0)return;
            Node target;
            if (map.containsKey(key)){
                target = map.get(key);
                target.val=value;
                target.count++;
            }else {
                if (size==capacity){
                    Node temp = tail.previous;
                    temp.previous.next=tail;
                    tail.previous = temp.previous;
                    map.remove(temp.key);
                    size--;
                }
                Node temp = tail.previous;
                target = new Node(value,key);
                target.val=value;
                target.previous=temp;
                temp.next=target;
                tail.previous=target;
                target.next=tail;
                size++;
                map.put(key,target);
            }
            modifyTarget(target);
        }

        private void modifyTarget(Node target) {
            Node tailnext = target.next;
            Node tailprevious = target.previous;
            Node headprevious = target.previous;

            while (headprevious.count<=target.count){
                headprevious = headprevious.previous;
            }
            if (tailprevious != headprevious){
                Node headnext = headprevious.next;
                headnext.previous = target;
                target.next=headnext;
                headprevious.next=target;
                target.previous=headprevious;

                tailnext.previous=tailprevious;
                tailprevious.next=tailnext;
            }
        }

        class Node{
            int count = 1;
            int val;
            int key;
            Node next;
            Node previous;
            public Node(int val,int key){
                this.val = val;
                this.key = key;
            }

        }
    }

    //双层链表  同一频率的node组成一个链表 这些链表相连 组成最外层链表
    public class LFUCache2 {

        private class Node {
            int key;
            int value;
            int frequency = 0; //访问次数
            Node next; //下一元素
            Node prev; //前一元素
            NodeQueue nq;  //所属的外层链表元素

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private class NodeQueue {
            NodeQueue next; //下一元素
            NodeQueue prev;  //前一元素
            Node tail;  //尾部Node
            Node head;  //头部Node

            public NodeQueue(NodeQueue next, NodeQueue prev, Node tail, Node head) {
                this.next = next;
                this.prev = prev;
                this.tail = tail;
                this.head = head;
            }
        }

        private NodeQueue tail;  //链表尾部的NodeQueue
        private int capacity;  //容量
        private HashMap<Integer, Node> map;  //存储key-value对的HashMap

        //构造方法
        public LFUCache2(int capacity) {
            this.capacity = capacity;
            map = new HashMap<Integer, Node>(capacity);
        }

        private void oneStepUp(Node n) {
            n.frequency++; //访问次数+1
            boolean singleNodeQ = false; //为true时，代表此NodeQueue中只有一个Node元素
            if (n.nq.head == n.nq.tail)
                singleNodeQ = true;
            if (n.nq.next != null) {
                if (n.nq.next.tail.frequency == n.frequency) {
                    //右侧NodeQueue的访问次数与Node当前访问次数一样，将此Node置于右侧NodeQueue的头部
                    removeNode(n); //从当前NodeQueue中删除Node
                    //把Node插入到右侧NodeQueue的头部
                    n.prev = n.nq.next.head;
                    n.nq.next.head.next = n;
                    n.nq.next.head = n;
                    n.nq = n.nq.next;
                } else if (n.nq.next.tail.frequency > n.frequency) {
                    //右侧NodeQueue的访问次数大于Node当前访问次数，则需要在两个NodeQueue之间插入一个新的NodeQueue
                    if (!singleNodeQ) {
                        removeNode(n);
                        NodeQueue nnq = new NodeQueue(n.nq.next, n.nq, n, n);
                        n.nq.next.prev = nnq;
                        n.nq.next = nnq;
                        n.nq = nnq;
                    }
                    //如果当前NodeQueue中只有一个Node，那么其实不需要任何额外操作了
                }
            } else {
                //此NodeQueue的next == null，说明此NodeQueue已经位于外层链表头部了，这时候需要往外侧链表头部插入一个新的NodeQueue
                if (!singleNodeQ) {
                    removeNode(n);
                    NodeQueue nnq = new NodeQueue(null, n.nq, n, n);
                    n.nq.next = nnq;
                    n.nq = nnq;
                }
                //同样地，如果当前NodeQueue中只有一个Node，不需要任何额外操作
            }
        }

        private Node removeNode(Node n) {
            //如果NodeQueue中只有一个Node，那么移除整个NodeQueue
            if (n.nq.head == n.nq.tail) {
                removeNQ(n.nq);
                return n;
            }
            if (n.prev != null)
                n.prev.next = n.next;
            if (n.next != null)
                n.next.prev = n.prev;
            if (n.nq.head == n)
                n.nq.head = n.prev;
            if (n.nq.tail == n)
                n.nq.tail = n.next;
            n.prev = null;
            n.next = null;
            return n;
        }

        private void removeNQ(NodeQueue nq) {
            if (nq.prev != null)
                nq.prev.next = nq.next;
            if (nq.next != null)
                nq.next.prev = nq.prev;
            if (this.tail == nq)
                this.tail = nq.next;
        }

        public int get(int key) {
            Node n = map.get(key);
            if (n == null)
                return -1;
            oneStepUp(n);
            return n.value;
        }

        public void put(int key, int value) {
            if (capacity == 0)
                return;

            Node cn = map.get(key);
            //key已存在的情况下，更新value值，并将Node右移
            if (cn != null) {
                cn.value = value;
                oneStepUp(cn);
                return;
            }
            //cache已满的情况下，把外层链表尾部的内层链表的尾部Node移除
            if (map.size() == capacity) {
                map.remove(removeNode(this.tail.tail).key);
            }
            //插入新的Node
            Node n = new Node(key, value);
            if (this.tail == null) {
                //tail为null说明此时cache中没有元素，直接把Node封装到NodeQueue里加入
                NodeQueue nq = new NodeQueue(null, null, n, n);
                this.tail = nq;
                n.nq = nq;
            } else if (this.tail.tail.frequency == 0) {
                //外层链表尾部元素的访问次数是0，那么将Node加入到外层链表尾部元素的头部
                n.prev = this.tail.head;
                this.tail.head.next = n;
                n.nq = this.tail;
                this.tail.head = n;
            } else {
                //外层链表尾部元素的访问次数不是0，那么实例化一个只包含此Node的NodeQueue，加入外层链表尾部
                NodeQueue nq = new NodeQueue(this.tail, null, n, n);
                this.tail.prev = nq;
                this.tail = nq;
                n.nq = nq;
            }
            //最后把key和Node存入HashMap中
            map.put(key, n);
        }
    }
}
